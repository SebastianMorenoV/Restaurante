/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOEntrada.CrearComandaDTO;
import DTOSalida.ComandaDTO;
import Entidades.Comanda;
import Entidades.DetallesComanda;
import Entidades.Mesa;
import Enums.Estado;
import exception.NegocioException;
import exception.PersistenciaException;
import interfaces.IComandaBO;
import interfaces.IComandaDAO;
import interfaces.IMesaDAO;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ComandaBO implements IComandaBO {

    private IComandaDAO comandaDAO;
    private IMesaDAO mesaDAO;

    public ComandaBO(IComandaDAO comandaDAO) {
        this.comandaDAO = comandaDAO;
    }

    @Override
    public ComandaDTO registrarComanda(CrearComandaDTO comandaDTO) throws NegocioException {
        if (comandaDTO == null) {
            throw new NegocioException("La información de la comanda es obligatoria.");
        }

        try {
            // Crear entidad Comanda desde DTO
            Comanda comanda = new Comanda();
            comanda.setFechaHora(LocalDateTime.now());
            comanda.setEstado(Estado.Abierta); // esto se va a cambiar
            //comanda.setMesa(obtenerMesaPorId()); // buscar mesa como?
            double total = calcularTotal(comanda.getDetallesComanda());
            comanda.setTotalVenta(total);

            // Generar folio (opcional, puedes cambiarlo)
            comanda.setFolio("CMD-" + System.currentTimeMillis());

            // Registrar en base de datos
            Comanda comandaRegistrada = comandaDAO.registrarComanda(comanda);

            // Retornar DTO de salida
            return new ComandaDTO(
                    comandaRegistrada.getFolio(),
                    comandaRegistrada.getFechaHora(),
                    comandaRegistrada.getMesa().getNumMesa(),
                    comandaRegistrada.getEstado(),
                    comandaRegistrada.getTotalVenta()
            );

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar la comanda: " + e.getMessage(), e);
        }
    }

    //Auxiliares
    private double calcularTotal(List<DetallesComanda> detalles) {
        return detalles.stream()
                .mapToDouble(d -> d.getPrecioUnitario() * d.getCantidad())
                .sum();
    }

}
