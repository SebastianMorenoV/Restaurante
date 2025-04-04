/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Enums.ProductoActivo;
import Enums.Tipo;
import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "Productos", uniqueConstraints = @UniqueConstraint(columnNames = {"nombre"})) // indica que solo un producto puede tener un nombre y no repetirse
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "precio")
    private double precio;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column(name = "Habilitado_Deshabilitado")
    @Enumerated(EnumType.STRING)
    private ProductoActivo productoActivo;

    @OneToMany(mappedBy = "producto", cascade = {/**
         * CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, *
         */
        CascadeType.REMOVE}, orphanRemoval = true /**
     * , fetch = FetchType.LAZY*
     */
    )
    private List<IngredientesProducto> ingredientes;

    @OneToMany(mappedBy = "producto") // revisar cascadas y orphan removable y fetch /*PUEDE SER UNIDIRECCIO NAL DICE LA PROFRE BRO😃*/
    private List<DetallesComanda> detallesComanda;

    public Producto() {
    }

    public Producto(String nombre, double precio, Tipo tipo, ProductoActivo productoActivo, List<IngredientesProducto> ingredientes, List<DetallesComanda> detallesComanda) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.productoActivo = productoActivo;
        this.ingredientes = ingredientes;
        this.detallesComanda = detallesComanda;
    }

    public Producto(String nombre, double precio, Tipo tipo, ProductoActivo productoActivo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.productoActivo = productoActivo;
    }

    public Producto(String nombre, double precio, Tipo tipo, ProductoActivo productoActivo, List<IngredientesProducto> ingredientes) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.productoActivo = productoActivo;
        this.ingredientes = ingredientes;
    }

    public Producto(Long id, String nombre, double precio, Tipo tipo, ProductoActivo productoActivo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.productoActivo = productoActivo;
    }

    public ProductoActivo getProductoActivo() {
        return productoActivo;
    }

    public void setProductoActivo(ProductoActivo productoActivo) {
        this.productoActivo = productoActivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public List<IngredientesProducto> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredientesProducto> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<DetallesComanda> getDetallesComanda() {
        return detallesComanda;
    }

    public void setDetallesComanda(List<DetallesComanda> detallesComanda) {
        this.detallesComanda = detallesComanda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Producto[ id=" + id + " ]";
    }

}
