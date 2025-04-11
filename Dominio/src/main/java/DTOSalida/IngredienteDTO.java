/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOSalida;

import Enums.UnidadMedida;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class IngredienteDTO {

    private Long id;
    private String nombre;
    private Integer stock;
    private UnidadMedida unidadMedida;
    private Integer cantidad;

    public IngredienteDTO() {
    }

    public IngredienteDTO(Long id, String nombre, Integer stock, UnidadMedida unidadMedida) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.unidadMedida = unidadMedida;
    }

    public IngredienteDTO(Long id, String nombre, Integer stock, UnidadMedida unidadMedida, Integer cantidad) {
        this.id = id;
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
    
    
     @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Si son la misma instancia, son iguales
        if (obj == null || getClass() != obj.getClass()) return false; // Si son de clases diferentes, no son iguales
        IngredienteDTO that = (IngredienteDTO) obj;
        // Comparar los atributos relevantes para la igualdad
        return Objects.equals(id, that.id) && // Compara el id
               Objects.equals(nombre, that.nombre) && // Compara el nombre
               Objects.equals(unidadMedida, that.unidadMedida); // Compara la unidadMedida
    }

    @Override
    public int hashCode() {
        // El hashCode debe generar un valor único para cada objeto basándose en los mismos atributos de `equals`
        return Objects.hash(id, nombre, unidadMedida);
    }

    @Override
    public String toString() {
        return "IngredienteDTO{" + "id=" + id + ", nombre=" + nombre + ", stock=" + stock + ", unidadMedida=" + unidadMedida + '}';
    }

}
