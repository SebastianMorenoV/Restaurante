/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.DetallesComanda;
import conexion.Conexion;
import exception.PersistenciaException;
import javax.persistence.EntityManager;

/**
 *
 * @author SDavidLedesma
 */
public class DetallesComandaDAO {

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

}
