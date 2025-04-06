/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOSalida.IngredienteDTO;
import DTOSalida.ProductoDTO;
import Entidades.Producto;
import Enums.ProductoActivo;
import exception.PersistenciaException;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author SDavidLedesma
 */
public interface IProductoDAO {
    
    /**
     * 
     * @param nombre
     * @return
     * @throws PersistenciaException 
     */
   // public boolean existeNombre (String nombre) throws PersistenciaException;
    
    /**
     * guarda un producto y lo persiste en la  base de datos
     * @param producto
     * @return
     * @throws PersistenciaException 
     */
    public Producto guardarProducto(Producto producto) throws PersistenciaException;
    
    /**
     *  actualiza un producto y lo persiste en la base de datos
     * @param producto
     * @return
     * @throws PersistenciaException 
     */
    public Producto actualizarProducto(Producto producto) throws PersistenciaException;
    
    /**
     * obtiene un producto en base al id
     * @param id
     * @return
     * @throws PersistenciaException 
     */
    public Producto obtenerProductoPorId(Long id) throws PersistenciaException;
    
    
    /**
     * busca un producto 
     * @param filtro
     * @return
     * @throws PersistenciaException 
     */
    public List<Producto> buscarProductos (ProductoDTO filtro) throws PersistenciaException;
    
    
    /**
     * 
     * @return
     * @throws PersistenciaException 
     */
    public List<Producto> obtenerTodos() throws PersistenciaException;
    
    
    /**
     * habilita/deshabilita el producto
     * @param id
     * @param nuevoEstado
     * @return
     * @throws PersistenciaException 
     */
    public boolean cambiarEstado(Long id, ProductoActivo nuevoEstado) throws PersistenciaException;
    
   /**
    * 
    * @param producto
    * @return
    * @throws PersistenceException 
    */
    public List<IngredienteDTO> obtenerIngredientesPorProducto(String producto) throws PersistenceException;
        
    /**
     * 
     * @param nombre
     * @return
     * @throws PersistenceException 
     */
    public Producto buscarPorNombre(String nombre) throws PersistenceException;
    
}
