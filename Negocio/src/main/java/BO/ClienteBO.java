/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOEntrada.CrearClienteDTO;
import DTOSalida.ClienteDTO;
import Entidades.Cliente;
import Entidades.ClienteFrecuente;
import exception.NegocioException;
import exception.PersistenciaException;
import interfaces.IClienteBO;
import interfaces.IClienteDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian Moreno
 */
public class ClienteBO implements IClienteBO {

    private IClienteDAO clienteDAO;

    public ClienteBO(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }
    /**
     * Metodo para registrar un cliente en la base de datos.
     * Este metodo valida los datos pasados en su parametro encapsulados.
     * Este metodo valida que no existan telefonos iguales en la base de datos antes de registrar el cliente.
     * Registra los clientes inicialmente con 0 puntos, 0 visitas y 0 de gasto acumulado.
     * @param clienteDTO los datos de un cliente encapsulados en un DTO.
     * @return El cliente DTO insertado.
     * @throws NegocioException Si alguno de los campos estan mal, Si no tiene los parametros solicitados. Si existe algun otro error.
     */
    @Override
    public ClienteDTO registrarCliente(CrearClienteDTO clienteDTO) throws NegocioException {

        // Validar el teléfono (solo dígitos)
        if (clienteDTO.getTelefono() != null && !clienteDTO.getTelefono().matches("^\\d{10}$")) {
            throw new NegocioException("El número de teléfono debe ser de 10 dígitos y contener solo números.");
        }

        // Validar nombres (solo letras)
        if (clienteDTO.getNombres() != null && !clienteDTO.getNombres().matches("^[A-Za-zÁáÉéÍíÓóÚúÑñ\\s]+$")) {
            throw new NegocioException("El nombre solo debe contener letras.");
        }

        // Validar Apellido Paterno (solo letras)
        if (clienteDTO.getApellidoPaterno() != null && !clienteDTO.getApellidoPaterno().matches("^[A-Za-zÁáÉéÍíÓóÚúÑñ\\s]+$")) {
            throw new NegocioException("El apellido paterno solo debe contener letras.");
        }

        // Validar Apellido Materno (solo letras, pero puede estar vacío)
        if (clienteDTO.getApellidoMaterno() != null && !clienteDTO.getApellidoMaterno().isEmpty() && !clienteDTO.getApellidoMaterno().matches("^[A-Za-zÁáÉéÍíÓóÚúÑñ\\s]+$")) {
            throw new NegocioException("El apellido materno solo debe contener letras.");
        }

        // Validar Correo (opcional, formato de correo electrónico)
        if (clienteDTO.getCorreoElectronico() != null && !clienteDTO.getCorreoElectronico().isEmpty() && !clienteDTO.getCorreoElectronico().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new NegocioException("El correo electrónico no es válido.");
        }

        try {
            // Verificar si el teléfono ya está registrado
            if (clienteDAO.existeTelefono(clienteDTO.getTelefono())) {
                throw new NegocioException("El número de teléfono ya está registrado.");
            }

            String apellidoMaterno = clienteDTO.getApellidoMaterno();
            String correo = clienteDTO.getCorreoElectronico();
            if (clienteDTO.getApellidoMaterno().isBlank() || clienteDTO.getApellidoMaterno() == null) {
                apellidoMaterno = null;
            }
            if (clienteDTO.getCorreoElectronico().isBlank() || clienteDTO.getCorreoElectronico() == null) {
                correo = null;
            }

            ClienteFrecuente cliente = new ClienteFrecuente(0, 0, 0, clienteDTO.getNombres(), clienteDTO.getApellidoPaterno(), apellidoMaterno, clienteDTO.getTelefono(), correo, LocalDate.now());
            String cadenaNombre = clienteDTO.getNombres() + " " + clienteDTO.getApellidoPaterno() + " " + apellidoMaterno;
            clienteDAO.guardarClienteFrecuente(cliente);
            return new ClienteDTO(cadenaNombre, cliente.getCorreo(), cliente.getTelefono(), cliente.getPuntosFidelidad(), cliente.getGastoAcumulado(), cliente.getVisitas());
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al guardar el cliente." + ex.getMessage(), ex);
        }

    }
    /**
     * Metodo que busca clientes parcialente por Nombre,Apellidos,Telefono y correo
     * Delega logica de busqueda a la capa DAO , crea una lista DTO de cliente con los datos obtenidos.
     * Dependiendo de los datos obtenidos presenta solo los que obtuvo desde la BD.
     * Detecta si es una instacia de ClienteFrecuente y construye el DTO con puntos , de lo cocntrario un DTO sin puntos.
     * @param clienteFiltroDTO el Cliente pasado de parametro por la presentacion.
     * @return la lista de ClienteDTO obtenida desde la base de datos.
     * @throws NegocioException  Si existe un error buscando los clientes en el proceso.
     */
    public List<ClienteDTO> buscarClientes(ClienteDTO clienteFiltroDTO) throws NegocioException {
        // Convertimos el DTO en una entidad Cliente
        Cliente clienteFiltro = new Cliente();
        clienteFiltro.setNombre(clienteFiltroDTO.getNombre());
        clienteFiltro.setApellidoPaterno(clienteFiltroDTO.getApellidoPaterno());
        clienteFiltro.setApellidoMaterno(clienteFiltroDTO.getApellidoMaterno());
        clienteFiltro.setTelefono(clienteFiltroDTO.getTelefono());
        clienteFiltro.setCorreo(clienteFiltroDTO.getCorreo());

        // Llamamos al método DAO para realizar la búsqueda
        List<Cliente> clientes;
        try {
            clientes = clienteDAO.buscarClientes(clienteFiltro);

            // Convertimos la lista de entidades Cliente a una lista de DTOs para la presentación
            List<ClienteDTO> clientesDTO = new ArrayList<>();
            for (Cliente cliente : clientes) {
                ClienteDTO clienteDTO;
                String correo = "";
                if (cliente.getCorreo() == null) {
                    correo = "N/A";
                } else {
                    correo = cliente.getCorreo();
                }
                String apellidoMaterno = "";
                if (cliente.getApellidoMaterno() == null) {
                    apellidoMaterno = " ";
                } else {
                    apellidoMaterno = cliente.getApellidoMaterno();
                }

                if (cliente instanceof ClienteFrecuente) {
                    // Si es un ClienteFrecuente, castéalo y agrega los valores adicionales
                    ClienteFrecuente clienteFrecuente = (ClienteFrecuente) cliente;
                    clienteDTO = new ClienteDTO(
                            cliente.getNombre(),
                            cliente.getApellidoPaterno(),
                            apellidoMaterno,
                            cliente.getTelefono(),
                            correo
                    );
                    // Aquí agregamos los atributos adicionales de ClienteFrecuente
                    clienteDTO.setPuntos(clienteFrecuente.getPuntosFidelidad());
                    clienteDTO.setVisitasTotales(clienteFrecuente.getVisitas());
                    clienteDTO.setTotalGastado(clienteFrecuente.getGastoAcumulado());
                } else {
                    // Si no es un ClienteFrecuente, simplemente se crea el DTO básico
                    clienteDTO = new ClienteDTO(
                            cliente.getNombre(),
                            cliente.getApellidoPaterno(),
                            apellidoMaterno,
                            cliente.getTelefono(),
                            correo
                    );
                }
                clientesDTO.add(clienteDTO);
            }

            return clientesDTO;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Hubo un error buscando clientes: " + ex.getLocalizedMessage());
        }
    }
    /**
     * Metodo que busca existencias de clientes dependiendo de un limite de visitas y nombres de el cliente.
     * 
     * @param filtro El ClienteDTO con los nombres y minimo de visitas a buscar
     * Este metodo delega su funcionalidad hacia la DAO que es la que se encarga de obtener las existencias.
     * Si el apellido obtenido es nulo entoncces no lo muestra como resultado en la lista.
     * @return Una lista de clientesDTO con los datos puntos,dias,gastoacumulado y fecha de su ultima comanda para cada cliente que corresponda.
     * @throws NegocioException 
     */
    @Override
    public List<ClienteDTO> buscarClienteReporte(ClienteDTO filtro) throws NegocioException {
        try {
            // Crear un ClienteFrecuente con los valores del DTO
            ClienteFrecuente clienteAConsultar = new ClienteFrecuente();
            clienteAConsultar.setNombre(filtro.getNombre());
            clienteAConsultar.setApellidoPaterno(filtro.getApellidoPaterno());
            clienteAConsultar.setApellidoMaterno(filtro.getApellidoMaterno()); // Corregir el apellido materno
            clienteAConsultar.setVisitas(filtro.getVisitasTotales());

            // Llamar al DAO para obtener la lista de clientes frecuentes con los filtros
            List<ClienteFrecuente> clientes = clienteDAO.obtenerClientesFrecuentes(clienteAConsultar);

            // Convertimos la lista de ClienteFrecuente a ClienteDTO
            List<ClienteDTO> clientesDTO = new ArrayList<>();
            for (ClienteFrecuente cliente : clientes) {

                // Crear el nombre completo con los apellidos si están disponibles
                String nombreCompleto = cliente.getNombre() + " " + cliente.getApellidoPaterno();
                if (cliente.getApellidoMaterno() != null) {
                    nombreCompleto += " " + cliente.getApellidoMaterno();
                }

                // Crear el DTO para cada cliente
                ClienteDTO clienteDTO = new ClienteDTO(
                        nombreCompleto, // Nombre completo
                        cliente.getVisitas(),
                        cliente.getGastoAcumulado(),
                        cliente.getPuntosFidelidad()
                );

                // Aquí puedes agregar la lógica adicional, como obtener la última fecha de comanda, si es necesario
                //logicaClienteComanda
                
                // Agregar el clienteDTO a la lista
                clientesDTO.add(clienteDTO);
            }

            return clientesDTO;

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener clientes para el reporte: " + ex.getMessage(), ex);
        }
    }

}
