/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOSalida;

import java.time.LocalDate;

/**
 *
 * @author Sebastian Moreno
 */
public class ClienteDTO {

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String telefono;
    private Integer visitasTotales;
    private double totalGastado;
    private Integer puntos;
    private LocalDate ultimaComanda;
    
    public ClienteDTO() {
    }
    
    public ClienteDTO(String nombreCompleto, String correo, String telefono, int visitasTotales, double totalGastado, int puntos) {
        this.nombre = nombreCompleto;
        this.correo = correo;
        this.telefono = telefono;
        this.visitasTotales = visitasTotales;
        this.totalGastado = totalGastado;
        this.puntos = puntos;
    }

    public ClienteDTO(String nombreCompleto, String correo, String telefono) {
        this.nombre = nombreCompleto;
        this.correo = correo;
        this.telefono = telefono;
    }
    // constructor para reporte de clientes sin comanda
    public ClienteDTO(String nombreCompleto, int visitasTotales, double totalGastado, int puntos) {
        this.nombre = nombreCompleto;
        this.visitasTotales = visitasTotales;
        this.totalGastado = totalGastado;
        this.puntos = puntos;
    }

    public ClienteDTO(String nombreCompleto, Integer visitasTotales) {
        this.nombre = nombre;
        this.visitasTotales = visitasTotales;
    }
    
    public ClienteDTO(String nombreCompleto, String correo, String telefono, int visitasTotales, double totalGastado, int puntos, LocalDate ultimaComanda) {
        this.nombre = nombreCompleto;
        this.correo = correo;
        this.telefono = telefono;
        this.visitasTotales = visitasTotales;
        this.totalGastado = totalGastado;
        this.puntos = puntos;
        this.ultimaComanda = ultimaComanda;
    }

    public LocalDate getUltimaComanda() {
        return ultimaComanda;
    }

    public void setUltimaComanda(LocalDate ultimaComanda) {
        this.ultimaComanda = ultimaComanda;
    }

    public String getNombreCompleto() {
        return nombre;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombre = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getVisitasTotales() {
        return visitasTotales;
    }

    public void setVisitasTotales(int visitasTotales) {
        this.visitasTotales = visitasTotales;
    }

    public double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(double totalGastado) {
        this.totalGastado = totalGastado;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

}
