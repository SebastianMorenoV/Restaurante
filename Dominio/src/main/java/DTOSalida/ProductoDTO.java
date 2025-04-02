/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOSalida;

import Enums.ProductoActivo;
import Enums.Tipo;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ProductoDTO {

    private Long id;
    private String nombre;
    private double precio;
    private Tipo tipo;
    private ProductoActivo productoActivo;
    private List<IngredienteDTO> ingredientes;
    private List<ComandaDTO> comandas;

    public ProductoDTO() {
    }

    public ProductoDTO(String nombre, double precio, Tipo tipo, ProductoActivo productoActivo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.productoActivo = productoActivo;
    }

    public ProductoDTO(Long id, String nombre, double precio, Tipo tipo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    public ProductoDTO(Long id, String nombre, double precio, Tipo tipo, ProductoActivo productoActivo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.productoActivo = productoActivo;
    }

    public ProductoDTO(String nombre, double precio, Tipo tipo, ProductoActivo productoActivo, List<IngredienteDTO> ingredientes, List<ComandaDTO> comandas) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.productoActivo = productoActivo;
        this.ingredientes = ingredientes;
        this.comandas = comandas;
    }

    public List<IngredienteDTO> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteDTO> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<ComandaDTO> getComandas() {
        return comandas;
    }

    public void setComandas(List<ComandaDTO> comandas) {
        this.comandas = comandas;
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

    @Override
    public String toString() {
        return "ProductoDTO{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", tipo=" + tipo + '}';
    }

}
