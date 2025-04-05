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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * DAO del Modulo ClientesFrecuentes
 *
 * @author Sebastian Moreno
 */
public class ClienteDAO implements IClienteDAO {

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
    public List<ClienteFrecuente> obtenerClientesFrecuentes(ClienteFrecuente filtro) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        List<ClienteFrecuente> clientes = new ArrayList<>();

        try {
            em.getTransaction().begin();
            em.flush(); // Asegurar que los datos recientes estén reflejados

            StringBuilder jpql = new StringBuilder("SELECT c FROM ClienteFrecuente c WHERE 1=1");

            if (filtro.getNombre() != null && !filtro.getNombre().trim().isEmpty()) {
                jpql.append(" AND LOWER(c.nombre) LIKE :nombre");
            }
            if (filtro.getVisitas() != null) { // Verifica si visitas NO es null
                jpql.append(" AND c.visitas >= :visitas"); // Buscar clientes con al menos X visitas
            }

            TypedQuery<ClienteFrecuente> query = em.createQuery(jpql.toString(), ClienteFrecuente.class)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH");

            if (filtro.getNombre() != null && !filtro.getNombre().trim().isEmpty()) {
                query.setParameter("nombre", "%" + filtro.getNombre().toLowerCase() + "%");
            }
            if (filtro.getVisitas() != null) {
                query.setParameter("visitas", filtro.getVisitas());
            }

            clientes = query.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al buscar Clientes Frecuentes: " + e.getMessage(), e);
        } finally {
            em.close();
        }

        return clientes;
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

    @Override
    public boolean existeTelefono(String telefono) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            Long count = em.createQuery(
                    "SELECT COUNT(c) FROM Cliente c WHERE c.telefono = :telefono", Long.class)
                    .setParameter("telefono", telefono)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            throw new PersistenciaException("Error al verificar teléfono: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Cliente> buscarClientes(Cliente clienteFiltro) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        List<Cliente> clientes = new ArrayList<>();

        try {
            em.getTransaction().begin(); // INICIAR TRANSACCIÓN
            // Asegurar que los cambios en la BD estén reflejados antes de la consulta
            em.flush();
            StringBuilder jpql = new StringBuilder("SELECT c FROM Cliente c WHERE 1=1");

            if (clienteFiltro.getNombre() != null && !clienteFiltro.getNombre().trim().isEmpty()) {
                jpql.append(" AND LOWER(c.nombre) LIKE :nombre");
            }
            if (clienteFiltro.getTelefono() != null && !clienteFiltro.getTelefono().trim().isEmpty()) {
                jpql.append(" AND c.telefono LIKE :telefono");
            }
            if (clienteFiltro.getCorreo() != null && !clienteFiltro.getCorreo().trim().isEmpty()) {
                jpql.append(" AND c.correo LIKE :correo");
            }

            TypedQuery<Cliente> query = em.createQuery(jpql.toString(), Cliente.class)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH"); // Evita caché esto lo tuve que hacer porque no encontraba los datos actualizados , se necesitaba refrescar la memoria.

            if (clienteFiltro.getNombre() != null && !clienteFiltro.getNombre().trim().isEmpty()) {
                query.setParameter("nombre", "%" + clienteFiltro.getNombre().toLowerCase() + "%");
            }
            if (clienteFiltro.getTelefono() != null && !clienteFiltro.getTelefono().trim().isEmpty()) {
                query.setParameter("telefono", "%" + clienteFiltro.getTelefono() + "%");
            }
            if (clienteFiltro.getCorreo() != null && !clienteFiltro.getCorreo().trim().isEmpty()) {
                query.setParameter("correo", "%" + clienteFiltro.getCorreo() + "%");
            }

            clientes = query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // REVERTIR CAMBIOS SI HAY ERROR
            }
            throw new PersistenciaException("Error al buscar clientes: " + e.getMessage(), e);
        } finally {
            em.close();
        }

        return clientes;
    }

}
