/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOEntrada.CrearComandaDTO;
import DTOSalida.ClienteDTO;
import DTOSalida.ComandaDTO;
import DTOSalida.DetallesComandaDTO;
import DTOSalida.FiltroComandaDTO;
import DTOSalida.ProductoDTO;
import Entidades.Cliente;
import Entidades.Comanda;
import Entidades.DetallesComanda;
import Entidades.Mesa;
import Entidades.Producto;
import Enums.Estado;
import exception.NegocioException;
import exception.PersistenciaException;
import interfaces.IClienteDAO;
import interfaces.IComandaBO;
import interfaces.IComandaDAO;
import interfaces.IDetallesComandaDAO;
import interfaces.IMesaDAO;
import interfaces.IProductoDAO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ComandaBO implements IComandaBO {

    private IComandaDAO comandaDAO;
    private IMesaDAO mesaDAO;
    private IClienteDAO clienteDAO;
    private IProductoDAO productoDAO;
    private IDetallesComandaDAO detallesComandaDAO;

    public ComandaBO(IComandaDAO comandaDAO, IClienteDAO clienteDAO, IProductoDAO productoDAO, IDetallesComandaDAO detallesComandaDAO) {
        this.detallesComandaDAO = detallesComandaDAO;
        this.productoDAO = productoDAO;
        this.clienteDAO = clienteDAO;
        this.comandaDAO = comandaDAO;
    }

    @Override
    public ComandaDTO registrarComanda(CrearComandaDTO comandaDTO) throws NegocioException {
        if (comandaDTO == null) {
            throw new NegocioException("La información de la comanda es obligatoria.");
        }
        try {
            // Crear la comanda
            Comanda comanda = new Comanda();
            comanda.setFechaHora(LocalDateTime.now());
            comanda.setEstado(comandaDTO.getEstado()); // esto se va a cambiar
            Mesa mesa = new Mesa();
            mesa.setId(comandaDTO.getNumeroMesa().longValue()); // EL NUMERO DE LA MESA VIENE DESDE EL DTO.
            mesa.setNumMesa(comandaDTO.getNumeroMesa());
            comanda.setMesa(mesa);
            comanda.setTotalVenta(0.00);

            int numeroConsecutivo = obtenerSiguienteNumeroConsecutivo();
            String folio = generarFolio(LocalDateTime.now(), numeroConsecutivo);
            comanda.setFolio(folio);

            // Registrar cliente, si aplica
            if (comandaDTO.getCliente() == null || comandaDTO.getCliente().getTelefono() == null) {
                comanda.setCliente(null);
            } else {
                Cliente cliente = clienteDAO.buscarClientePorTelefono(comandaDTO.getCliente().getTelefono());
                comanda.setCliente(cliente);
                // Actualizar puntos, visitas y gasto acumulado al cliente si es necesario --------
            }

            // Registrar la comanda en la base de datos y obtener la comanda con id
            Comanda comandaRegistrada = comandaDAO.registrarComanda(comanda);

            // Obtener los productos y detalles de la comanda
            List<ProductoDTO> productosDTO = comandaDTO.getProductosComanda();
            List<DetallesComandaDTO> detallesComandaDTO = comandaDTO.getDetallesComanda();

            for (int i = 0; i < productosDTO.size(); i++) {
                ProductoDTO productoDTO = productosDTO.get(i);  // Obtener el ProductoDTO actual
                Producto productoConsultado = productoDAO.buscarProductoPorNombre(productoDTO.getNombre());

                DetallesComandaDTO detalleComandaDTO = detallesComandaDTO.get(i);
                // Convertir DetallesComandaDTO a entidad DetallesComanda
                DetallesComanda detallesAGuardar = new DetallesComanda();
                detallesAGuardar.setComentarios(detalleComandaDTO.getComentarios());
                detallesAGuardar.setCantidad(detalleComandaDTO.getCantidad());
                detallesAGuardar.setProducto(productoConsultado); // Asignar el producto consultado
                detallesAGuardar.setPrecioUnitario(detalleComandaDTO.getPrecioUnitario());
                detallesAGuardar.setImporteTotal(detalleComandaDTO.getImporteTotal());

                // Aquí es donde asignas la comanda persistida al detalle
                detallesAGuardar.setComanda(comandaRegistrada); // Asignar la comanda persistida al detalle

                // Guardar el detalle de la comanda
                detallesComandaDAO.guardarDetallesComanda(detallesAGuardar);

            }

            // Retornar DTO de salida
            return new ComandaDTO(
                    comandaRegistrada.getFolio(),
                    comandaRegistrada.getFechaHora(),
                    comandaRegistrada.getMesa().getNumMesa(),
                    comandaRegistrada.getEstado(),
                    comandaRegistrada.getTotalVenta()
            );

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar la comanda: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ComandaDTO> obtenerComandas() throws NegocioException {
        try {
            // Obtener todas las comandas desde el DAO
            List<Comanda> comandas = comandaDAO.obtenerComandasAbiertas();

            // Convertir las entidades Comanda a DTOs
            List<ComandaDTO> comandasDTO = new ArrayList<>();
            for (Comanda comanda : comandas) {
                ComandaDTO comandaDTO = new ComandaDTO(
                        comanda.getFolio(),
                        comanda.getFechaHora(),
                        comanda.getMesa().getNumMesa(),
                        comanda.getEstado(),
                        comanda.getTotalVenta()
                );
                comandasDTO.add(comandaDTO);
            }

            return comandasDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener las comandas: " + e.getMessage(), e);
        }
    }

    @Override
    public ComandaDTO actualizarComanda(ComandaDTO comandaActualizar) throws NegocioException {
        try {
            // Buscar la comanda en la base de datos
            String folio = comandaActualizar.getFolio();
            Comanda comanda = comandaDAO.buscarComandaPorFolio(folio);

            // Actualizar el estado o cualquier otro campo necesario
            comanda.setEstado(comandaActualizar.getEstado()); // por ejemplo, actualizando solo el estado

            // Persistir los cambios en la base de datos
            comandaDAO.actualizarComanda(comanda);

            // Convertir la entidad Comanda actualizada a un DTO
            ComandaDTO comandaDTO = new ComandaDTO(
                    comanda.getFolio(),
                    comanda.getFechaHora(),
                    comanda.getMesa().getNumMesa(),
                    comanda.getEstado(),
                    comanda.getTotalVenta()
            );

            return comandaDTO; // Retornar el DTO actualizado

        } catch (PersistenciaException ex) {
            // Manejo de excepciones
            throw new NegocioException("Error al actualizar la comanda: " + ex.getMessage());
        }
    }

    @Override
    public ComandaDTO buscarComandaPorFolio(String folio) throws NegocioException {
        if (folio == null || folio.trim().isEmpty()) {
            throw new NegocioException("El folio es obligatorio para buscar la comanda.");
        }

        try {
            Comanda comanda = comandaDAO.buscarComandaPorFolio(folio);
            if (comanda == null) {
                throw new NegocioException("No se encontró una comanda con el folio proporcionado.");
            }

            return new ComandaDTO(
                    comanda.getFolio(),
                    comanda.getFechaHora(),
                    comanda.getMesa().getNumMesa(),
                    comanda.getEstado(),
                    comanda.getTotalVenta()
            );

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar la comanda por folio: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ComandaDTO> obtenerUltimaComandaClientes(List<ClienteDTO> clientesDTO) throws NegocioException {
        List<ComandaDTO> comandasDTO = new ArrayList<>();

        for (ClienteDTO cliente : clientesDTO) {
            try {
                Comanda comandaConFecha = comandaDAO.obtenerUltimaComandaCliente(cliente.getId());

                if (comandaConFecha != null) {
                    ComandaDTO comandaDTO = new ComandaDTO();
                    comandaDTO.setFolio(comandaConFecha.getFolio());
                    comandaDTO.setFechaHora(comandaConFecha.getFechaHora());

                    comandasDTO.add(comandaDTO);
                }

            } catch (PersistenciaException ex) {
                throw new NegocioException("Error al buscar la comanda del cliente con ID " + cliente.getId() + ": " + ex.getMessage());
            }
        }

        return comandasDTO;
    }

    @Override
    public List<ComandaDTO> buscarComandas(FiltroComandaDTO filtro) throws NegocioException {
        try {
            List<Comanda> comandas = comandaDAO.buscarComandas(filtro);
            List<ComandaDTO> comandasDTO = new ArrayList<>();

            for (Comanda comanda : comandas) {
                comandasDTO.add(new ComandaDTO(
                        comanda.getFolio(),
                        comanda.getFechaHora(),
                        comanda.getMesa().getNumMesa(),
                        comanda.getEstado(),
                        comanda.getTotalVenta()
                ));
            }

            return comandasDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar comandas: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ComandaDTO> buscarComandasPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws NegocioException {
        try {
            FiltroComandaDTO filtro = new FiltroComandaDTO();
            filtro.setFechaInicio(fechaInicio);
            filtro.setFechaFin(fechaFin);

            List<Comanda> comandas = comandaDAO.buscarComandas(filtro);
            List<ComandaDTO> comandasDTO = new ArrayList<>();
            String nombreCliente;
            for (Comanda comanda : comandas) {
                if (comanda.getCliente() == null || comanda.getCliente().getNombre() == null) {
                    nombreCliente = "N/A";
                } else {
                    nombreCliente = comanda.getCliente().getNombre() + " " + comanda.getCliente().getApellidoPaterno() + " " + comanda.getCliente().getApellidoMaterno();
                }
                ClienteDTO cliente = new ClienteDTO();
                cliente.setNombreCompleto(nombreCliente);
                comandasDTO.add(new ComandaDTO(
                        comanda.getFolio(),
                        comanda.getFechaHora(),
                        comanda.getMesa().getNumMesa(),
                        comanda.getEstado(),
                        comanda.getTotalVenta(),
                        cliente
                ));
            }

            return comandasDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar comandas por fechas: " + e.getMessage(), e);
        }
    }

    //Auxiliares
    private double calcularTotal(List<DetallesComanda> detalles) {
        return detalles.stream()
                .mapToDouble(d -> d.getPrecioUnitario() * d.getCantidad())
                .sum();
    }

    private String generarFolio(LocalDateTime fecha, int numeroConsecutivo) {
        // Formatear la fecha como YYYYMMDD
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String fechaFormateada = fecha.format(formatter);

        // Formatear el número consecutivo a tres dígitos con ceros a la izquierda
        String consecutivoFormateado = String.format("%03d", numeroConsecutivo);

        // Combinar los valores para formar el folio
        return "OB-" + fechaFormateada + "-" + consecutivoFormateado;
    }

    private int obtenerSiguienteNumeroConsecutivo() throws PersistenciaException {
        // Lógica para obtener el número consecutivo único desde la base de datos o almacenamiento
        // Ejemplo simplificado:
        int ultimoNumero = comandaDAO.obtenerUltimoConsecutivo(); // Recuperar el último número usado
        return ultimoNumero + 1;
    }

    @Override
    public String obtenerDetallesComandaPorFolio(String folio) throws NegocioException {
        if (folio == null || folio.trim().isEmpty()) {
            throw new NegocioException("El folio es obligatorio para obtener los detalles de la comanda.");
        }

        try {
            // Buscar la comanda por el folio
            Comanda comanda = comandaDAO.buscarComandaPorFolio(folio);
            if (comanda == null) {
                throw new NegocioException("No se encontró una comanda con el folio proporcionado.");
            }

            // Obtener los detalles de la comanda
            return comandaDAO.obtenerDetallesComanda(comanda);  // Llamada al DAO para obtener los comentarios

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener los detalles de la comanda: " + e.getMessage(), e);
        }
    }

}
