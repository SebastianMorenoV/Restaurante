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

        if (clienteDTO.getNombres().isBlank()) {
            throw new NegocioException("El nombre no debe de estar vacio!.");
        }
        if (clienteDTO.getApellidoPaterno().isBlank()) {
            throw new NegocioException("Se necesita un apellido paterno!.");
        }
        if (clienteDTO.getTelefono().isEmpty()) {
            throw new NegocioException("Se necesita un telefono!.");
        }

        String cadenaNombre;
        if (clienteDTO.getApellidoMaterno().isBlank() || clienteDTO.getApellidoMaterno() == null) {
            cadenaNombre = clienteDTO.getNombres() + " " + clienteDTO.getApellidoPaterno();
        } else {
            cadenaNombre = clienteDTO.getNombres() + " " + clienteDTO.getApellidoPaterno() + " " + clienteDTO.getApellidoMaterno();
        }

        ClienteFrecuente cliente = new ClienteFrecuente(0, 0, 0, cadenaNombre, clienteDTO.getTelefono(), clienteDTO.getCorreoElectronico(), LocalDate.now());

        try {
            clienteDAO.guardarClienteFrecuente(cliente);
            return new ClienteDTO(cadenaNombre,cliente.getCorreo(),cliente.getTelefono(),cliente.getPuntosFidelidad(),cliente.getGastoAcumulado(),cliente.getVisitas());
        } catch (PersistenciaException ex) {
            Logger.getLogger(ClienteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al guardar el cliente." + ex.getMessage(), ex);
        }

    }

}
