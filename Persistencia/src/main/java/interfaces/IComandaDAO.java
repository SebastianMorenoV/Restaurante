/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOSalida.FiltroComandaDTO;
import Entidades.Cliente;
import Entidades.Comanda;
import Entidades.DetallesComanda;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public interface IComandaDAO {

    public Comanda obtenerComandaPorId(Long id) throws PersistenciaException;

    public Comanda registrarComanda(Comanda comanda) throws PersistenciaException;

    public Comanda actualizarComanda(Comanda comandaActualizar) throws PersistenciaException;

    public int obtenerUltimoConsecutivo() throws PersistenciaException;

    public List<Comanda> obtenerComandasAbiertas() throws PersistenciaException;

    public Comanda buscarComandaPorFolio(String folio) throws PersistenciaException;

    public List<Comanda> buscarComandas(FiltroComandaDTO filtro) throws PersistenciaException;

    public String obtenerDetallesComanda(Comanda comanda);
    
    public Comanda obtenerUltimaComandaCliente(Long idCliente)throws PersistenciaException;

}
