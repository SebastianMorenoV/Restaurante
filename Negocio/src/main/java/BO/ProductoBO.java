/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.ProductoDAO;
import DTOSalida.ProductoDTO;
import Entidades.Producto;
import Enums.ProductoActivo;

/**
 *
 * @author SDavidLedesma
 */
public class ProductoBO {

    private ProductoDAO productoDAO;

    public ProductoBO(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    // agrega un nuevo producto validadno que  no exista ya con el mismo nombre
    public void agregarProducto(ProductoDTO productoDTO) throws Exception {
        if (productoDAO.EncontrarPorNombre(productoDTO.getNombre()) != null) {
            throw new Exception("El producto ya existe");
        }
        Producto producto = new Producto(
                productoDTO.getNombre(),
                productoDTO.getPrecio(),
                productoDTO.getTipo(),
                productoDTO.getProductoActivo() != null ? productoDTO.getProductoActivo() : ProductoActivo.Habilitado
        );
        productoDAO.crear(producto);
    }
    
}
