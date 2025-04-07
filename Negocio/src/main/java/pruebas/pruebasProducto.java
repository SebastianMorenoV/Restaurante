/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import BO.ProductoBO;
import exception.NegocioException;

/**
 *
 * @author SDavidLedesma
 */
public class pruebasProducto {

    public static void main(String[] args) throws NegocioException {
        // Instanciamos ProductoBO para acceder a sus métodos
        //ProductoBO productoBO = new ProductoBO();
       /** 
        try{
            productoBO.crearYGuardarDetalleComanda(1L, 1L, 3, "sin cebolla");
        } catch(Exception e){
            throw new NegocioException("chin");
        }
/**
        // Prueba de registrar un producto
        try {
            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setNombre("Arroz con pollo");
            productoDTO.setPrecio(100.0f);
            productoDTO.setTipo(Tipo.PLATILLO);

            ProductoDTO productoRegistrado = productoBO.registrarProducto(productoDTO);
            System.out.println("Producto registrado: " + productoRegistrado.getNombre() + " con ID: " + productoRegistrado.getId());
        } catch (NegocioException ex) {
            System.err.println("Error al registrar producto: " + ex.getMessage());
        }

        // Prueba de actualizar un producto
        try {
            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setId(1l); // Asumiendo que el producto con ID 1 existe
            productoDTO.setNombre("Arroz con pollo especial");
            productoDTO.setPrecio(120.0f);
            productoDTO.setTipo(Tipo.PLATILLO);

            ProductoDTO productoActualizado = productoBO.actualizarProducto(productoDTO);
            System.out.println("Producto actualizado: " + productoActualizado.getNombre());
        } catch (NegocioException ex) {
            System.err.println("Error al actualizar producto: " + ex.getMessage());
        }

        // Prueba de habilitar/deshabilitar un producto
        try {
            int idProducto = 1; // Asumiendo que el producto con ID 1 existe
            productoBO.habilitar_deshabilitar_producto(idProducto, ProductoActivo.Deshabilitado);
            System.out.println("Producto con ID " + idProducto + " deshabilitado exitosamente.");
        } catch (NegocioException ex) {
            System.err.println("Error al habilitar/deshabilitar producto: " + ex.getMessage());
        }

        // Prueba de buscar productos
        try {
            ProductoDTO filtro = new ProductoDTO(); // Filtro vacío, obtener todos los productos
            List<ProductoDTO> productos = productoBO.buscarProductos(filtro);
            for (ProductoDTO prod : productos) {
                System.out.println("Producto encontrado: " + prod.getNombre());
            }
        } catch (NegocioException ex) {
            System.err.println("Error al buscar productos: " + ex.getMessage());
        }

        // Prueba de agregar ingrediente a un producto
        try {
            Long productoId = 1L; // Asumiendo que el producto con ID 1 existe
            Long ingredienteId = 1L; // Asumiendo que el ingrediente con ID 2 existe
            Integer cantidad = 3;

            productoBO.agregarIngredienteAProducto(productoId, ingredienteId, cantidad);
            System.out.println("Ingrediente agregado al producto exitosamente.");
        } catch (Exception ex) {
            System.err.println("Error al agregar ingrediente al producto: " + ex.getMessage());
        }
    }
    **/
    }
}

