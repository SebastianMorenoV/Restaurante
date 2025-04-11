/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.ComandaDAO;
import DAO.DetallesComandaDAO;
import DAO.IngredienteDAO;
import DAO.IngredientesProductoDAO;
import DTOSalida.IngredienteDTO;
import DTOSalida.IngredientesProductoDTO;
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
import interfaces.IProductoDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SDavidLedesma
 */
public class ProductoBO implements IProductoBO {

    private IProductoDAO productoDAO;
    private final IngredienteDAO ingredienteDAO = new IngredienteDAO();
    private final IngredientesProductoDAO ingredientesProductoDAO = new IngredientesProductoDAO();
    private final ComandaDAO comandaDAO = new ComandaDAO();
    private final DetallesComandaDAO detallesComandaDAO = new DetallesComandaDAO();

//cambiar constructor con las instancais dentro en caso de problema 
    public ProductoBO(IProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
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

        } catch (PersistenciaException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<IngredientesProductoDTO> getIngredientesProducto(Long idProducto) throws NegocioException {
        try {
            List<IngredientesProducto> lista = ingredientesProductoDAO.obtenerPorProductoId(idProducto);
            List<IngredientesProductoDTO> listaDTO = new ArrayList<>();

            for (IngredientesProducto ip : lista) {
                IngredienteDTO ingredienteDTO = new IngredienteDTO();
                ingredienteDTO.setId(ip.getIngrediente().getId());
                ingredienteDTO.setNombre(ip.getIngrediente().getNombre());
                ingredienteDTO.setUnidadMedida(ip.getIngrediente().getUnidadMedida());

                ProductoDTO productoDTO = new ProductoDTO();
                productoDTO.setId(ip.getProducto().getId());
                productoDTO.setNombre(ip.getProducto().getNombre());
                productoDTO.setPrecio(ip.getProducto().getPrecio());
                productoDTO.setTipo(ip.getProducto().getTipo());
                productoDTO.setProductoActivo(ip.getProducto().getProductoActivo());

                IngredientesProductoDTO dto = new IngredientesProductoDTO();
                dto.setId(ip.getId());
                dto.setCantidad(ip.getCantidad());
                dto.setIngrediente(ingredienteDTO);
                dto.setProducto(productoDTO);

                listaDTO.add(dto);
            }

            return listaDTO;

        } catch (Exception e) {
            throw new NegocioException("Error al obtener ingredientes del producto", e);
        }
    }

    @Override
    public ProductoDTO buscarProductoPorNombre(String nombre) throws NegocioException {
        try {
            Producto producto = productoDAO.buscarProductoPorNombre(nombre);
            if (producto == null) {
                return null;
            }

            ProductoDTO dto = new ProductoDTO();
            dto.setId(producto.getId());
            dto.setNombre(producto.getNombre());
            dto.setPrecio(producto.getPrecio());
            dto.setTipo(producto.getTipo());
            dto.setProductoActivo(producto.getProductoActivo());

            // AQUÍ DEBES CARGAR LOS INGREDIENTES
            List<IngredientesProducto> ingredientes = ingredientesProductoDAO.obtenerPorProductoId(producto.getId());
            List<IngredientesProductoDTO> ingredientesDTO = new ArrayList<>();

            for (IngredientesProducto ip : ingredientes) {
                Ingrediente i = ip.getIngrediente();

                IngredienteDTO iDTO = new IngredienteDTO();
                iDTO.setId(i.getId());
                iDTO.setNombre(i.getNombre());
                iDTO.setUnidadMedida(i.getUnidadMedida());
                iDTO.setStock(i.getStock());

                IngredientesProductoDTO ipDTO = new IngredientesProductoDTO();
                ipDTO.setId(ip.getId());
                ipDTO.setCantidad(ip.getCantidad());
                ipDTO.setIngrediente(iDTO);

                ingredientesDTO.add(ipDTO);
            }

//            dto.setIngredienteProducto(ingredientesDTO); // ️ esto es clave
            return dto;

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar producto por nombre", e);
        }
    }

    public void deshabilitarProducto(ProductoDTO productoDTO) throws NegocioException {
        try {
            Producto producto = productoDAO.obtenerProductoPorId(productoDTO.getId());

            if (producto == null) {
                throw new NegocioException("Producto no encontrado.");
            }

            producto.setProductoActivo(ProductoActivo.Deshabilitado);
            productoDAO.actualizarProducto(producto); // Usa merge aquí
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al deshabilitar producto", e);
        }
    }

    @Override
    public void habilitarProducto(ProductoDTO productoDTO) throws NegocioException {
      try {
            Producto producto = productoDAO.obtenerProductoPorId(productoDTO.getId());

            if (producto == null) {
                throw new NegocioException("Producto no encontrado.");
            }

            producto.setProductoActivo(ProductoActivo.Habilitado);
            productoDAO.actualizarProducto(producto); // Usa merge aquí
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al habilitar producto", e);
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
