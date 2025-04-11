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
    private List<IngredientesProductoDTO> ingredienteProducto;
    private List<DetallesComandaDTO> detalleComandas;

    public ProductoDTO() {
    }

    public ProductoDTO(String nombre, double precio, Tipo tipo, ProductoActivo productoActivo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.productoActivo = productoActivo;
    }

    public ProductoDTO(Long id, String nombre, double precio, Tipo tipo, ProductoActivo productoActivo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.productoActivo = productoActivo;
    }

    public ProductoDTO(String nombre, double precio, Tipo tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    public ProductoDTO(Long id, String nombre, double precio, Tipo tipo, ProductoActivo productoActivo, List<IngredientesProductoDTO> ingredienteProducto, List<DetallesComandaDTO> detalleComandas) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.productoActivo = productoActivo;
        this.ingredienteProducto = ingredienteProducto;
        this.detalleComandas = detalleComandas;
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

    public ProductoActivo getProductoActivo() {
        return productoActivo;
    }

    public void setProductoActivo(ProductoActivo productoActivo) {
        this.productoActivo = productoActivo;
    }

    public List<IngredientesProductoDTO> getIngredienteProducto() {
        return ingredienteProducto;
    }

    public void setIngredienteProducto(List<IngredientesProductoDTO> ingredienteProducto) {
        this.ingredienteProducto = ingredienteProducto;
    }   

    public List<DetallesComandaDTO> getDetalleComandas() {
        return detalleComandas;
    }

    public void setDetalleComandas(List<DetallesComandaDTO> detalleComandas) {
        this.detalleComandas = detalleComandas;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", tipo=" + tipo + ", productoActivo=" + productoActivo + ", ingredienteProducto=" + ingredienteProducto + ", detalleComandas=" + detalleComandas + '}';
    }

}
