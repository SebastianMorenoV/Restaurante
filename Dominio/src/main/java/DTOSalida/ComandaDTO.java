/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOSalida;

import java.util.Date;

/**
 *
 * @author Sebastian Moreno
 */
public class ComandaDTO {

    private String folio;
    private Date fechaHora;
    private Integer numeroMesa;
    private Enum estado;
    private double totalVenta;

    public ComandaDTO(String folio, Date fechaHora, Integer numeroMesa, Enum estado, double totalVenta) {
        this.folio = folio;
        this.fechaHora = fechaHora;
        this.numeroMesa = numeroMesa;
        this.estado = estado;
        this.totalVenta = totalVenta;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Enum getEstado() {
        return estado;
    }

    public void setEstado(Enum estado) {
        this.estado = estado;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    @Override
    public String toString() {
        return "ComandaDTO{" + "folio=" + folio + ", fechaHora=" + fechaHora + ", numeroMesa=" + numeroMesa + ", estado=" + estado + ", totalVenta=" + totalVenta + '}';
    }
    
    
    
    

}
