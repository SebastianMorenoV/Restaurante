/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import interfaces.IComandaDAO;
import Entidades.Comanda;
import Enums.Estado;
import conexion.Conexion;
import exception.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author SDavidLedesma
 */
public class ComandaDAO implements IComandaDAO {

    public static ComandaDAO instanceComandaDAO;

    public static ComandaDAO getInstanceDAO() {
        if (instanceComandaDAO == null) {
            instanceComandaDAO = new ComandaDAO();
        }
        return instanceComandaDAO;
    }

    public ComandaDAO() {
    }

    @Override
    public Comanda obtenerComandaPorId(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();

        try {
            return em.find(Comanda.class, id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar Comanda por ID: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Comanda registrarComanda(Comanda comanda) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();

        try {
            em.getTransaction().begin();
            em.persist(comanda);
            em.getTransaction().commit();
            if (comanda.getId() == null) {
                throw new PersistenciaException("Error no se genero un id para la comanda");
            }
            return comanda;
        } catch (Exception e) {
            throw new PersistenciaException("Error al regisstrar comanda " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Comanda actualizarComanda(Comanda comandaActualizar) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();

        try {
            em.getTransaction().begin();
            Comanda comandaActualizada = em.merge(comandaActualizar);
            em.getTransaction().commit();
            return comandaActualizada;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al actualizar la comanda: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }


    //Metodos auxiliares
    //metodo para crear el formato del folio
    @Override
    public int obtenerUltimoConsecutivo() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();

        try {
            // Consulta para obtener el ID más alto de las comandas registradas
            Long ultimoConsecutivo = em.createQuery(
                    "SELECT MAX(c.id) FROM Comanda c", Long.class
            ).getSingleResult();

            // Si no existe ningún registro en la base de datos, comenzamos con 0
            return (ultimoConsecutivo != null) ? ultimoConsecutivo.intValue() : 0;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener el último consecutivo: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Comanda> obtenerComandasAbiertas() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();

        try {
            // Asegúrate de que 'Estado.Abierta' es un valor correcto de tu enum
            return em.createQuery(
                    "SELECT c FROM Comanda c WHERE c.estado = :estado ORDER BY c.fechaHora DESC",
                    Comanda.class)
                    .setParameter("estado", Estado.Abierta) // Aquí 'Estado.Abierta' debe ser un valor de tipo Enum
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener las comandas abiertas: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }



    @Override
    public Comanda buscarComandaPorFolio(String folio) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();

        try {
            return em.createQuery(
                    "SELECT c FROM Comanda c WHERE c.folio = :folio", Comanda.class)
                    .setParameter("folio", folio)
                    .getSingleResult();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar comanda por folio: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
    
    

}
