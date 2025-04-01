/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Sebastian Moreno
 */
@Entity
public class DetallesComanda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // atributo Producto aqui
    @ManyToOne
    @JoinColumn(name = "comanda_id", nullable = false) // revisar las cascadas.
    private Comanda comanda;
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
    @Column(name = "precioUnitario", nullable = false)
    private double precioUnitario;
    @Column(name = "comentarios", nullable = true)
    private String comentarios;
    @Column(name = "importeTotal", nullable = true)
    private double importeTotal;

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Entidades.DetallesComanda[ id=" + id + " ]";
    }

}
