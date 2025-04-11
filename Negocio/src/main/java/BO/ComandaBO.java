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
import Entidades.ClienteFrecuente;
import Entidades.Comanda;
import Entidades.DetallesComanda;
import Entidades.Mesa;
import Entidades.Producto;
import Enums.Estado;
import Enums.Tipo;
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
            comanda.setTotalVenta(0);

            int numeroConsecutivo = obtenerSiguienteNumeroConsecutivo();
            String folio = generarFolio(LocalDateTime.now(), numeroConsecutivo);
            comanda.setFolio(folio);

            // Registrar cliente, si aplica
            if (comandaDTO.getCliente() == null || comandaDTO.getCliente().getTelefono() == null) {
                comanda.setCliente(null);
            } else {
                Cliente cliente = clienteDAO.buscarClientePorTelefono(comandaDTO.getCliente().getTelefono());
                // Actualizar puntos, visitas y gasto acumulado al cliente si es necesario --------
                if (cliente instanceof ClienteFrecuente) {
                    ClienteFrecuente cf = (ClienteFrecuente) cliente;
                    int puntos = cf.getPuntosFidelidad(); // o como se llame tu método
                    Integer numeroVisitas = cf.getVisitas();
                    double gasto = cf.getGastoAcumulado();
                    System.out.println("Puntos de fidelidad: " + puntos);
                    System.out.println("Numero de visitas " + numeroVisitas);
                    System.out.println("gasto acumulado " + gasto + comanda.getTotalVenta());
                } else {
                    System.out.println("Este cliente no es frecuente.");
                }

                comanda.setCliente(cliente);

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
    public ComandaDTO actualizarComandaDetalles(ComandaDTO comandaActualizar) throws NegocioException {
        try {
            String folio = comandaActualizar.getFolio();
            Comanda comanda = comandaDAO.buscarComandaPorFolio(folio);

            List<DetallesComandaDTO> nuevosDetallesDTO = comandaActualizar.getDetallesComanda();
            if (nuevosDetallesDTO == null || nuevosDetallesDTO.isEmpty()) {
                throw new NegocioException("No se proporcionaron detalles para actualizar la comanda.");
            }

            // Limpiar detalles anteriores si es necesario
            comanda.getDetallesComanda().clear();

            List<DetallesComanda> nuevosDetallesEntidad = new ArrayList<>();
            int totalVenta = 0;

            for (DetallesComandaDTO dto : nuevosDetallesDTO) {
                DetallesComanda detalle = new DetallesComanda();

                Producto productoBD = productoDAO.buscarProductoPorNombreYTipo(
                        dto.getProducto().getNombre(),
                        dto.getProducto().getTipo()
                );

                if (productoBD == null) {
                    throw new NegocioException("Producto no encontrado: " + dto.getProducto().getNombre());
                }

                detalle.setProducto(productoBD);
                detalle.setComentarios(dto.getComentarios());
                detalle.setCantidad(1); // ✅ ESTO RESUELVE EL ERROR
                detalle.setComanda(comanda);
                detalle.setPrecioUnitario(productoBD.getPrecio());
                detalle.setImporteTotal(productoBD.getPrecio() * 1);

                totalVenta += productoBD.getPrecio() * 1; // actualiza total correctamente
             
                nuevosDetallesEntidad.add(detalle);
            }
                   System.out.println("totalVenta : " + totalVenta);
            comanda.setDetallesComanda(nuevosDetallesEntidad);
            comanda.setTotalVenta(totalVenta);

            // Guardar cambios
            comandaDAO.actualizarComanda(comanda);
            System.out.println("Comanda actualizada correctamente");
            // Devolver DTO actualizado
            ComandaDTO comandaDTO = new ComandaDTO(
                    comanda.getFolio(),
                    comanda.getFechaHora(),
                    comanda.getMesa().getNumMesa(),
                    comanda.getEstado(),
                    comanda.getTotalVenta()
            );
            comandaDTO.setDetallesComanda(nuevosDetallesDTO); // Opcional

            return comandaDTO;

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al actualizar la comanda: " + ex.getMessage());
        }
    }

    @Override   // se usa
    public ComandaDTO actualizarComanda(ComandaDTO comandaActualizar) throws NegocioException {
        try {
            // Buscar la comanda en la base de datos
            String folio = comandaActualizar.getFolio();
            Comanda comanda = comandaDAO.buscarComandaPorFolio(folio);

            int totalVenta = 0;
            List<DetallesComanda> detalles = comanda.getDetallesComanda();
            // Verificar si los detalles de la comanda están cargados y no están vacíos
            if (detalles == null || detalles.isEmpty()) {
                throw new NegocioException("No hay detalles en la comanda para actualizar.");
            }
            // Variable para acumular los puntos de la comanda
            int puntosComanda = 0;

            for (int i = 0; i < detalles.size(); i++) {
                DetallesComanda detalle = detalles.get(i);
                totalVenta += detalle.getProducto().getPrecio(); // Suma el importe total de cada detalle a totalVenta
            }

            // puntos de la comanda 
            puntosComanda = totalVenta / 20;
            int puntosRedondeados = (int) Math.round(totalVenta / 20.0); // Redondeo con precisión de los puntos

            // Obtener el cliente asociado con la comanda
            Cliente cliente1 = comanda.getCliente();
            Cliente cliente = clienteDAO.buscarClientePorId(cliente1.getId());
            System.out.println(cliente.getTelefono());

            // Si el cliente es de tipo ClienteFrecuente, actualizamos los puntos
            if (cliente instanceof ClienteFrecuente) {
                ClienteFrecuente clienteFrecuente = (ClienteFrecuente) cliente;

                // Obtener los puntos actuales y sumarle los nuevos puntos de la comanda
                int puntosActuales = clienteFrecuente.getPuntosFidelidad();
                int nuevosPuntos = puntosActuales + puntosRedondeados;

                Integer visitasActuales = clienteFrecuente.getVisitas();
                Integer visitasActualizadas = visitasActuales + 1;

                double totalGastadoActual = clienteFrecuente.getGastoAcumulado();
                double totalGastadoActualizado = totalGastadoActual + totalVenta;

                // Actualizar los puntos en el cliente
                clienteFrecuente.setPuntosFidelidad(nuevosPuntos);
                clienteFrecuente.setVisitas(visitasActualizadas);
                clienteFrecuente.setGastoAcumulado(totalGastadoActualizado);
                System.out.println("Puntos actualizados: " + nuevosPuntos);
                System.out.println("puntos visitas: " + visitasActualizadas);
                System.out.println("total gastado : " + totalGastadoActualizado);

                // Persistir los cambios del cliente en la base de datos
                clienteDAO.actualizarCliente(clienteFrecuente);
            }

            // Actualizar el estado o cualquier otro campo necesario
            comanda.setEstado(comandaActualizar.getEstado()); // por ejemplo, actualizando solo el estado
            comanda.setTotalVenta(totalVenta);
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

    @Override   // se usa
    public ComandaDTO actualizarComandaCancelada(ComandaDTO comandaActualizar) throws NegocioException {
        try {
            // Buscar la comanda en la base de datos por su folio
            String folio = comandaActualizar.getFolio();
            Comanda comanda = comandaDAO.buscarComandaPorFolio(folio);

            // Verificamos que la comanda tenga detalles (opcional, puedes quitar esto si no quieres validación)
            List<DetallesComanda> detalles = comanda.getDetallesComanda();
            if (detalles == null || detalles.isEmpty()) {
                throw new NegocioException("No hay detalles en la comanda para actualizar.");
            }

            // Actualizamos solo el estado y el total en 0
            comanda.setEstado(comandaActualizar.getEstado());
            comanda.setTotalVenta(0.0);

            // Persistimos los cambios en la base de datos
            comandaDAO.actualizarComanda(comanda);

            // Retornamos el DTO actualizado
            return new ComandaDTO(
                    comanda.getFolio(),
                    comanda.getFechaHora(),
                    comanda.getMesa().getNumMesa(),
                    comanda.getEstado(),
                    comanda.getTotalVenta()
            );

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al actualizar la comanda como cancelada: " + ex.getMessage());
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

    @Override
    public CrearComandaDTO obtenerComandaActiva(String folio) throws NegocioException {
        if (folio == null || folio.trim().isEmpty()) {
            throw new NegocioException("El folio es obligatorio para obtener los detalles de la comanda.");
        }

        try {
            // Buscar la comanda por el folio
            Comanda comanda = comandaDAO.buscarComandaPorFolio(folio);//de aqui sacar totalVenta
            List<DetallesComanda> detalles = detallesComandaDAO.obtenerDetallesPorFolio(folio);//de aqui sacar comentarios e importe
            //De aqui sacar nombreProducto, categorio y precioUnitario
            List<ProductoDTO> productosDTO = new ArrayList<>();
            List<DetallesComandaDTO> detallesComanda = new ArrayList<>();
            for (int i = 0; i < detalles.size(); i++) {
                DetallesComanda detalle = detalles.get(i);
                Producto producto = productoDAO.obtenerProductoPorId(detalle.getProducto().getId());
                ProductoDTO productoDTO = new ProductoDTO();
                productoDTO.setNombre(producto.getNombre());
                productoDTO.setPrecio(producto.getPrecio());
                productoDTO.setTipo(producto.getTipo());
                productoDTO.setProductoActivo(producto.getProductoActivo());

                productosDTO.add(productoDTO);

                DetallesComandaDTO detallesComandaDTO = new DetallesComandaDTO();
                detallesComandaDTO.setPrecioUnitario(detalle.getPrecioUnitario());
                detallesComandaDTO.setComentarios(detalle.getComentarios());
                detallesComandaDTO.setImporteTotal(detalle.getImporteTotal());

                detallesComanda.add(detallesComandaDTO);

            }
            if (comanda == null) {
                throw new NegocioException("No se encontró una comanda con el folio proporcionado.");
            }

            // Obtener los detalles de la comanda
            //Conversion listas a listasDTO
            ClienteDTO cliente = new ClienteDTO();
            var cli = comanda.getCliente();
            if (cli != null) {
                cliente.setNombreCompleto(
                        (cli.getNombre() != null ? cli.getNombre() : "")
                        + (cli.getApellidoPaterno() != null ? " " + cli.getApellidoPaterno() : "")
                        + (cli.getApellidoMaterno() != null ? " " + cli.getApellidoMaterno() : "")
                );
                cliente.setApellidoPaterno(cli.getApellidoPaterno());
                cliente.setApellidoMaterno(cli.getApellidoMaterno());
                cliente.setTelefono(cli.getTelefono());
            } else {
                cliente.setNombreCompleto("SIN ESPECIFICAR");
            }

            CrearComandaDTO comandaDTO = new CrearComandaDTO();
            comandaDTO.setCliente(cliente);
            comandaDTO.setNumeroMesa(comanda.getMesa().getNumMesa());
            comandaDTO.setTotalVenta(comanda.getTotalVenta());
            comandaDTO.setDetallesComanda(detallesComanda);
            comandaDTO.setProductosComanda(productosDTO);

            return comandaDTO;

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener los detalles de la comanda: " + e.getMessage(), e);
        }
    }

}
