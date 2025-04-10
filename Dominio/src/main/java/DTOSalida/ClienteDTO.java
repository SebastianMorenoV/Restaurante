/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOSalida;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 *
 * @author Sebastian Moreno
 */
public class ClienteDTO {

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String telefono;
    private Integer visitasTotales;
    private double totalGastado;
    private Integer puntos;
    private LocalDateTime ultimaComanda;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nombre, Integer visitasTotales, double totalGastado, Integer puntos) {
        this.id = id;
        this.nombre = nombre;
        this.visitasTotales = visitasTotales;
        this.totalGastado = totalGastado;
        this.puntos = puntos;
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

    // constructor auxiliar para la creacion de comanda.    
    public ClienteDTO(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public ClienteDTO(String nombreCompleto, Integer visitasTotales) {
        this.nombre = nombre;
        this.visitasTotales = visitasTotales;
    }

    public ClienteDTO(String nombreCompleto, String correo, String telefono, int visitasTotales, double totalGastado, int puntos, LocalDateTime ultimaComanda) {
        this.nombre = nombreCompleto;
        this.correo = correo;
        this.telefono = telefono;
        this.visitasTotales = visitasTotales;
        this.totalGastado = totalGastado;
        this.puntos = puntos;
        this.ultimaComanda = ultimaComanda;
    }

    public ClienteDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String telefono, Integer visitasTotales, double totalGastado, Integer puntos) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.telefono = telefono;
        this.visitasTotales = visitasTotales;
        this.totalGastado = totalGastado;
        this.puntos = puntos;
    }

    public ClienteDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String telefono) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getUltimaComanda() {
        return ultimaComanda;
    }

    public String getUltimaComandaFormateada() {
        if (ultimaComanda != null) {
            java.util.Date date = java.util.Date.from(ultimaComanda.atZone(java.time.ZoneId.systemDefault()).toInstant());
            return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date);
        }
        return "";
    }

    public void setUltimaComanda(LocalDateTime ultimaComanda) {
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" + "nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", correo=" + correo + ", telefono=" + telefono + ", visitasTotales=" + visitasTotales + ", totalGastado=" + totalGastado + ", puntos=" + puntos + ", ultimaComanda=" + ultimaComanda + '}';
    }

}
