/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import Entidades.DetallesComanda;
import exception.PersistenciaException;

/**
 *
 * @author Sebastian Moreno
 */
public interface IDetallesComandaDAO {
    public DetallesComanda guardarDetallesComanda(DetallesComanda detalle)throws PersistenciaException;
}
