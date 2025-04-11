/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTOSalida.IngredienteDTO;
import Entidades.Ingrediente;
import Entidades.IngredientesProducto;
import conexion.Conexion;
import exception.PersistenciaException;
import interfaces.IIngredientesProductoDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author SDavidLedesma
 */
public class IngredientesProductoDAO implements IIngredientesProductoDAO {

    public static IngredientesProductoDAO instanceIPDAO;

    public IngredientesProductoDAO() {
    }

    public static IngredientesProductoDAO getInstanceDAO() {
        if (instanceIPDAO == null) {
            instanceIPDAO = new IngredientesProductoDAO();
        }

        return instanceIPDAO;
    }

    @Override
    public IngredientesProducto insertar(IngredientesProducto ingredientesProducto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();

        try {
            em.getTransaction().begin();
            em.persist(ingredientesProducto);
            em.getTransaction().commit();

            if (ingredientesProducto.getId() == null) {
                throw new PersistenciaException("Error: No se generó un ID para el ingrediente-producto");
            }

            return ingredientesProducto;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("No se pudo registrar el ingrediente-producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
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

    @Override
    public List<IngredientesProducto> obtenerPorProductoId(Long idProducto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<IngredientesProducto> query = em.createQuery(
                    "SELECT ip FROM IngredientesProducto ip WHERE ip.producto.id = :idProducto", IngredientesProducto.class);
            query.setParameter("idProducto", idProducto);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener los ingredientes del producto con ID: " + idProducto, e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public boolean existeIngredienteEnProductos(IngredienteDTO ingrediente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
