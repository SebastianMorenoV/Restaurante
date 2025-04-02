/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOEntrada.CrearClienteDTO;
import DTOSalida.ClienteDTO;
import exception.NegocioException;
import interfaces.IClienteBO;
import interfaces.IClienteDAO;

/**
 *
 * @author Sebastian Moreno
 */
public class ClienteBO implements IClienteBO{
     private IClienteDAO clienteDAO;

    public ClienteBO(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @Override
    public ClienteDTO registrarCliente(CrearClienteDTO clienteDTO) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     
     
}
