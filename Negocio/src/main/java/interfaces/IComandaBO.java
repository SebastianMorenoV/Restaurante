/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOEntrada.CrearComandaDTO;
import DTOSalida.ComandaDTO;
import Enums.Estado;
import exception.NegocioException;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IComandaBO {
    public ComandaDTO registrarComanda(CrearComandaDTO comandaDTO)throws NegocioException;
    
    public List<ComandaDTO> obtenerComandas() throws NegocioException;
    
    public ComandaDTO actualizarComanda(ComandaDTO comandaDTO) throws NegocioException ;
    
    public ComandaDTO buscarComandaPorFolio(String folio) throws NegocioException;
    
}
