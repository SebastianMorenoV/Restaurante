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
import java.time.format.DateTimeFormatter;
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
            Comanda comanda = new Comanda();
            comanda.setFechaHora(LocalDateTime.now());
            comanda.setEstado(comandaDTO.getEstado()); // esto se va a cambiar
            Mesa mesa = new Mesa();
            mesa.setId(comandaDTO.getNumeroMesa().longValue());
            mesa.setNumMesa(comandaDTO.getNumeroMesa());
            comanda.setMesa(mesa);
            //Setea la comanda inicialmente en 0
            comanda.setTotalVenta(0.00);

            int numeroConsecutivo = obtenerSiguienteNumeroConsecutivo();
            String folio = generarFolio(LocalDateTime.now(),numeroConsecutivo);
            System.out.println(folio);
            
            comanda.setFolio(folio);

            // Registrar en la bd
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

    private String generarFolio(LocalDateTime fecha, int numeroConsecutivo) {
        // Formatear la fecha como YYYYMMDD
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String fechaFormateada = fecha.format(formatter);

        // Formatear el número consecutivo a tres dígitos con ceros a la izquierda
        String consecutivoFormateado = String.format("%03d", numeroConsecutivo);

        // Combinar los valores para formar el folio
        return "OB-" + fechaFormateada + "-" + consecutivoFormateado;
    }
    
    private int obtenerSiguienteNumeroConsecutivo() throws PersistenciaException {
        // Lógica para obtener el número consecutivo único desde la base de datos o almacenamiento
        // Ejemplo simplificado:
        int ultimoNumero = comandaDAO.obtenerUltimoConsecutivo(); // Recuperar el último número usado
        return ultimoNumero + 1;
    }




}
