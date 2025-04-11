/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOSalida.IngredientesProductoDTO;
import exception.NegocioException;

/**
 *
 * @author SDavidLedesma
 */
public interface IIngredientesProductoBO {

    public IngredientesProductoDTO registrarIngredienteProducto(IngredientesProductoDTO ingredientesProducto) throws NegocioException;

}
