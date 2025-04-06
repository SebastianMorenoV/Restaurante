/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Ingrediente;
import Enums.UnidadMedida;
import conexion.Conexion;
import exception.PersistenciaException;
import interfaces.IIngredienteDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

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

            IngredientesProductoDAO ipDAO = IngredientesProductoDAO.getInstanceDAO();
            if (ipDAO.existeIngredienteEnProductos(ingrediente)) {
                throw new PersistenciaException("Este ingrediente esta asociado a uno o mas productos.");
            }

            em.getTransaction().begin(); // ← solo después de validaciones
            em.remove(ingrediente);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            // Solo hacer rollback si la transacción está activa
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
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

    @Override
    public boolean existeIngrediente(String nombre, UnidadMedida unidadMedida) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            Long count = em.createQuery(
                    "SELECT COUNT(i) FROM Ingrediente i WHERE i.nombre = :nombre AND i.unidadMedida = :unidadMedida",
                    Long.class)
                    .setParameter("nombre", nombre)
                    .setParameter("unidadMedida", unidadMedida)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            throw new PersistenciaException("Error al verificar existencia del ingrediente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Ingrediente buscarPorNombre(String nombre) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT i FROM Ingrediente i WHERE i.nombre = :nombre", Ingrediente.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar el ingrediente por nombre: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Ingrediente> buscarIngredientes(Ingrediente ingredienteFiltro) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        List<Ingrediente> ingredientes = new ArrayList<>();

        try {
            em.getTransaction().begin(); // iniciar transacción
            em.flush(); // asegurar que los cambios estén reflejados antes de la consulta

            StringBuilder jpql = new StringBuilder("SELECT i FROM Ingrediente i WHERE 1=1");

            if (ingredienteFiltro.getNombre() != null && !ingredienteFiltro.getNombre().trim().isEmpty()) {
                jpql.append(" AND LOWER(i.nombre) LIKE :nombre");
            }

            if (ingredienteFiltro.getUnidadMedida() != null) {
                jpql.append(" AND i.unidadMedida = :unidadMedida");
            }

            TypedQuery<Ingrediente> query = em.createQuery(jpql.toString(), Ingrediente.class)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH"); // evitar cache

            if (ingredienteFiltro.getNombre() != null && !ingredienteFiltro.getNombre().trim().isEmpty()) {
                query.setParameter("nombre", "%" + ingredienteFiltro.getNombre().toLowerCase() + "%");
            }

            if (ingredienteFiltro.getUnidadMedida() != null) {
                query.setParameter("unidadMedida", ingredienteFiltro.getUnidadMedida()); // no necesita LIKE porque es un ENUM
            }

            ingredientes = query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // revertir cambios en caso de error
            }
            throw new PersistenciaException("Error al buscar ingredientes: " + e.getMessage(), e);
        } finally {
            em.close();
        }

        return ingredientes;
    }

}
