/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOSalida.IngredienteDTO;
import Entidades.IngredientesProducto;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public interface IIngredientesProductoDAO {

    public IngredientesProducto insertar(IngredientesProducto ingredientesProducto) throws PersistenciaException;

    public List<IngredientesProducto> obtenerTodos() throws PersistenciaException;

    public boolean existeIngredienteEnProductos(IngredienteDTO ingrediente);

    public List<IngredientesProducto> obtenerPorProductoId(Long idProducto) throws PersistenciaException;

}
