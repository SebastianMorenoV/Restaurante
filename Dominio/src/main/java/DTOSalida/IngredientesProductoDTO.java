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
    private IngredienteDTO ingrediente;
    private ProductoDTO producto;

    public IngredientesProductoDTO() {
    }

    public IngredientesProductoDTO(Integer cantidad, IngredienteDTO ingrediente, ProductoDTO producto) {
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

    public IngredienteDTO getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(IngredienteDTO ingrediente) {
        this.ingrediente = ingrediente;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "IngredientesProductoDTO{" + "id=" + id + ", cantidad=" + cantidad + ", ingrediente=" + ingrediente + ", producto=" + producto + '}';
    }

}
