/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Ingrediente;
import Entidades.IngredientesProducto;
import conexion.Conexion;
import exception.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

    public List<IngredientesProducto> obtenerTodos() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<IngredientesProducto> query = em.createQuery("SELECT ip FROM IngredientesProducto ip", IngredientesProducto.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener los ingredientes del producto.", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public boolean existeIngredienteEnProductos(Ingrediente ingrediente) {
        EntityManager em = Conexion.crearConexion();
        try {
            String jpql = "SELECT COUNT(ip) FROM IngredientesProducto ip WHERE ip.ingrediente = :ingrediente";
            Long count = em.createQuery(jpql, Long.class)
                    .setParameter("ingrediente", ingrediente)
                    .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

}
