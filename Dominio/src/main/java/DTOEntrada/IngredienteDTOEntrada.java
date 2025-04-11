/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOEntrada;

import Enums.UnidadMedida;

/**
 *
 * @author SDavidLedesma
 */
public class IngredienteDTOEntrada {

    private Long id;
    private String nombre;
    private Integer stock;
    private UnidadMedida unidadMedida;
    private Integer cantidad;

    public IngredienteDTOEntrada() {
    }

    public IngredienteDTOEntrada(String nombre, Integer stock, UnidadMedida unidadMedida, Integer cantidad) {
        this.nombre = nombre;
        this.stock = stock;
        this.unidadMedida = unidadMedida;
        this.cantidad = cantidad;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

}
