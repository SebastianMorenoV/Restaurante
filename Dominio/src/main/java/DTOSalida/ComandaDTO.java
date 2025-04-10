/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOSalida;

import Entidades.DetallesComanda;
import Enums.Estado;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Sebastian Moreno
 */
public class ComandaDTO {

    private String folio;
    private LocalDateTime fechaHora;
    private Integer numeroMesa;
    private Estado estado;
    private double totalVenta;
    private List<DetallesComanda> detallesComanda;
    private ClienteDTO cliente;

    
    
    public ComandaDTO(String folio, LocalDateTime fechaHora, Integer numeroMesa, Estado estado, double totalVenta) {
        this.folio = folio;
        this.fechaHora = fechaHora;
        this.numeroMesa = numeroMesa;
        this.estado = estado;
        this.totalVenta = totalVenta;
    }

    public ComandaDTO(String folio, LocalDateTime fechaHora, Integer numeroMesa, Estado estado, double totalVenta, List<DetallesComanda> detallesComanda) {
        this.folio = folio;
        this.fechaHora = fechaHora;
        this.numeroMesa = numeroMesa;
        this.estado = estado;
        this.totalVenta = totalVenta;
        this.detallesComanda = detallesComanda;
    }

    public ComandaDTO(String folio, LocalDateTime fechaHora, Integer numeroMesa, Estado estado, double totalVenta, ClienteDTO cliente) {
        this.folio = folio;
        this.fechaHora = fechaHora;
        this.numeroMesa = numeroMesa;
        this.estado = estado;
        this.totalVenta = totalVenta;
        this.cliente = cliente;
    }

    
    
    

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
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

    public List<DetallesComanda> getDetallesComanda() {
        return detallesComanda;
    }

    public void setDetallesComanda(List<DetallesComanda> detallesComanda) {
        this.detallesComanda = detallesComanda;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
    
    

    @Override
    public String toString() {
        return "ComandaDTO{" + "folio=" + folio + ", fechaHora=" + fechaHora + ", numeroMesa=" + numeroMesa + ", estado=" + estado + ", totalVenta=" + totalVenta + '}';
    }
    
    
    
    

}
