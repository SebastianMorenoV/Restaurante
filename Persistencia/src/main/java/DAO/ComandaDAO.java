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

    public ComandaDAO() {
    }
    
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
    
    
}
