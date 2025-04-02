/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import GUI.ModuloClientesFrecuentes.PantallaRegistrarCliente;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Sebastian Moreno
 */
public class Aplicacion {

    private JFrame framePrincipal; // Ventana principal
    MenuPrincipalRestaurante menuPrincipal;
    PantallaRegistrarCliente registrarCliente;

    public Aplicacion() {
        framePrincipal = new JFrame("Sistema Restaurante");
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePrincipal.setSize(1150, 700);
        framePrincipal.setLocationRelativeTo(null); // Centrar pantalla

        //inicializar pantallas
        menuPrincipal = new MenuPrincipalRestaurante(this);
        registrarCliente = new  PantallaRegistrarCliente(this);
        
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
