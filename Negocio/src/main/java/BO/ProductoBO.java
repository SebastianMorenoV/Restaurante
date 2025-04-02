/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.ComandaDAO;
import DAO.IngredienteDAO;
import DAO.ProductoDAO;
import DTOSalida.ProductoDTO;
import Entidades.Producto;
import Enums.ProductoActivo;
import static Mapper.ProductoMapper.convertirADTO;
import exception.NegocioException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author SDavidLedesma
 */
public class ProductoBO {

    private ProductoDAO productoDAO;
    private IngredienteDAO ingredienteDAO;
    private ComandaDAO comandaDAO;

    public ProductoBO() {
        this.productoDAO = new ProductoDAO();
        this.ingredienteDAO = new IngredienteDAO();
        this.comandaDAO = new ComandaDAO();
    }

    // falta agregar la lista dfe ingredientes  y de comandas
    // agrega un nuevo producto validadno que  no exista ya con el mismo nombre
    public void agregarProducto(ProductoDTO productoDTO) throws NegocioException {
        if (productoDAO.EncontrarPorNombre(productoDTO.getNombre()) != null) {
            throw new NegocioException("El producto ya existe");
        }
        Producto producto = new Producto(
                productoDTO.getNombre(),
                productoDTO.getPrecio(),
                productoDTO.getTipo(),
                productoDTO.getProductoActivo() != null ? productoDTO.getProductoActivo() : ProductoActivo.Habilitado // valor por defecto
        );
        productoDAO.crear(producto);
    }

    //obtiene el producto por id y lo convierte en DTO
    public ProductoDTO obtenerProducto(Long id) {
        Producto producto = productoDAO.findById(id);
        return (producto != null) ? convertirADTO(producto) : null;
    }

    //Lista todos los productos registrados
    public List<ProductoDTO> listaProductos() {
        List<Producto> productos = productoDAO.findAll();
        return productos.stream().map(Mapper.ProductoMapper::convertirADTO).collect(Collectors.toList());
    }

    //actualiza el producti, valida nombre duplicados
    public void actualizarProducti(ProductoDTO productoDTO) throws NegocioException {
        Producto productoExistente = productoDAO.findById(productoDTO.getId());
        if (productoExistente == null) {
            throw new NegocioException("Producto no encontrado");
        }
        Producto productoNombreIgual = productoDAO.EncontrarPorNombre(productoDTO.getNombre());
        if (productoNombreIgual != null && !productoNombreIgual.getId().equals(productoDTO.getId())) {
            throw new NegocioException("El nombre del producto ya existe");
        }

        productoExistente.setNombre(productoDTO.getNombre());
        productoExistente.setPrecio(productoDTO.getPrecio());
        productoExistente.setTipo(productoDTO.getTipo());
        productoExistente.setProductoActivo(productoDTO.getProductoActivo());
    }

    /** cambiar a deshabilitar
     * // elimina un producto por id public void eliminarProducto(Long id)
     * throws NegocioException { Producto producto = productoDAO.findById(id);
     * if (producto == null) { throw new NegocioException("Produco no
     * encontrado"); } productoDAO.eliminar(producto); }
    *
     */
}
