/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Enums.Estado;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Sebastian Moreno
 */
public class Comanda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaHora")
    private LocalDateTime fechaHora;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name = "totalVenta", nullable = true)
    private double totalVenta;

    @OneToMany(mappedBy = "comanda")
    private List<DetallesComanda> detallesComanda;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "numeroMesa")
    private Mesa numMesa;

    public Comanda() {
    }

    public Comanda(Long id, LocalDateTime fechaHora, Estado estado, double totalVenta, List<DetallesComanda> detallesComanda, Cliente cliente, Mesa numMesa) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.totalVenta = totalVenta;
        this.detallesComanda = detallesComanda;
        this.cliente = cliente;
        this.numMesa = numMesa;
    }

    public Comanda(LocalDateTime fechaHora, Estado estado, double totalVenta, List<DetallesComanda> detallesComanda, Cliente cliente, Mesa numMesa) {
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.totalVenta = totalVenta;
        this.detallesComanda = detallesComanda;
        this.cliente = cliente;
        this.numMesa = numMesa;
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

    public Mesa getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(Mesa numMesa) {
        this.numMesa = numMesa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Comanda{" + "id=" + id + ", fechaHora=" + fechaHora + ", estado=" + estado + ", totalVenta=" + totalVenta + ", detallesComanda=" + detallesComanda + ", cliente=" + cliente + ", numMesa=" + numMesa + '}';
    }

}
