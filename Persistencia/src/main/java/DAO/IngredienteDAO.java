/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Ingrediente;
import conexion.Conexion;
import exception.PersistenciaException;
import interfaces.IIngredienteDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

/**
 *
 * @author Admin
 */
public class IngredienteDAO implements IIngredienteDAO {

    private static IngredienteDAO instanceIngredienteDAO;

    public IngredienteDAO() {
    }

    public static IngredienteDAO getInstanceDAO() {
        if (instanceIngredienteDAO == null) {
            instanceIngredienteDAO = new IngredienteDAO();
        }

        return instanceIngredienteDAO;
    }

    @Override
    public Ingrediente guardarIngrediente(Ingrediente ingrediente) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();

        try {
            em.getTransaction().begin();
            em.persist(ingrediente);
            em.getTransaction().commit();

            if (ingrediente.getId() == null) {
                throw new PersistenciaException("Error: No se generó un ID para el ingrediente");
            }
            return ingrediente;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo registrar el ingrediente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Ingrediente> obtenerTodos() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();

        try {
            return em.createQuery("SELECT i FROM Ingrediente i", Ingrediente.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar todos los ingredientes " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean eliminarIngrediente(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();

        try {
            Ingrediente ingrediente = em.find(Ingrediente.class, id);
            if (ingrediente == null) {
                throw new PersistenciaException("No se encontró el ingrediente con ID: " + id);
            }
            em.getTransaction().begin();
            em.remove(ingrediente);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo eliminar el ingrediente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Ingrediente actualizarIngrediente(Ingrediente ingrediente) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Ingrediente actualizado = em.merge(ingrediente);
            em.getTransaction().commit();
            return actualizado;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo actualizar el ingrediente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Ingrediente buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Ingrediente.class, id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar Ingrediente por ID: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
