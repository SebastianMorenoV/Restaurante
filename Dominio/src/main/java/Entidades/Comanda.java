/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Enums.Estado;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sebastian Moreno
 */

@Entity
@Table(name = "Comandas")
public class Comanda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name ="folio", nullable = false, length = 15)
    private String folio;

    @Column(name = "fechaHora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name = "totalVenta", nullable = true)
    private double totalVenta;

    @OneToMany(mappedBy = "comanda" , cascade = {CascadeType.REMOVE, CascadeType.MERGE , CascadeType.PERSIST} , orphanRemoval = true,fetch = FetchType.EAGER) 
    private List<DetallesComanda> detallesComanda = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_mesa")
    private Mesa mesa;

    public Comanda() {
    }

    public Comanda(Long id, String folio, LocalDateTime fechaHora, Estado estado, double totalVenta, List<DetallesComanda> detallesComanda, Cliente cliente, Mesa mesa) {
        this.id = id;
        this.folio = folio;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.totalVenta = totalVenta;
        this.detallesComanda = detallesComanda;
        this.cliente = cliente;
        this.mesa = mesa;
    }

    public Comanda(LocalDateTime fechaHora, Estado estado, double totalVenta, Mesa mesa) {
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.totalVenta = totalVenta;
        this.mesa = mesa;
    }

    public Comanda(String folio, LocalDateTime fechaHora, Estado estado, double totalVenta, List<DetallesComanda> detallesComanda, Cliente cliente, Mesa mesa) {
        this.folio = folio;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.totalVenta = totalVenta;
        this.detallesComanda = detallesComanda;
        this.cliente = cliente;
        this.mesa = mesa;
    }

    

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    @Override
    public String toString() {
        return "Comanda{" + "id=" + id + ", folio=" + folio + ", fechaHora=" + fechaHora + ", estado=" + estado + ", totalVenta=" + totalVenta + ", detallesComanda=" + detallesComanda + ", cliente=" + cliente + ", mesa=" + mesa + '}';
    }



    

}
