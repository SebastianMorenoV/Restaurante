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
import java.util.List;
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

    //atributos de sesion
    private String rol;
    // Ventana principal
    private JFrame framePrincipal;
    // pantallas
    private MenuPrincipalRestaurante menuPrincipal;
    private MenuSelector menuSelector;
    private PantallaRegistrarCliente registrarCliente;
    private PantallaConsultarClientes consultarCliente;
    private PantallaComandasActivas comandasActivas;

    //Manejadores de BO
    private IClienteBO clientesBO;

    public Aplicacion() {
        framePrincipal = new JFrame("Sistema Restaurante");
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePrincipal.setSize(1150, 700);
        framePrincipal.setLocationRelativeTo(null); // Centrar pantalla

        //inicializar pantallas
        menuSelector = new MenuSelector(this);
        menuPrincipal = new MenuPrincipalRestaurante(this);
        registrarCliente = new PantallaRegistrarCliente(this);
        consultarCliente = new PantallaConsultarClientes(this);
        comandasActivas = new PantallaComandasActivas(this);

        //manejadores de bo
        clientesBO = ManejadorObjetosNegocio.crearClientesBO();

    }

    public ClienteDTO registrarCliente(CrearClienteDTO cliente) throws NegocioException {
        try {
            return clientesBO.registrarCliente(cliente);
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getLocalizedMessage());
        }
    }

    public List<ClienteDTO> buscarClientes(ClienteDTO clienteFiltroDTO) throws NegocioException {
        try {
            return clientesBO.buscarClientes(clienteFiltroDTO);
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getLocalizedMessage());
        }
    }

    // Metodos para realizar cambios de pantalla
    public void mostrarMenuSelector() {
        cambiarPantalla(menuSelector);
    }

    public void mostrarMenuPrincipal() {
        cambiarPantalla(menuPrincipal);
    }

    public void mostrarPantallaRegistrarCliente() {
        cambiarPantalla(registrarCliente);
    }

    public void mostrarPantallaConsultarCliente() {
        cambiarPantalla(consultarCliente);
    }

    public void mostrarPantallaComandasActivas() {
        cambiarPantalla(comandasActivas);
    }

    // Cambiar de pantalla dentro del frame principal
    private void cambiarPantalla(JPanel nuevaPantalla) {
        framePrincipal.getContentPane().removeAll(); // Eliminar contenido anterior
        framePrincipal.getContentPane().add(nuevaPantalla);
        framePrincipal.revalidate();
        framePrincipal.repaint();
        framePrincipal.setVisible(true);
    }

    //Metodos para obtener datos necesarios de sesion
    public void setRol(String rol) {
        this.rol = rol;
    }
    public String getRol(){
        return rol;
    }
}
