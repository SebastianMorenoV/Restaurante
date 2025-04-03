/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOSalida;

import Enums.UnidadMedida;

/**
 *
 * @author Admin
 */
public class IngredienteDTO {

    private Long id;
    private String nombre;
    private Integer stock;
    private UnidadMedida unidadMedida;

    public IngredienteDTO() {
    }

    public IngredienteDTO(Long id, String nombre, Integer stock, UnidadMedida unidadMedida) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.unidadMedida = unidadMedida;
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

    @Override
    public String toString() {
        return "IngredienteDTO{" + "id=" + id + ", nombre=" + nombre + ", stock=" + stock + ", unidadMedida=" + unidadMedida + '}';
    }

}
