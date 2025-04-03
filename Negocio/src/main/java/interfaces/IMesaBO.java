/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOSalida.MesaDTO;
import exception.NegocioException;
import java.util.List;

/**
 *
 * @author Sebastian Moreno
 */
public interface IMesaBO {
    
    public boolean insertMasivoMesas() throws NegocioException;
    
    public List<MesaDTO> obtenerTodas() throws NegocioException;
}
