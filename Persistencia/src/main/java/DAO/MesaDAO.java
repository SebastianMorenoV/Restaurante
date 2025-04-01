/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Mesa;
import conexion.Conexion;
import exception.PersistenciaException;
import javax.persistence.EntityManager;
import interfaces.IMesaDAO;

/**
 *
 * @author Sebastian Moreno
 */
public class MesaDAO implements IMesaDAO {

    private static MesaDAO instanceMesaDAO;

    private MesaDAO() {
    }

    public static MesaDAO getInstanceDAO() {
        if (instanceMesaDAO == null) {
            instanceMesaDAO = new MesaDAO();
        }
        return instanceMesaDAO;
    }

    @Override
    public Mesa guardarMesa(Mesa mesa) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(mesa);
            em.getTransaction().commit();

            if (mesa.getId() == null) {
                throw new PersistenciaException("Error: No se generó un ID para la mesa");
            }
            return mesa;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo registrar la mesa: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean eliminarMesa(Long numeroMesa) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            Mesa mesa = em.find(Mesa.class, numeroMesa);
            if (mesa == null) {
                throw new PersistenciaException("No se encontró la mesa con numero: " + numeroMesa);
            }
            em.getTransaction().begin();
            em.remove(mesa);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo eliminar la mesa: " + e.getMessage());
        } finally {
            em.close();
        }
    }

}
