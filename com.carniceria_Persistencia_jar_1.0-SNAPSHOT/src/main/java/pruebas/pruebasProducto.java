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
        ProductoDAO productoDAO = ProductoDAO.getInstanceDAO();

        try {
            
            // 1. Guardar un nuevo producto
            Producto producto = new Producto();
            producto.setNombre("Hamburguesa Clásica");
            producto.setPrecio(59.99);
            producto.setTipo(Tipo.PLATILLO); 
            producto.setProductoActivo(ProductoActivo.Habilitado); 

            Producto guardado = productoDAO.guardarProducto(producto);
            System.out.println("Producto guardado: " + guardado.getId());

            // 2. Obtener producto por ID
            Producto obtenido = productoDAO.obtenerProductoPorId(guardado.getId());
            System.out.println("Producto obtenido: " + obtenido.getNombre());

            // 3. Actualizar producto
            obtenido.setPrecio(65.00);
            Producto actualizado = productoDAO.actualizarProducto(obtenido);
            System.out.println("Producto actualizado, nuevo precio: " + actualizado.getPrecio());


            // 4. Buscar productos con filtro (DTO)
            ProductoDTO filtro = new ProductoDTO();
            filtro.setNombre("Hamburguesa");
            filtro.setTipo(Tipo.PLATILLO);
            filtro.setProductoActivo(ProductoActivo.Habilitado);

            List<Producto> encontrados = productoDAO.buscarProductos(filtro);
            System.out.println("Productos encontrados con filtro: " + encontrados.size());

            // 5. Obtener todos los productos
            List<Producto> todos = productoDAO.obtenerTodos();
            System.out.println("Total de productos en BD: " + todos.size());

            // 6. Cambiar estado del producto
            boolean cambioEstado = productoDAO.cambiarEstado(guardado.getId(), ProductoActivo.Deshabilitado);
            System.out.println("Cambio de estado exitoso: " + cambioEstado);

            // Verificar cambio
            Producto deshabilitado = productoDAO.obtenerProductoPorId(guardado.getId());
            System.out.println("Nuevo estado del producto: " + deshabilitado.getProductoActivo());

        } catch (PersistenciaException e) {
            System.err.println("Error de persistencia: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }
}
