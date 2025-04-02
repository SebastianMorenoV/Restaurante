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
    @Column(name = "visitas" , nullable = false)
    private int visitas;
    
    @Column(name = "gastoAcumulado" , nullable = false)
    private double gastoAcumulado;
    
    @Column(name = "puntosFidelidad" , nullable = false)
    private int puntosFidelidad;

    public ClienteFrecuente() {
    }

    public ClienteFrecuente(int visitas, double gastoAcumulado, int puntosFidelidad, String nombre, String telefono, String correo, LocalDate fechaRegistro) {
        super(nombre, telefono, correo, fechaRegistro);
        this.visitas = visitas;
        this.gastoAcumulado = gastoAcumulado;
        this.puntosFidelidad = puntosFidelidad;
    }

    public ClienteFrecuente(int visitas, double gastoAcumulado, int puntosFidelidad, Long id, String nombre, String telefono, String correo, LocalDate fechaRegistro, List<Comanda> comandas) {
        super(id, nombre, telefono, correo, fechaRegistro, comandas);
        this.visitas = visitas;
        this.gastoAcumulado = gastoAcumulado;
        this.puntosFidelidad = puntosFidelidad;
    }

    public int getVisitas() {
        return visitas;
    }

    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }

    public double getGastoAcumulado() {
        return gastoAcumulado;
    }

    public void setGastoAcumulado(double gastoAcumulado) {
        this.gastoAcumulado = gastoAcumulado;
    }

    public int getPuntosFidelidad() {
        return puntosFidelidad;
    }

    public void setPuntosFidelidad(int puntosFidelidad) {
        this.puntosFidelidad = puntosFidelidad;
    }

    @Override
    public String toString() {
        return "ClienteFrecuente{" + "visitas=" + visitas + ", gastoAcumulado=" + gastoAcumulado + ", puntosFidelidad=" + puntosFidelidad + '}';
    }
    
    

}
