/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOEntrada.CrearComandaDTO;
import DTOSalida.ComandaDTO;
import exception.NegocioException;

/**
 *
 * @author Admin
 */
public interface IComandaBO {
    public ComandaDTO registrarComanda(CrearComandaDTO comandaDTO)throws NegocioException;
}
