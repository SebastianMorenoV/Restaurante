/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOSalida;

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
    private List<IngredientesProductoDTO> ingredientes;

    public ProductoDTO() {
    }

    public ProductoDTO(Long id, String nombre, double precio, Tipo tipo, List<IngredientesProductoDTO> ingredientes) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.ingredientes = ingredientes;
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

    public List<IngredientesProductoDTO> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredientesProductoDTO> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
