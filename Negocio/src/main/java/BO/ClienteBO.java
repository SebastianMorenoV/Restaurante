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

    public List<ClienteDTO> buscarClientes(ClienteDTO clienteFiltroDTO) throws NegocioException {
        // Convertimos el DTO en una entidad Cliente
        Cliente clienteFiltro = new Cliente();
        clienteFiltro.setNombre(clienteFiltroDTO.getNombreCompleto()); // Solo se usa nombre completo
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
                String nombreCompleto = "";
                if (cliente.getApellidoMaterno() == null) {
                    nombreCompleto = cliente.getNombre() + " " + cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno();
                } else {
                    nombreCompleto = cliente.getNombre() + " " + cliente.getApellidoPaterno() + " ";
                }

                if (cliente instanceof ClienteFrecuente) {
                    // Si es un ClienteFrecuente, castéalo y agrega los valores adicionales
                    ClienteFrecuente clienteFrecuente = (ClienteFrecuente) cliente;
                    clienteDTO = new ClienteDTO(
                            nombreCompleto, // Nombre completo
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
                            nombreCompleto, // Nombre completo
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

    @Override
    public List<ClienteDTO> buscarClienteReporte(ClienteDTO filtro) throws NegocioException {
        try {
            // Crear un ClienteFrecuente con los valores del DTO
            ClienteFrecuente clienteAConsultar = new ClienteFrecuente();
            clienteAConsultar.setNombre(filtro.getNombreCompleto());
            System.out.println("Visitas desde bo : " + filtro.getVisitasTotales());
            clienteAConsultar.setVisitas(filtro.getVisitasTotales());

            // Obtener la lista de clientes frecuentes desde la base de datos
            List<ClienteFrecuente> clientes = clienteDAO.obtenerClientesFrecuentes(clienteAConsultar);

            // Convertimos la lista de ClienteFrecuente a ClienteDTO
            List<ClienteDTO> clientesDTO = new ArrayList<>();
            for (ClienteFrecuente cliente : clientes) {

                String nombreCompleto = "";
                if (cliente.getApellidoMaterno() == null) {
                    nombreCompleto = cliente.getNombre() + " " + cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno();
                } else {
                    nombreCompleto = cliente.getNombre() + " " + cliente.getApellidoPaterno() + " ";
                }
                ClienteDTO clienteDTO = new ClienteDTO(
                        nombreCompleto, // Nombre completo
                        cliente.getVisitas(),
                        cliente.getGastoAcumulado(),
                        cliente.getPuntosFidelidad()
                );
                //aqui necesita ir la parte donde obtengo las cosas de la comanda.
                // tambien se calcula la ultima fecha de comanda.

                clientesDTO.add(clienteDTO);
            }

            return clientesDTO;

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener clientes para el reporte: " + ex.getMessage(), ex);
        }
    }

}
