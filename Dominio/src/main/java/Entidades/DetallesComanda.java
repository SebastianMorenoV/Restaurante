/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Sebastian Moreno
 */
@Entity
@Table(name = "detallesComanda")
public class DetallesComanda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // atributo Producto aqui
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comanda_id", nullable = false) // revisar las cascadas.
    private Comanda comanda;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precioUnitario", nullable = false)
    private double precioUnitario;

    @Column(name = "comentarios", nullable = true, length = 250)
    private String comentarios;

    @Column(name = "importeTotal", nullable = false)
    private double importeTotal;

    public DetallesComanda() {
    }

    public DetallesComanda(Long id, Producto producto, Comanda comanda, Integer cantidad, double precioUnitario, String comentarios, double importeTotal) {
        this.id = id;
        this.producto = producto;
        this.comanda = comanda;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.comentarios = comentarios;
        this.importeTotal = importeTotal;
    }

    public DetallesComanda(Producto producto, Comanda comanda, Integer cantidad, double precioUnitario, String comentarios, double importeTotal) {
        this.producto = producto;
        this.comanda = comanda;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.comentarios = comentarios;
        this.importeTotal = importeTotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    
    
    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   @Override
public String toString() {
    return "DetallesComanda{" +
            "producto=" + (producto != null ? producto.getNombre() : "Producto no disponible") +
            ", cantidad=" + cantidad +
            ", total=" + importeTotal +
            '}';
}

}
