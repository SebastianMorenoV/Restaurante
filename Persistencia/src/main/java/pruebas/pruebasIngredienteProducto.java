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
import exception.PersistenciaException;
import interfaces.IIngredienteDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;

/**
 *
 * @author Admin
 */
public class pruebasIngredienteProducto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        ProductoDAO productoDAO = ProductoDAO.getInstanceDAO();
        IIngredienteDAO ingredienteDAO = IngredienteDAO.getInstanceDAO();
        IngredientesProductoDAO ingredienteProductoDAO = IngredientesProductoDAO.getInstanceDAO();

        // guardar un nuevo producto
        //Producto producto = new Producto("Hamburguesa Clasica", 150.00, Tipo.PLATILLO, ProductoActivo.Habilitado);
        try {
//            productoDAO.guardarProducto(producto);
//
//            // crear ingredientes del producto
//            Ingrediente ingrediente = new Ingrediente("Pan", 20, UnidadMedida.PIEZAS);
//            Ingrediente ingrediente1 = new Ingrediente("Tomate", 20, UnidadMedida.PIEZAS);
//            Ingrediente ingrediente2 = new Ingrediente("Lechuga", 20, UnidadMedida.PIEZAS);
//            Ingrediente ingrediente3 = new Ingrediente("Queso americano", 20, UnidadMedida.PIEZAS);
//            Ingrediente ingrediente4 = new Ingrediente("Carne molida", 1000, UnidadMedida.GRAMOS);
//            Ingrediente ingrediente5 = new Ingrediente("Mayonesa", 1000, UnidadMedida.MILILITROS);
//
//            ingredienteDAO.guardarIngrediente(ingrediente);
//            ingredienteDAO.guardarIngrediente(ingrediente1);
//            ingredienteDAO.guardarIngrediente(ingrediente2);
//            ingredienteDAO.guardarIngrediente(ingrediente3);
//            ingredienteDAO.guardarIngrediente(ingrediente4);
//            ingredienteDAO.guardarIngrediente(ingrediente5);
//
//            //IngredientesProducto(Integer cantidad, Ingrediente ingrediente, Producto producto)
//            // creamos los ingredientes del producto a IngredientesProducto
//            IngredientesProducto ip = new IngredientesProducto(1, ingrediente, producto);
//            IngredientesProducto ip1 = new IngredientesProducto(2, ingrediente1, producto);
//            IngredientesProducto ip2 = new IngredientesProducto(1, ingrediente2, producto);
//            IngredientesProducto ip3 = new IngredientesProducto(1, ingrediente3, producto);
//            IngredientesProducto ip4 = new IngredientesProducto(250, ingrediente4, producto);
//            IngredientesProducto ip5 = new IngredientesProducto(50, ingrediente5, producto);
//
//            ingredienteProductoDAO.insertar(ip);
//            ingredienteProductoDAO.insertar(ip1);
//            ingredienteProductoDAO.insertar(ip2);
//            ingredienteProductoDAO.insertar(ip3);
//            ingredienteProductoDAO.insertar(ip4);
//            ingredienteProductoDAO.insertar(ip5);

            Ingrediente ingredienteAsociado = ingredienteDAO.buscarPorId(1L); // o por ID
            ingredienteDAO.eliminarIngrediente(ingredienteAsociado.getId()); // aquí lanzará excepción si está en un producto

        } catch (PersistenciaException ex) {
            Logger.getLogger(pruebasIngredienteProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
//            List<IngredientesProducto> ips = ingredienteProductoDAO.obtenerTodos();
//
//            for (IngredientesProducto ingredientesp : ips) {
//                System.out.println(ingredientesp);
//            }
    }

}
