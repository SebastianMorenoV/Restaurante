/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Cliente;
import Entidades.ClienteFrecuente;
import conexion.Conexion;
import exception.PersistenciaException;
import interfaces.IClienteDAO;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * DAO del Modulo ClientesFrecuentes
 *
 * @author Sebastian Moreno
 */
public class ClienteDAO implements IClienteDAO{

    private static ClienteDAO instanceClienteDAO;

    private ClienteDAO() {
    }

    public static ClienteDAO getInstanceDAO() {
        if (instanceClienteDAO == null) {
            instanceClienteDAO = new ClienteDAO();
        }
        return instanceClienteDAO;
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();

            if (cliente.getId() == null) {
                throw new PersistenciaException("Error: No se generó un ID para el cliente a guardar");
            }
            return cliente;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo registrar el cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public ClienteFrecuente guardarClienteFrecuente(ClienteFrecuente clienteFrecuente) throws PersistenciaException {
         EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(clienteFrecuente);
            em.getTransaction().commit();

            if (clienteFrecuente.getId() == null) {
                throw new PersistenciaException("Error: No se generó un ID para el cliente frecuente a guardar");
            }
            return clienteFrecuente;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo registrar el cliente frecuente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Cliente buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Cliente.class, id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar Cliente por ID: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Cliente> obtenerTodos() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar todos los clientes: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<ClienteFrecuente> obtenerClientesFrecuentes() throws PersistenciaException {
       EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT c FROM ClienteFrecuente c", ClienteFrecuente .class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar todos los Clientes Frecuentes: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Cliente actualizado = em.merge(cliente);
            em.getTransaction().commit();
            return actualizado;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo actualizar el cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public boolean eliminar(Long id) throws PersistenciaException {
       EntityManager em = Conexion.crearConexion();
        try {
            Cliente cliente = em.find(Cliente.class, id);
            if (cliente == null) {
                throw new PersistenciaException("No se encontró el cliente con ID: " + id);
            }
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo eliminar el cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    
    
}
