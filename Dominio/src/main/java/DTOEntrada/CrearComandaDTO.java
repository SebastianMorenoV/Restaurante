/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOEntrada;

import Enums.Estado;
import java.time.LocalDateTime;

/**
 *
 * @author Admin
 */
public class CrearComandaDTO {
    private LocalDateTime fechaHora;
    private Integer numeroMesa;
    private Estado estado;
    private double totalVenta;

    public CrearComandaDTO() {
    }

    public CrearComandaDTO(LocalDateTime fechaHora, Integer numeroMesa, Estado estado, double totalVenta) {
        this.fechaHora = fechaHora;
        this.numeroMesa = numeroMesa;
        this.estado = estado;
        this.totalVenta = totalVenta;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }
    
    
}
