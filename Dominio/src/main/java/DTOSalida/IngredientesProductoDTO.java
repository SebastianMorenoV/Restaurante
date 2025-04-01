/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOSalida;

import Entidades.Ingrediente;
import Entidades.Producto;

/**
 *
 * @author Admin
 */
public class IngredientesProductoDTO {

    private Long id;
    private Integer cantidad;
    private Ingrediente ingrediente;
    private Producto producto;

    public IngredientesProductoDTO() {
    }

    public IngredientesProductoDTO(Long id, Integer cantidad, Ingrediente ingrediente, Producto producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.ingrediente = ingrediente;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "IngredientesProductoDTO{" + "id=" + id + ", cantidad=" + cantidad + ", ingrediente=" + ingrediente + ", producto=" + producto + '}';
    }

}
