/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.ComandaDAO;
import DAO.DetallesComandaDAO;
import DAO.IngredienteDAO;
import DAO.IngredientesProductoDAO;
import DAO.ProductoDAO;
import DTOSalida.ProductoDTO;
import Entidades.Comanda;
import Entidades.DetallesComanda;
import Entidades.Ingrediente;
import Entidades.IngredientesProducto;
import Entidades.Producto;
import Enums.ProductoActivo;
import exception.NegocioException;
import exception.PersistenciaException;
import interfaces.IProductoBO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SDavidLedesma
 */
public class ProductoBO implements IProductoBO {

    private final ProductoDAO productoDAO;
    private final IngredienteDAO ingredienteDAO;
    private final IngredientesProductoDAO ingredientesProductoDAO;
    private final ComandaDAO comandaDAO;
    private final DetallesComandaDAO detallesComandaDAO;

    public ProductoBO() {
        this.productoDAO = new ProductoDAO();
        this.ingredienteDAO = new IngredienteDAO();
        this.ingredientesProductoDAO = new IngredientesProductoDAO();
        this.comandaDAO = new ComandaDAO();
        this.detallesComandaDAO = new DetallesComandaDAO();
    }

    @Override
    public ProductoDTO registrarProducto(ProductoDTO productoDTO) throws NegocioException {
        // Validaciones básicas
        if (productoDTO.getNombre() == null || productoDTO.getNombre().isBlank()) {
            throw new NegocioException("El nombre del producto no puede estar vacío.");
        }

        if (productoDTO.getPrecio() <= 0) {
            throw new NegocioException("El precio debe ser mayor a cero.");
        }

        try {
            Producto producto = new Producto();
            producto.setNombre(productoDTO.getNombre());
            producto.setPrecio(productoDTO.getPrecio());
            producto.setTipo(productoDTO.getTipo());
            producto.setProductoActivo(ProductoActivo.Habilitado); // Por defecto se guarda activo

            productoDAO.guardarProducto(producto);

            return new ProductoDTO(
                    producto.getId(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getTipo(),
                    producto.getProductoActivo()
            );
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al registrar el producto: " + ex.getMessage(), ex);
        }
    }

    @Override
    public ProductoDTO actualizarProducto(ProductoDTO productoDTO) throws NegocioException {
        if (productoDTO.getId() <= 0) {
            throw new NegocioException("El ID del producto no es válido.");
        }

        try {
            Producto productoExistente = productoDAO.obtenerProductoPorId(productoDTO.getId());

            if (productoExistente == null) {
                throw new NegocioException("No se encontró el producto con ID " + productoDTO.getId());
            }

            productoExistente.setNombre(productoDTO.getNombre());
            productoExistente.setPrecio(productoDTO.getPrecio());
            productoExistente.setTipo(productoDTO.getTipo());

            Producto actualizado = productoDAO.actualizarProducto(productoExistente);

            return new ProductoDTO(
                    actualizado.getId(),
                    actualizado.getNombre(),
                    actualizado.getPrecio(),
                    actualizado.getTipo(),
                    actualizado.getProductoActivo()
            );
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al actualizar el producto: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void habilitar_deshabilitar_producto(int idProducto, ProductoActivo nuevoEstado) throws NegocioException {
        try {
            boolean actualizado = productoDAO.cambiarEstado((long) idProducto, nuevoEstado);
            if (!actualizado) {
                throw new NegocioException("No se pudo cambiar el estado del producto con ID " + idProducto);
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al cambiar el estado del producto: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<ProductoDTO> buscarProductos(ProductoDTO filtro) throws NegocioException {
        try {
            // Obtener productos desde el DAO
            List<Producto> productos = productoDAO.buscarProductos(filtro);

            // Convertir manualmente a ProductoDTO
            List<ProductoDTO> productosDTO = new ArrayList<>();
            for (Producto producto : productos) {
                ProductoDTO dto = new ProductoDTO();
                dto.setId(producto.getId());
                dto.setNombre(producto.getNombre());
                dto.setPrecio(producto.getPrecio());
                dto.setTipo(producto.getTipo());
                dto.setProductoActivo(producto.getProductoActivo());
                productosDTO.add(dto);
            }

            return productosDTO;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al buscar productos: " + ex.getMessage(), ex);
        }
    }

    public void agregarIngredienteAProducto(Long productoId, Long ingredienteId, Integer cantidad) throws Exception {
        Producto producto = productoDAO.obtenerProductoPorId(productoId);
        Ingrediente ingrediente = ingredienteDAO.buscarPorId(ingredienteId);

        if (producto == null || ingrediente == null) {
            throw new Exception("Producto o Ingrediente no encontrado");
        }

        IngredientesProducto nuevo = new IngredientesProducto();
        nuevo.setProducto(producto);
        nuevo.setIngrediente(ingrediente);
        nuevo.setCantidad(cantidad);

        ingredientesProductoDAO.insertar(nuevo);
    }

    public void crearYGuardarDetalleComanda(Long productoId, Long comandaId, int cantidad, String comentarios) {
        try {
            Producto producto = productoDAO.obtenerProductoPorId(productoId);
            Comanda comanda = comandaDAO.obtenerComandaPorId(comandaId);

            if (producto == null || comanda == null) {
                System.out.println("Producto o Comanda no encontrados.");
                return;
            }

            DetallesComanda detalle = new DetallesComanda();
            detalle.setProducto(producto);
            detalle.setComanda(comanda);
            detalle.setCantidad(cantidad);
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setImporteTotal(producto.getPrecio() * cantidad);
            detalle.setComentarios(comentarios);

            detallesComandaDAO.guardar(detalle);
            System.out.println("Detalle de comanda guardado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Override public List<Producto> buscarProductos(ProductoDTO filtro)
     * throws PersistenciaException { EntityManager em =
     * Conexion.crearConexion(); List<Producto> productos = new ArrayList<>();
     *
     * try { em.getTransaction().begin(); em.flush();
     *
     * StringBuilder jpql = new StringBuilder("SELECT p FROM Producto p WHERE
     * 1=1");
     *
     * if (filtro.getNombre() != null && !filtro.getNombre().trim().isEmpty()) {
     * jpql.append(" AND LOWER(p.nombre) LIKE :nombre"); } if (filtro.getTipo()
     * != null) { jpql.append(" AND p.tipo = :tipo"); } if
     * (filtro.getProductoActivo() != null) { jpql.append(" AND p.productoActivo
     * = :productoActivo"); }
     *
     * TypedQuery<Producto> query = em.createQuery(jpql.toString(),
     * Producto.class) .setHint("javax.persistence.cache.storeMode", "REFRESH");
     *
     * if (filtro.getNombre() != null && !filtro.getNombre().trim().isEmpty()) {
     * query.setParameter("nombre", "%" + filtro.getNombre().toLowerCase() +
     * "%"); } if (filtro.getTipo() != null) { query.setParameter("tipo",
     * filtro.getTipo()); } if (filtro.getProductoActivo() != null) {
     * query.setParameter("productoActivo", filtro.getProductoActivo()); }
     *
     * productos = query.getResultList(); em.getTransaction().commit(); } catch
     * (Exception e) { if (em.getTransaction().isActive()) {
     * em.getTransaction().rollback(); } throw new PersistenciaException("Error
     * al buscar productos: " + e.getMessage(), e); } finally { em.close(); }
     *
     * return productos; }
     */
}
