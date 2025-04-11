/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import DAO.IngredienteDAO;
import DAO.IngredientesProductoDAO;
import DAO.ProductoDAO;
import Entidades.Ingrediente;
import Entidades.IngredientesProducto;
import Entidades.Producto;
import Enums.ProductoActivo;
import Enums.Tipo;
import Enums.UnidadMedida;
import conexion.Conexion;
import exception.PersistenciaException;
import interfaces.IIngredienteDAO;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Admin
 */
public class pruebasIngredienteProducto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PersistenciaException {
        // TODO code application logic here
        EntityManager em = Conexion.crearConexion();
        ProductoDAO productoDAO = ProductoDAO.getInstanceDAO();
        IIngredienteDAO ingredienteDAO = IngredienteDAO.getInstanceDAO();
        IngredientesProductoDAO ingredienteProductoDAO = IngredientesProductoDAO.getInstanceDAO();

        Producto producto = new Producto("HamburguesssSsssSSSyaa Clasica", 150.00, Tipo.PLATILLO, ProductoActivo.Habilitado);

        // Crear un ingrediente
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("Tomate");
        ingrediente.setStock(12);
        ingrediente.setUnidadMedida(UnidadMedida.PIEZAS);

        // Crear productos asociados a ese ingrediente
        IngredientesProducto producto1 = new IngredientesProducto();
        producto1.setProducto(producto);
        producto1.setIngrediente(ingrediente);

        IngredientesProducto producto2 = new IngredientesProducto();
        producto2.setProducto(producto);
        producto2.setIngrediente(ingrediente);
        producto2.setCantidad(2);

        // Asociar los productos al ingrediente
        ingrediente.setProductos(Arrays.asList(producto1, producto2));

        // Iniciar transacción y persistir
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(ingrediente); // Esto persiste tanto el ingrediente como los productos
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
