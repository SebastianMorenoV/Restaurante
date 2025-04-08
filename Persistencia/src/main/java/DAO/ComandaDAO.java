/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import interfaces.IComandaDAO;
import Entidades.Comanda;
import conexion.Conexion;
import exception.PersistenciaException;
import javax.persistence.EntityManager;

/**
 *
 * @author SDavidLedesma
 */
public class ComandaDAO implements IComandaDAO {
    
    public static ComandaDAO instanceComandaDAO;
    
        public static ComandaDAO getInstanceDAO() {
        if (instanceComandaDAO == null) {
            instanceComandaDAO  = new ComandaDAO();
        }
        return instanceComandaDAO;
    }

    public ComandaDAO() {
    }
    
    @Override
    public Comanda obtenerComandaPorId(Long id) throws PersistenciaException{
        EntityManager em = Conexion.crearConexion();
        
        try{
            return em.find(Comanda.class, id);
        }catch(Exception e){
            throw new PersistenciaException("Error al buscar Comanda por ID: "+e.getMessage());
        }finally {
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
            if(comanda.getId()==null){
                throw new PersistenciaException("Error no se genero un id para la comanda");
            }
            return comanda;
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar Comanda por ID: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Comanda actualizarComanda(Comanda comandaActualizar) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //Metodos auxiliares
    //metodo para crear el formato del folio
    
    
}
