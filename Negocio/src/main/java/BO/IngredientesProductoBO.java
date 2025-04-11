/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOSalida.IngredientesProductoDTO;
import Entidades.Ingrediente;
import Entidades.IngredientesProducto;
import Entidades.Producto;
import exception.NegocioException;
import exception.PersistenciaException;
import interfaces.IIngredienteDAO;
import interfaces.IIngredientesProductoBO;
import interfaces.IIngredientesProductoDAO;
import interfaces.IProductoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SDavidLedesma
 */
public class IngredientesProductoBO implements IIngredientesProductoBO {

    private IIngredientesProductoDAO IngredientesProductoDAO;
    private IProductoDAO productoDAO;
    private IIngredienteDAO ingredienteDAO;

    public IngredientesProductoBO(IIngredientesProductoDAO IngredientesProductoDAO) {
        this.IngredientesProductoDAO = IngredientesProductoDAO;
    }

    @Override
    public IngredientesProductoDTO registrarIngredienteProducto(IngredientesProductoDTO ingredientesProducto) throws NegocioException {
        // Crear un objeto IngredientesProducto para la capa de negocio
        IngredientesProducto ingredienteProducto = new IngredientesProducto();
        ingredienteProducto.setCantidad(ingredientesProducto.getCantidad());

        // Convertir el DTO de Ingrediente a la clase de negocio Ingrediente
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setId(ingredientesProducto.getIngrediente().getId()); // Establecer el ID del ingrediente
        ingrediente.setNombre(ingredientesProducto.getIngrediente().getNombre()); // Establecer el nombre del ingrediente
        ingrediente.setUnidadMedida(ingredientesProducto.getIngrediente().getUnidadMedida()); // Establecer la unidad de medida
        
        // Asociar el ingrediente al ingredienteProducto
        ingredienteProducto.setIngrediente(ingrediente);
                
        // Convertir el DTO de Producto al objeto Producto de negocio
        Producto producto = new Producto();
        producto.setId(ingredientesProducto.getProducto().getId()); // Establecer el ID del producto
        producto.setNombre(ingredientesProducto.getProducto().getNombre()); // Establecer el nombre del producto
        producto.setPrecio(ingredientesProducto.getProducto().getPrecio()); // Establecer el precio del producto
        producto.setTipo(ingredientesProducto.getProducto().getTipo()); // Establecer el tipo del producto
        
        // Asociar el producto al ingredienteProducto
        ingredienteProducto.setProducto(producto);
        
        try {
            // Registrar el ingrediente-producto en la base de datos usando el DAO
            IngredientesProductoDAO.insertar(ingredienteProducto);

            // Ahora, devolver el DTO correspondiente
            IngredientesProductoDTO nuevoIngredienteProductoDTO = new IngredientesProductoDTO();
            nuevoIngredienteProductoDTO.setCantidad(ingredienteProducto.getCantidad());
            nuevoIngredienteProductoDTO.setIngrediente(ingredientesProducto.getIngrediente());
            nuevoIngredienteProductoDTO.setProducto(ingredientesProducto.getProducto());

            return nuevoIngredienteProductoDTO; // Devuelvo el DTO con los datos del ingrediente-producto guardado
        } catch (PersistenciaException ex) {
            // Manejar error de persistencia
            Logger.getLogger(IngredientesProductoBO.class.getName()).log(Level.SEVERE, null, ex);
            // Lanzar una excepción de negocio si ocurre un error al guardar
            throw new NegocioException("Error al registrar ingrediente-producto", ex);
        }
    }

}
