/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOSalida;

/**
 *
 * @author Sebastian Moreno
 */
public class ClienteDTO {
    private String nombreCompleto;
    private String correo;
    private String telefono;
    private int visitasTotales;
    private double totalGastado;
    private int puntos;

    public ClienteDTO() {
    }

    public ClienteDTO(String nombreCompleto, String correo, String telefono, int visitasTotales, double totalGastado, int puntos) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.telefono = telefono;
        this.visitasTotales = visitasTotales;
        this.totalGastado = totalGastado;
        this.puntos = puntos;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
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
