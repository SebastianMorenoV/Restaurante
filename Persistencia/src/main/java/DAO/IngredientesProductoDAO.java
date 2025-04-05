/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.IngredientesProducto;
import conexion.Conexion;
import exception.PersistenciaException;
import javax.persistence.EntityManager;

/**
 *
 * @author SDavidLedesma
 */
public class IngredientesProductoDAO {

    public static IngredientesProductoDAO instanceIPDAO;

    public IngredientesProductoDAO() {
    }

    public static IngredientesProductoDAO getInstanceDAO() {
        if (instanceIPDAO == null) {
            instanceIPDAO = new IngredientesProductoDAO();
        }

        return instanceIPDAO;
    }

    public void insertar(IngredientesProducto ingredientesProducto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(ingredientesProducto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }
}
