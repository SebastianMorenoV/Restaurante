/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Sebastian Moreno
 */
@Entity
@DiscriminatorValue(value = "Frecuente")
@Table(name = "ClientesFrecuentes")
public class ClienteFrecuente extends Cliente {
    @Column(name = "visitas" , nullable = false) // transient?
    private Integer visitas;
    
    @Column(name = "gastoAcumulado" , nullable = false)
    private double gastoAcumulado;
    
    @Column(name = "puntosFidelidad" , nullable = false)
    private Integer puntosFidelidad;

    public ClienteFrecuente() {
    }

   
    
    
    //insertar constructores con apellido materno y paterno.
   

    public ClienteFrecuente(Integer visitas, double gastoAcumulado, Integer puntosFidelidad, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, LocalDate fechaRegistro) {
        super(nombre, apellidoPaterno, apellidoMaterno, telefono, correo, fechaRegistro);
        this.visitas = visitas;
        this.gastoAcumulado = gastoAcumulado;
        this.puntosFidelidad = puntosFidelidad;
    }
    
    

    public double getGastoAcumulado() {
        return gastoAcumulado;
    }

    public void setGastoAcumulado(double gastoAcumulado) {
        this.gastoAcumulado = gastoAcumulado;
    }

    public Integer getVisitas() {
        return visitas;
    }

    public void setVisitas(Integer visitas) {
        this.visitas = visitas;
    }

    public Integer getPuntosFidelidad() {
        return puntosFidelidad;
    }

    public void setPuntosFidelidad(Integer puntosFidelidad) {
        this.puntosFidelidad = puntosFidelidad;
    }

    

    @Override
    public String toString() {
        return "ClienteFrecuente{" + "visitas=" + visitas + ", gastoAcumulado=" + gastoAcumulado + ", puntosFidelidad=" + puntosFidelidad + '}';
    }
    
    

}
