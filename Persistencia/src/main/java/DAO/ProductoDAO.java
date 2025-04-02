/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Producto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author SDavidLedesma
 */
public class ProductoDAO implements IProductoDAO {

    private EntityManager em;

    public ProductoDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void crear(Producto producto) {
        em.getTransaction().begin();
        em.persist(producto);
        em.getTransaction().commit();
    }

    @Override
    public Producto findById(Long id) {
        return em.find(Producto.class, id);
    }

    @Override
    public List<Producto> findAll() {
        TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p", Producto.class);
        return query.getResultList();
    }

    @Override
    public void actualizar(Producto producto) {
        em.getTransaction().begin();
        em.merge(producto);
        em.getTransaction().commit();
    }

    @Override
    public void eliminar(Producto producto) {
        em.getTransaction().begin();
        if (!em.contains(producto)) {
            producto = em.merge(producto);
        }
        em.remove(producto);
        em.getTransaction().commit();
    }

    @Override
    public Producto EncontrarPorNombre(String nombre) {
        TypedQuery query = em.createQuery("SELECT p FROM Producto P WHERE p.nombre = :nombre", Producto.class);
        query.setParameter("nombre", nombre);
        List<Producto> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

}
