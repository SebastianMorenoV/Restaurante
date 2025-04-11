/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOSalida.IngredientesProductoDTO;
import Entidades.IngredientesProducto;
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
    public void insertar(IngredientesProductoDTO ingredientesProducto) throws NegocioException {
        IngredientesProducto ingredienteProducto = new IngredientesProducto();
        ingredienteProducto.setCantidad(ingredientesProducto.getCantidad());
        ingredienteProducto.setId(ingredientesProducto.getId());
        ingredientesProducto.setIngrediente(ingredientesProducto.getIngrediente());
        ingredientesProducto.setProducto(ingredientesProducto.getProducto());

        try {
            IngredientesProductoDAO.insertar(ingredienteProducto);

            // ingredienteProducto.setIngrediente(ingrediente);
            // IngredientesProductoDAO.insertar(ingredientesProducto);
        } catch (PersistenciaException ex) {
            Logger.getLogger(IngredientesProductoBO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
