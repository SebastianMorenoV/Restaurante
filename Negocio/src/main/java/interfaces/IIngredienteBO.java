/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOEntrada.CrearIngredienteDTO;
import DTOSalida.IngredienteDTO;
import exception.NegocioException;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IIngredienteBO {
    public IngredienteDTO agregarIngrediente(CrearIngredienteDTO ingredienteDTO) throws NegocioException;
    
    public IngredienteDTO actualizarPorID(Long id, int nuevoStock) throws NegocioException;
    
    public void eliminarIngrediente(Long id) throws NegocioException;
    
    public List<IngredienteDTO> obtenerTodos() throws NegocioException;
    
    public IngredienteDTO obtenerPorId(Long id) throws NegocioException ;
    
}
