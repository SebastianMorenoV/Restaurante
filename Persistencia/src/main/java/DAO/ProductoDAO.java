/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTOSalida.ProductoDTO;
import Entidades.IngredientesProducto;
import interfaces.IProductoDAO;
import Entidades.Producto;
import Enums.ProductoActivo;
import conexion.Conexion;
import exception.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author SDavidLedesma
 */
public class ProductoDAO implements IProductoDAO {

    private static ProductoDAO instanceProductoDAO;

    public ProductoDAO() {
    }

    public static ProductoDAO getInstanceDAO() {
        if (instanceProductoDAO == null) {
            instanceProductoDAO = new ProductoDAO();
        }

        return instanceProductoDAO;
    }

    @Override
    public Producto guardarProducto(Producto producto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();

            if (producto.getId() == null) {
                throw new PersistenciaException("Error: No se generó un ID para el producto a guardar");
            }
            return producto;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo registrar el producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Producto actualizarProducto(Producto producto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Producto actualizado = em.merge(producto);
            em.getTransaction().commit();
            return actualizado;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo actualizar el producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Producto obtenerProductoPorId(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Producto.class, id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar Producto por ID: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Producto> buscarProductos(ProductoDTO filtro) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        List<Producto> productos = new ArrayList<>();

        try {
            em.getTransaction().begin();
            em.flush();

            StringBuilder jpql = new StringBuilder("SELECT p FROM Producto p WHERE 1=1");

            if (filtro.getNombre() != null && !filtro.getNombre().trim().isEmpty()) {
                jpql.append(" AND LOWER(p.nombre) LIKE :nombre");
            }
            if (filtro.getTipo() != null) {
                jpql.append(" AND p.tipo = :tipo");
            }
            if (filtro.getProductoActivo() != null) {
                jpql.append(" AND p.productoActivo = :estatus");
            }

            TypedQuery<Producto> query = em.createQuery(jpql.toString(), Producto.class)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH");

            if (filtro.getNombre() != null && !filtro.getNombre().trim().isEmpty()) {
                query.setParameter("nombre", "%" + filtro.getNombre().toLowerCase() + "%");
            }
            if (filtro.getTipo() != null) {
                query.setParameter("tipo", filtro.getTipo());
            }
            if (filtro.getProductoActivo() != null) {
                query.setParameter("estatus", filtro.getProductoActivo());
            }

            productos = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al buscar productos: " + e.getMessage(), e);
        } finally {
            em.close();
        }

        return productos;
    }

    @Override
    public List<Producto> obtenerTodos() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener todos los productos: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean cambiarEstado(Long id, ProductoActivo nuevoEstado) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            Producto producto = em.find(Producto.class, id);
            if (producto == null) {
                throw new PersistenciaException("No se encontró el producto con ID: " + id);
            }

            em.getTransaction().begin();
            producto.setProductoActivo(nuevoEstado);
            em.merge(producto);
            em.getTransaction().commit();
            return true;
        } catch (PersistenciaException e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo cambiar el estatus del producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Producto buscarProductoPorNombre(String nombre) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();

        try {
            TypedQuery<Producto> query = em.createQuery(
                    "SELECT p FROM Producto p WHERE LOWER(p.nombre) = :nombre", Producto.class);
            query.setParameter("nombre", nombre.toLowerCase());

            return query.getSingleResult(); // Lanza excepción si no encuentra
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar producto por nombre", e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<IngredientesProducto> obtenerIngredientesPorProducto(Long idProducto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT ip FROM IngredientesProducto ip WHERE ip.producto.id = :id", IngredientesProducto.class)
                    .setParameter("id", idProducto)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener ingredientes del producto", e);
        } finally {
            em.close();
        }
    }
    


}
