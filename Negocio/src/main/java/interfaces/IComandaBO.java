/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOEntrada.CrearComandaDTO;
import DTOSalida.ClienteDTO;
import DTOSalida.ComandaDTO;
import DTOSalida.FiltroComandaDTO;
import DTOSalida.ProductoDTO;
import Entidades.DetallesComanda;
import Enums.Estado;
import exception.NegocioException;
import java.time.LocalDateTime;
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
    
    public List<ComandaDTO> buscarComandas(FiltroComandaDTO filtro) throws NegocioException;
    
    public List<ComandaDTO> buscarComandasPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws NegocioException;
    
    public String obtenerDetallesComandaPorFolio(String folio) throws NegocioException;
    
    public List<ComandaDTO> obtenerUltimaComandaClientes(List<ClienteDTO> clientesDTO) throws NegocioException;
    
    public CrearComandaDTO obtenerComandaActiva(String folio) throws NegocioException;
    

    
}
