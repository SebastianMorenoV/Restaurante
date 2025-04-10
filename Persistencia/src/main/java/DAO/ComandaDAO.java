/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTOSalida.FiltroComandaDTO;
import interfaces.IComandaDAO;
import Entidades.Comanda;
import Entidades.DetallesComanda;
import Enums.Estado;
import conexion.Conexion;
import exception.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
    
    @Override
    public List<Comanda> buscarComandas(FiltroComandaDTO filtro) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        List<Comanda> comandas = new ArrayList<>();

        try {
            em.getTransaction().begin();
            em.flush();

            StringBuilder jpql = new StringBuilder("SELECT c FROM Comanda c LEFT JOIN FETCH c.cliente WHERE 1=1");


            if (filtro.getFolio() != null && !filtro.getFolio().trim().isEmpty()) {
                jpql.append(" AND LOWER(c.folio) LIKE :folio");
            }

            if (filtro.getEstado() != null) {
                jpql.append(" AND c.estado = :estado");
            }

            if (filtro.getFechaInicio() != null) {
                jpql.append(" AND c.fechaHora >= :fechaInicio");
            }

            if (filtro.getFechaFin() != null) {
                jpql.append(" AND c.fechaHora <= :fechaFin");
            }

            TypedQuery<Comanda> query = em.createQuery(jpql.toString(), Comanda.class)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH");

            if (filtro.getFolio() != null && !filtro.getFolio().trim().isEmpty()) {
                query.setParameter("folio", "%" + filtro.getFolio().toLowerCase() + "%");
            }

            if (filtro.getEstado() != null) {
                query.setParameter("estado", filtro.getEstado());
            }

            if (filtro.getFechaInicio() != null) {
                query.setParameter("fechaInicio", filtro.getFechaInicio());
            }

            if (filtro.getFechaFin() != null) {
                query.setParameter("fechaFin", filtro.getFechaFin());
            }

            comandas = query.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al buscar comandas: " + e.getMessage(), e);
        } finally {
            em.close();
        }

        return comandas;
    }
    
    @Override
    public String obtenerDetallesComanda(Comanda comanda) {
        StringBuilder detalles = new StringBuilder();

        // Verificar si la comanda tiene detalles
        if (comanda != null && comanda.getDetallesComanda() != null) {
            for (DetallesComanda detalle : comanda.getDetallesComanda()) {
                detalles.append(detalle.getComentarios()).append(" ");  // Asumiendo que DetallesComanda tiene un método getComentarios()
            }
        }

        return detalles.toString().trim();  // Trim para eliminar cualquier espacio extra al final
    }




    
    

}
