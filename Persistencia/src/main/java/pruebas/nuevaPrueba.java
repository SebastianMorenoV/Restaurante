/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import DAO.ProductoDAO;
import Entidades.IngredientesProducto;
import Entidades.Producto;
import exception.PersistenciaException;

/**
 *
 * @author SDavidLedesma
 */
public class nuevaPrueba {

    public static void main(String[] args) throws PersistenciaException {
        ProductoDAO dao = new ProductoDAO();
        Producto p = dao.obtenerProductoConIngredientes(8L); // ID de producto

        if (p != null) {
            System.out.println("Producto: " + p.getNombre());
            for (IngredientesProducto i : p.getIngredientes()) {
                System.out.println(" - Ingrediente: " + i.getIngrediente().getNombre());
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }
}
