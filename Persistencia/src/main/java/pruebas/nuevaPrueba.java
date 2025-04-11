/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import DAO.IngredientesProductoDAO;
import DAO.ProductoDAO;
import Entidades.Ingrediente;
import Entidades.IngredientesProducto;
import Entidades.Producto;
import Enums.ProductoActivo;
import Enums.Tipo;
import Enums.UnidadMedida;
import exception.PersistenciaException;

/**
 *
 * @author SDavidLedesma
 */
public class nuevaPrueba {

    public static void main(String[] args) throws PersistenciaException {
        // Crear el Producto
        Producto producto = new Producto();
        producto.setNombre("Producto de prueba");
        producto.setPrecio(50.0);
        producto.setTipo(Tipo.PLATILLO);
        producto.setProductoActivo(ProductoActivo.Habilitado);

        // Crear el Ingrediente
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("Ingrediente de prueba");
        ingrediente.setUnidadMedida(UnidadMedida.GRAMOS);

        // Crear el IngredientesProducto
        IngredientesProducto ingredienteProducto = new IngredientesProducto();
        ingredienteProducto.setCantidad(5); // Definir la cantidad para la relación

        // Relacionar el Ingrediente y el Producto con IngredientesProducto
        ingredienteProducto.setIngrediente(ingrediente);
        ingredienteProducto.setProducto(producto);

        // Crear el DAO para realizar la inserción
        IngredientesProductoDAO dao = new IngredientesProductoDAO();

        try {
            // Llamar al método insertar
            IngredientesProducto resultado = dao.insertar(ingredienteProducto);

            // Si la inserción es exitosa, mostrar el ID generado
            if (resultado != null && resultado.getId() != null) {
                System.out.println("Ingrediente-Producto insertado exitosamente. ID: " + resultado.getId());
            } else {
                System.out.println("Error: El ID no fue generado.");
            }
        } catch (PersistenciaException ex) {
            // Si ocurre una excepción, se muestra el mensaje de error
            System.err.println("Error al insertar el ingrediente-producto: " + ex.getMessage());
        }
    }

}
