/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTOSalida.ProductoDTO;
import Entidades.Producto;

/**
 *
 * @author SDavidLedesma
 */
public class ProductoMapper {
    
    
    public static ProductoDTO convertirADTO(Producto producto){
        return new ProductoDTO(
        producto.getId(),
        producto.getNombre(),
        producto.getPrecio(),
        producto.getTipo(),
        producto.getProductoActivo()
                // agregar ingredientes y comandas
        );
    }
    
    
    public static Producto convertirAEntity(ProductoDTO productoDTO){
        return new Producto(
        productoDTO.getId(),
        productoDTO.getNombre(),
        productoDTO.getPrecio(),
        productoDTO.getTipo(),
        productoDTO.getProductoActivo()
              //  agregar ingredientes y comandas
        );
    }
}
