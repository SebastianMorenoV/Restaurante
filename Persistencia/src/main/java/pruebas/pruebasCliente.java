/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import DAO.ClienteDAO;
import Entidades.Cliente;
import Entidades.ClienteFrecuente;
import exception.PersistenciaException;
import interfaces.IClienteDAO;
import java.time.LocalDate;

/**
 *
 * @author Sebastian Moreno
 */
public class pruebasCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        IClienteDAO clienteDAO = ClienteDAO.getInstanceDAO();
//
//        try {
//            Cliente cliente = new Cliente(1L, "Juanito Lopez", "6441901249", "juanito@gmail.com", LocalDate.now(), null);
//            clienteDAO.guardarCliente(cliente);
//        } catch (PersistenciaException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            ClienteFrecuente cliente = new ClienteFrecuente(0, 0, 0, "Pedrito Mora", "6441501923", "pedrito@gmail.com", LocalDate.now());
//            clienteDAO.guardarClienteFrecuente(cliente);
//        } catch (PersistenciaException e) {
//            e.printStackTrace();
//        }

        try {
           
            clienteDAO.eliminar(2L);
        } catch (PersistenciaException e) {
            e.printStackTrace();
        }

    }

}
