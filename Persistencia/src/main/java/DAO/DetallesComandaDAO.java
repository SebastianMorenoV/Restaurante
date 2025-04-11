/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.DetallesComanda;
import conexion.Conexion;
import exception.PersistenciaException;
import interfaces.IDetallesComandaDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author SDavidLedesma
 */
public class DetallesComandaDAO implements IDetallesComandaDAO {

    public static DetallesComandaDAO instanceDetallesDAO;

    public DetallesComandaDAO() {
    }

    public static DetallesComandaDAO getInstanceDAO() {
        if (instanceDetallesDAO == null) {
            instanceDetallesDAO = new DetallesComandaDAO();
        }

        return instanceDetallesDAO;
    }

    public void guardar(DetallesComanda detallesComanda) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(detallesComanda);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public DetallesComanda guardarDetallesComanda(DetallesComanda detallesComanda) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();

        try {
            em.getTransaction().begin();
            em.persist(detallesComanda);  // Persistir el objeto detallesComanda
            em.getTransaction().commit();

            if (detallesComanda.getId() == null) {
                throw new PersistenciaException("Error, no se generó un id para el detalle de la comanda");
            }

            return detallesComanda;
        } catch (Exception e) {
            throw new PersistenciaException("Error al registrar el detalle de la comanda: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    @Override
    public List<DetallesComanda> obtenerDetallesPorFolio(String folio) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();

        try {
            // Se asume que existe una relación entre DetallesComanda y Comanda con un campo llamado 'comanda'
            TypedQuery<DetallesComanda> query = em.createQuery(
                    "SELECT d FROM DetallesComanda d WHERE d.comanda.folio = :folio", DetallesComanda.class);
            query.setParameter("folio", folio);

            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener los detalles por folio: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
}
