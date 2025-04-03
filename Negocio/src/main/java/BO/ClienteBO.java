/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOEntrada.CrearClienteDTO;
import DTOSalida.ClienteDTO;
import Entidades.ClienteFrecuente;
import exception.NegocioException;
import exception.PersistenciaException;
import interfaces.IClienteBO;
import interfaces.IClienteDAO;
import java.time.LocalDate;
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

            String cadenaNombre;
            if (clienteDTO.getApellidoMaterno().isBlank() || clienteDTO.getApellidoMaterno() == null) {
                cadenaNombre = clienteDTO.getNombres() + " " + clienteDTO.getApellidoPaterno();
            } else {
                cadenaNombre = clienteDTO.getNombres() + " " + clienteDTO.getApellidoPaterno() + " " + clienteDTO.getApellidoMaterno();
            }
            ClienteFrecuente cliente = new ClienteFrecuente(0, 0, 0, cadenaNombre, clienteDTO.getTelefono(), clienteDTO.getCorreoElectronico(), LocalDate.now());

            clienteDAO.guardarClienteFrecuente(cliente);
            return new ClienteDTO(cadenaNombre, cliente.getCorreo(), cliente.getTelefono(), cliente.getPuntosFidelidad(), cliente.getGastoAcumulado(), cliente.getVisitas());
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al guardar el cliente." + ex.getMessage(), ex);
        }

    }

    @Override
    public List<ClienteDTO> obtenerTodos(CrearClienteDTO clienteDTO) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
