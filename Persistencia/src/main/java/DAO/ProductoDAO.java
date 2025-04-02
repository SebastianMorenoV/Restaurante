/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import interfaces.IProductoDAO;
import Entidades.Producto;
import Enums.ProductoActivo;
import conexion.Conexion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
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
    public Producto crear(Producto producto) throws PersistenceException {
        EntityManager em = Conexion.crearConexion();

        try {
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();

            if (producto.getId() == null) {
                throw new PersistenceException("ID no generado");
            }
            return producto;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenceException("No se pudo registrar el producto");
        } finally {
            em.close();
        }

    }

    @Override
    public Producto findById(Long id) throws PersistenceException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Producto.class, id);
        } catch (Exception e) {
            throw new PersistenceException("Error al buscar Producto");
        } finally {
            em.close();
        }
    }

    @Override
    public List<Producto> findAll() throws PersistenceException {
        EntityManager em = Conexion.crearConexion();

        try {
            return em.createQuery("SELECT p FROM Productos p", Producto.class).getResultList();
        } catch (Exception e) {
            throw new PersistenceException("Error al buscar todos los productos");
        } finally {
            em.close();
        }
    }

    @Override
    public Producto actualizar(Producto producto) throws PersistenceException {

        EntityManager em = Conexion.crearConexion();

        try {
            em.getTransaction().begin();
            Producto actualizado = em.merge(producto);
            em.getTransaction().commit();
            return actualizado;
        } catch (Exception e) {
            throw new PersistenceException("No se pudo actualizar el producto");
        }
    }

    @Override
    public Producto cambiarEstado(Producto producto, ProductoActivo estadoProducto) throws PersistenceException {

        EntityManager em = Conexion.crearConexion();

        try {
            em.getTransaction().begin();
            if (!em.contains(producto)) {
                producto = em.merge(producto);
            }
            producto.setProductoActivo(estadoProducto); // cambia el estado
            em.getTransaction().commit();
            return producto;
        } catch (Exception e) {
            throw new PersistenceException("No se pudo cambiar el estado del producto");
        } finally {
            em.close();
        }
    }

    @Override
    public Producto EncontrarPorNombre(String nombre) throws PersistenceException {
       EntityManager em = Conexion.crearConexion();
       Producto producto = null;
       
       try{
           TypedQuery<Producto> query = em.createQuery("SELECT p FROM Productos p WHERE p.nombre = :nombre", Producto.class);
           query.setParameter("nombre", nombre);
           List<Producto> results = query.getResultList();
           
           if (!results.isEmpty()) {
               producto = results.get(0);
           }
       }catch(Exception e){
           throw new PersistenceException("Error al busar el producto por nombre: " + e.getMessage(), e);
       } finally{
           if (em != null && em.isOpen()) {
               em.close();
           }
       }
       
       return producto;
    }

}
