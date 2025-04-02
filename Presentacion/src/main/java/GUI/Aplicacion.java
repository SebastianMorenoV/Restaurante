/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BO.ClienteBO;
import DTOEntrada.CrearClienteDTO;
import DTOSalida.ClienteDTO;
import GUI.ModuloClientesFrecuentes.PantallaRegistrarCliente;
import exception.NegocioException;
import interfaces.IClienteBO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import manejadoresDeObjetoNegocio.ManejadorObjetosNegocio;

/**
 *
 * @author Sebastian Moreno
 */
public class Aplicacion {

    private JFrame framePrincipal; // Ventana principal
    private MenuPrincipalRestaurante menuPrincipal;
    private PantallaRegistrarCliente registrarCliente;
    private IClienteBO clientesBO; 

    public Aplicacion() {
        framePrincipal = new JFrame("Sistema Restaurante");
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePrincipal.setSize(1150, 700);
        framePrincipal.setLocationRelativeTo(null); // Centrar pantalla

        //inicializar pantallas
        menuPrincipal = new MenuPrincipalRestaurante(this);
        registrarCliente = new  PantallaRegistrarCliente(this);
        clientesBO = ManejadorObjetosNegocio.crearClientesBO();
        
    }
    
    
    public ClienteDTO registrarCliente(CrearClienteDTO cliente) throws NegocioException{
        try {
            return clientesBO.registrarCliente(cliente);
        } catch (NegocioException ex) {
            throw new NegocioException("salio algo mal.");
        }
    }
            
            
            
    public void mostrarMenuPrincipal() {
        cambiarPantalla(menuPrincipal);
    }
    
    public void mostrarPantallaRegistrarCliente() {
        cambiarPantalla(registrarCliente);
    }

    // Cambiar de pantalla dentro del frame principal
    private void cambiarPantalla(JPanel nuevaPantalla) {
        framePrincipal.getContentPane().removeAll(); // Eliminar contenido anterior
        framePrincipal.getContentPane().add(nuevaPantalla);
        framePrincipal.revalidate();
        framePrincipal.repaint();
        framePrincipal.setVisible(true);
    }

}
