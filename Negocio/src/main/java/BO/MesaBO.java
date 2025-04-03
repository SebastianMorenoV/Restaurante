/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOSalida.MesaDTO;
import Entidades.Mesa;
import Enums.EstadoMesa;
import exception.NegocioException;
import exception.PersistenciaException;
import interfaces.IMesaBO;
import interfaces.IMesaDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Sebastian Moreno
 */
public class MesaBO implements IMesaBO {

    private IMesaDAO mesaDAO;

    public MesaBO(IMesaDAO mesaDAO) {
        this.mesaDAO = mesaDAO;
    }

    @Override
    public boolean insertMasivoMesas() throws NegocioException {
        try {
            for (int i = 1; i <= 20; i++) {
                Mesa mesa = new Mesa(i, EstadoMesa.Desocupada);
                mesaDAO.guardarMesa(mesa);
            }
            return true; // Retorna true si todas las inserciones fueron exitosas
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al insertar las mesas: " + e.getMessage());
        }
    }

    @Override
    public List<MesaDTO> obtenerTodas() throws NegocioException {
        try {
            List<Mesa> mesas = mesaDAO.obtenerTodas();

            // Convertimos cada Mesa a MesaDTO usando un stream
            return mesas.stream()
                    .map(mesa -> new MesaDTO(mesa.getNumMesa())) // Solo se usa el número de mesa
                    .collect(Collectors.toList());

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar las mesas: " + ex.getMessage());
        }

    }

}
