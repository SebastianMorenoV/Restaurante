/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOSalida;

/**
 *
 * @author SDavidLedesma
 */
public class DetallesComandaDTO {

    private Long id;
    private ProductoDTO producto;
    private ComandaDTO comanda;
    private Integer cantidad;
    private double precioUnitario;
    private String comentarios;
    private double importeTotal;

    public DetallesComandaDTO() {
    }

    public DetallesComandaDTO(ProductoDTO producto, ComandaDTO comanda, Integer cantidad, double precioUnitario, String comentarios, double importeTotal) {
        this.producto = producto;
        this.comanda = comanda;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.comentarios = comentarios;
        this.importeTotal = importeTotal;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public ComandaDTO getComanda() {
        return comanda;
    }

    public void setComanda(ComandaDTO comanda) {
        this.comanda = comanda;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    @Override
    public String toString() {
        return  comentarios ;
    }

   
}
