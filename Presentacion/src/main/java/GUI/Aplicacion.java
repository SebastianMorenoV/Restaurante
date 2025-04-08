/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DAO.IngredientesProductoDAO;
import DTOEntrada.CrearClienteDTO;
import DTOEntrada.CrearComandaDTO;
import DTOEntrada.CrearIngredienteDTO;
import DTOSalida.ClienteDTO;
import DTOSalida.ComandaDTO;
import DTOSalida.IngredienteDTO;
import DTOSalida.MesaDTO;
import DTOSalida.ProductoDTO;
import Entidades.IngredientesProducto;
import GUI.ModuloClientesFrecuentes.PantallaConsultarClientes;
import GUI.ModuloClientesFrecuentes.PantallaRegistrarCliente;
import GUI.ModuloComandas.PantallaComanda;
import GUI.ModuloIngredientes.FormularioRegistrarIngrediente;
import GUI.ModuloIngredientes.PantallaConsultarIngredientes;
import GUI.ModuloReportes.MenuReportes;
import GUI.ModuloReportes.PantallaReporteClientes;
import GUI.ModuloReportes.PantallaReporteComandas;
import GUI.Productos.BuscarProducto;
import GUI.Productos.RegistroProducto;
import exception.NegocioException;
import interfaces.IClienteBO;
import interfaces.IComandaBO;
import interfaces.IIngredienteBO;
import interfaces.IMesaBO;
import interfaces.IProductoBO;
import java.util.List;
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
    private String mesa;
    private String clienteSeleccionado; // talvez puede ser un clienteDTO , si es necesario guardar en comanda el cliente con puntos.
    private boolean siguienteComanda;

    // Ventana principal
    private JFrame framePrincipal;
    // pantallas
    private MenuPrincipalRestaurante menuPrincipal;
    private MenuSelector menuSelector;
    private MenuMesero menuMesero;
    private PantallaRegistrarCliente registrarCliente;
    private PantallaConsultarClientes consultarCliente;
    private PantallaComandasActivas comandasActivas;
    private FormularioRegistrarIngrediente formularioIngrediente;
    private MenuReportes menuReportes;
    private PantallaReporteComandas reporteComandas;
    private PantallaReporteClientes reporteClientes;
    private PantallaConsultarIngredientes consultarIngredientes;
    private PantallaComanda comanda;
    private RegistroProducto producto;
    private BuscarProducto productoConsulta;

    //Manejadores de BO
    private IClienteBO clientesBO;
    private IIngredienteBO ingredientesBO;
    private IMesaBO mesasBO;
    private IProductoBO productoBO;
    private IComandaBO comandaBO;

    public Aplicacion() {
        framePrincipal = new JFrame("Sistema Restaurante");
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePrincipal.setSize(1150, 700);
        framePrincipal.setLocationRelativeTo(null); // Centrar pantalla

        //manejadores de bo
        clientesBO = ManejadorObjetosNegocio.crearClientesBO();
        ingredientesBO = ManejadorObjetosNegocio.crearIngredientesBO();
        mesasBO = ManejadorObjetosNegocio.crearMesasBO();
        productoBO = ManejadorObjetosNegocio.crearProductosBO();
        comandaBO = ManejadorObjetosNegocio.crearComandaBO();

        //inicializar pantallas
        menuSelector = new MenuSelector(this);
        menuPrincipal = new MenuPrincipalRestaurante(this);
        menuMesero = new MenuMesero(this);
        registrarCliente = new PantallaRegistrarCliente(this);
        consultarCliente = new PantallaConsultarClientes(this);
        comandasActivas = new PantallaComandasActivas(this);
        formularioIngrediente = new FormularioRegistrarIngrediente(this);
        menuReportes = new MenuReportes(this);
        reporteComandas = new PantallaReporteComandas(this);
        reporteClientes = new PantallaReporteClientes(this);
        consultarIngredientes = new PantallaConsultarIngredientes(this);
        comanda = new PantallaComanda(this);
        producto = new RegistroProducto(this);
        productoConsulta = new BuscarProducto(this);
    }

    public ClienteDTO registrarCliente(CrearClienteDTO cliente) throws NegocioException {
        try {
            return clientesBO.registrarCliente(cliente);
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getLocalizedMessage());
        }
    }

    public ProductoDTO registrarProducto(ProductoDTO producto) throws NegocioException {
        try {
            return productoBO.registrarProducto(producto);
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

    public List<ClienteDTO> buscarClienteReporte(ClienteDTO filtro) throws NegocioException {
        try {
            return clientesBO.buscarClienteReporte(filtro);
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getLocalizedMessage());
        }
    }

    public IngredienteDTO registrarIngrediente(CrearIngredienteDTO ingrediente) throws NegocioException {
        try {
            return ingredientesBO.agregarIngrediente(ingrediente);
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getLocalizedMessage());
        }
    }

    public void eliminarIngrediente(Long id) throws NegocioException {
        try {
            IngredienteDTO ingrediente = ingredientesBO.obtenerPorId(id);
            ingredientesBO.eliminarIngrediente(ingrediente.getId());
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getLocalizedMessage());
        }
    }

    public IngredienteDTO actualizarStockIngrediente(Long id, int stock) throws NegocioException {
        try {
            return ingredientesBO.actualizarPorID(id, stock);
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getLocalizedMessage());
        }
    }

    public IngredienteDTO obtenerIngredientePorId(Long id) throws NegocioException {
        try {
            return ingredientesBO.obtenerPorId(id);
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getLocalizedMessage());
        }
    }

    public List<IngredienteDTO> obtenerIngredientes() throws NegocioException {
        try {
            if (ingredientesBO == null) {
                throw new NegocioException("Error: ingredientesBO no ha sido inicializado.");
            }
            return ingredientesBO.obtenerTodos();
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getLocalizedMessage());
        }
    }

    public List<IngredienteDTO> buscarIngredientes(IngredienteDTO ingredienteFiltroDTO) throws NegocioException {
        try {
            return ingredientesBO.buscarIngredientes(ingredienteFiltroDTO);
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getLocalizedMessage());
        }
    }

    public boolean insertMasivoMesas() throws NegocioException {
        try {
            return mesasBO.insertMasivoMesas();
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getLocalizedMessage());
        }
    }

    public List<MesaDTO> obtenerTodas() throws NegocioException {
        try {
            return mesasBO.obtenerTodas();
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getLocalizedMessage());
        }
    }

    public List<IngredientesProducto> getIngredientesProductoPorIdProducto(Long idProducto) throws Exception {
        return IngredientesProductoDAO.getInstanceDAO()
                .obtenerTodos()
                .stream()
                .filter(ip -> ip.getProducto().getId().equals(idProducto))
                .toList();
    }

    public List<ProductoDTO> buscarProductos(ProductoDTO filtro) throws NegocioException {
        return productoBO.buscarProductos(filtro);
    }

<<<<<<< HEAD
=======
    //Comanda
    public ComandaDTO guardarComanda(CrearComandaDTO comandaDTO) throws NegocioException {
        try {
            return comandaBO.registrarComanda(comandaDTO);
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getLocalizedMessage());
        }
    }

>>>>>>> f0a8c873ee78cfbb118989b840d8e817704346e6
    /**
     *
     * public ProductoDTO buscarProductoPorNombre(String nombreProducto) throws
     * NegocioException { ProductoDTO filtro = new ProductoDTO();
     * filtro.setNombre(nombreProducto);
     *
     * List<ProductoDTO> productos = productoBO.buscarProductos(filtro); if
     * (!productos.isEmpty()) { return productos.get(0); // Si hay más de uno,
     * retornamos el primero } return null; }
    *
     */
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

    public void mostrarPantallaComanda() {
        cambiarPantalla(comanda);
    }

    public void mostrarPantallaComandasActivas() {
        cambiarPantalla(comandasActivas);
    }

    public void mostrarPantallaRegistrarIngrediente() {
        cambiarPantalla(formularioIngrediente);
    }

    public void mostrarMenuReportes() {
        cambiarPantalla(menuReportes);
    }

    public void mostrarPantallaReporteComandas() {
        cambiarPantalla(reporteComandas);
    }

    public void mostrarPantallaReporteClientes() {
        cambiarPantalla(reporteClientes);
    }

    public void mostrarPantallaConsultarIngredientes() {
        cambiarPantalla(consultarIngredientes);
    }

    public void mostrarMenuMesero() {
        cambiarPantalla(menuMesero);
    }

    public void mostrarRegistroProducto() {
        cambiarPantalla(producto);
    }
    
    public void mostrarBusquedaProducto(){
        cambiarPantalla(productoConsulta);
    }

    public void reconstruirPantallaMesero() {
        menuMesero = new MenuMesero(this);

    }

    public void reconstruirPantallaComanda() {
        comanda = new PantallaComanda(this);

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

    public String getRol() {
        return rol;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public String getMesa() {
        return mesa;
    }

    public String getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(String clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public boolean isSiguienteComanda() {
        return siguienteComanda;
    }

    public void setSiguienteComanda(boolean siguienteComanda) {
        this.siguienteComanda = siguienteComanda;
    }

}
