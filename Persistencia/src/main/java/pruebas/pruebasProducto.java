/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import DAO.ProductoDAO;
import DTOSalida.ProductoDTO;
import Entidades.Producto;
import Enums.ProductoActivo;
import Enums.Tipo;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public class pruebasProducto {

    public static void main(String[] args) {

        // Creamos una instancia de ProductoDAO
        ProductoDAO productoDAO = ProductoDAO.getInstanceDAO();

        // Crear un producto para pruebas
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre("Producto de prueba");
        nuevoProducto.setTipo(Tipo.PLATILLO);
        nuevoProducto.setProductoActivo(ProductoActivo.Habilitado); // Asumiendo que ProductoActivo es un Enum

        try {
            // Prueba para guardar un producto
            Producto productoGuardado = productoDAO.guardarProducto(nuevoProducto);
            System.out.println("Producto guardado con ID: " + productoGuardado.getId());

            // Prueba para obtener un producto por ID
            Producto productoObtenido = productoDAO.obtenerProductoPorId(productoGuardado.getId());
            System.out.println("Producto obtenido: " + productoObtenido.getNombre());

            // Prueba para actualizar un producto
            productoObtenido.setNombre("Producto actualizado");
            Producto productoActualizado = productoDAO.actualizarProducto(productoObtenido);
            System.out.println("Producto actualizado: " + productoActualizado.getNombre());

            // Prueba para obtener todos los productos
            List<Producto> productos = productoDAO.obtenerTodos();
            System.out.println("Productos obtenidos: ");
            for (Producto producto : productos) {
                System.out.println(producto.getNombre());
            }

            // Prueba para cambiar el estado de un producto
            boolean estadoCambiado = productoDAO.cambiarEstado(productoGuardado.getId(), ProductoActivo.Deshabilitado);
            System.out.println("Estado cambiado: " + estadoCambiado);

            // Prueba para buscar producto por nombre
            Producto productoBuscado = productoDAO.buscarProductoPorNombre("Producto actualizado");
            if (productoBuscado != null) {
                System.out.println("Producto encontrado por nombre: " + productoBuscado.getNombre());
            } else {
                System.out.println("Producto no encontrado por nombre");
            }

        } catch (PersistenciaException e) {
            System.out.println("Error en la persistencia: " + e.getMessage());
        }
    }
}


