/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOSalida.ProductoDTO;
import Entidades.Producto;
import Enums.ProductoActivo;
import exception.NegocioException;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public interface IProductoBO {
    
    /**
     * Registra un nuevo producto
     * @param productoDTO
     * @return
     * @throws NegocioException 
     */
    public ProductoDTO registrarProducto(ProductoDTO productoDTO) throws NegocioException;
    
    /**
     * Actualiza la informacion de un producto
     * @param productoDTO
     * @return
     * @throws NegocioException 
     */
    public ProductoDTO actualizarProducto(ProductoDTO productoDTO) throws NegocioException;
    
    /**
     * Habilita o Deshabilita un producto
     * @param idProducto
     * @param a
     * @throws NegocioException 
     */
    public void habilitar_deshabilitar_producto(int idProducto, ProductoActivo a) throws NegocioException;
    
    /**
     * busca productos segun los filtros proporcionados 
     * @param filtro
     * @return
     * @throws NegocioException 
     */
    public List<ProductoDTO> buscarProductos(ProductoDTO filtro) throws NegocioException;
    
    /**
     * 
     * @param nombre
     * @return
     * @throws NegocioException 
     */
    public ProductoDTO obtenerPorNombre(String nombre) throws NegocioException;
}
