/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import DTOEntrada.CrearClienteDTO;
import exception.NegocioException;
import interfaces.IClienteBO;
import manejadoresDeObjetoNegocio.ManejadorObjetosNegocio;

/**
 *
 * @author Sebastian Moreno
 */
public class PruebasCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IClienteBO clienteBO = ManejadorObjetosNegocio.crearClientesBO();

        try {
            CrearClienteDTO cliente = new CrearClienteDTO("Juanito Miguel ", "Lopez", "Perez", "6441901249", "lopez@gmail.com");
            CrearClienteDTO cliente2 = new CrearClienteDTO("Juanito Miguel ", "Lopez", "", "6441901249");
            clienteBO.registrarCliente(cliente);
            clienteBO.registrarCliente(cliente2);
        } catch (NegocioException e) {
            e.printStackTrace();
        }

    }

}
