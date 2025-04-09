/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import Entidades.Comanda;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public interface IComandaDAO {
    
     public Comanda obtenerComandaPorId(Long id) throws PersistenciaException;
     
     public Comanda registrarComanda(Comanda comanda)throws PersistenciaException;
     
     public Comanda actualizarComanda(Comanda comandaActualizar)throws PersistenciaException;
     
     public int obtenerUltimoConsecutivo() throws PersistenciaException;    
     
     public List<Comanda> obtenerComandasAbiertas() throws PersistenciaException;
     
     public Comanda buscarComandaPorFolio(String folio) throws PersistenciaException;
     
     
}
