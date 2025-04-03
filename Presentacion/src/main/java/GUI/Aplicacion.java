/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BO.ClienteBO;
import DTOEntrada.CrearClienteDTO;
import DTOSalida.ClienteDTO;
import GUI.ModuloClientesFrecuentes.PantallaConsultarClientes;
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
        // pantallas
    private MenuPrincipalRestaurante menuPrincipal;
    private PantallaRegistrarCliente registrarCliente;
    private PantallaConsultarClientes consultarCliente;
    
    
        //Manejadores de BO
    private IClienteBO clientesBO; 
    

    public Aplicacion() {
        framePrincipal = new JFrame("Sistema Restaurante");
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePrincipal.setSize(1150, 700);
        framePrincipal.setLocationRelativeTo(null); // Centrar pantalla

        //inicializar pantallas
        menuPrincipal = new MenuPrincipalRestaurante(this);
        registrarCliente = new  PantallaRegistrarCliente(this);
        consultarCliente = new PantallaConsultarClientes(this);
        
        //manejadores de bo
        clientesBO = ManejadorObjetosNegocio.crearClientesBO();
        
        
    }
    
    
    public ClienteDTO registrarCliente(CrearClienteDTO cliente) throws NegocioException{
        try {
            return clientesBO.registrarCliente(cliente);
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getLocalizedMessage());
        }
    }
            
            
    // Metodos para realizar cambios de pantalla
    public void mostrarMenuPrincipal() {
        cambiarPantalla(menuPrincipal);
    }
    
    public void mostrarPantallaRegistrarCliente() {
        cambiarPantalla(registrarCliente);
    }
    public void mostrarPantallaConsultarCliente(){
        cambiarPantalla(consultarCliente);
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
