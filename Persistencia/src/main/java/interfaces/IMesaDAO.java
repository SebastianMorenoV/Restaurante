/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import Entidades.Mesa;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Sebastian Moreno.
 */
public interface IMesaDAO {
    
    public Mesa guardarMesa(Mesa mesa) throws PersistenciaException;
    
    public boolean eliminarMesa(Long numeroMesa) throws PersistenciaException;
    
    public List<Mesa> obtenerTodas() throws PersistenciaException;
    
    public Mesa buscarPorId(Long id) throws PersistenciaException;
    
}
