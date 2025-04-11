/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOEntrada;

import DTOSalida.ClienteDTO;
import DTOSalida.DetallesComandaDTO;
import DTOSalida.ProductoDTO;
import Enums.Estado;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CrearComandaDTO {
    private LocalDateTime fechaHora;
    private Integer numeroMesa;
    private Estado estado;
    private double totalVenta;
    private ClienteDTO cliente;
    private List<ProductoDTO> productosComanda = new ArrayList<>();
    private List<DetallesComandaDTO> detallesComanda = new ArrayList<>();

    public CrearComandaDTO() {
    }
    
    
    public CrearComandaDTO(LocalDateTime fechaHora, Integer numeroMesa, Estado estado, double totalVenta) {
        this.fechaHora = fechaHora;
        this.numeroMesa = numeroMesa;
        this.estado = estado;
        this.totalVenta = totalVenta;
    }
    
    // para crear comanda con cliente y productos.

    public CrearComandaDTO(LocalDateTime fechaHora, Integer numeroMesa, Estado estado, double totalVenta, ClienteDTO cliente, List<ProductoDTO> productosComanda) {
        this.fechaHora = fechaHora;
        this.numeroMesa = numeroMesa;
        this.estado = estado;
        this.totalVenta = totalVenta;
        this.cliente = cliente;
        this.productosComanda = productosComanda;
    }
    
    
    // para crear la comanda con el cliente 

    public CrearComandaDTO(LocalDateTime fechaHora, Integer numeroMesa, Estado estado, double totalVenta, ClienteDTO cliente) {
        this.fechaHora = fechaHora;
        this.numeroMesa = numeroMesa;
        this.estado = estado;
        this.totalVenta = totalVenta;
        this.cliente = cliente;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public List<ProductoDTO> getProductosComanda() {
        return productosComanda;
    }

    public void setDetallesComanda(List<DetallesComandaDTO> detallesComanda) {
        this.detallesComanda = detallesComanda;
    }

    public void setProductosComanda(List<ProductoDTO> productosComanda) {
        this.productosComanda = productosComanda;
    }
  
    public void addProductoComanda(ProductoDTO producto){
        this.productosComanda.add(producto);
    }
    
    public void addDetallesComanda(DetallesComandaDTO detalleComanda){
        this.detallesComanda.add(detalleComanda);
    }
     public List<DetallesComandaDTO> getDetallesComanda() {
        return detallesComanda;
    }
    

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    @Override
    public String toString() {
        return "CrearComandaDTO{" + "fechaHora=" + fechaHora + ", numeroMesa=" + numeroMesa + ", estado=" + estado + ", totalVenta=" + totalVenta + ", cliente=" + cliente + ", productosComanda=" + productosComanda + ", detallesComanda=" + detallesComanda + '}';
    }
    
    
    
}
