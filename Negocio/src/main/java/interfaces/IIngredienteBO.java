/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOEntrada.CrearIngredienteDTO;
import DTOSalida.IngredienteDTO;
import exception.NegocioException;

/**
 *
 * @author Admin
 */
public interface IIngredienteBO {
    public IngredienteDTO agregarIngrediente(CrearIngredienteDTO ingredienteDTO) throws NegocioException;
}
