/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import Entidades.Producto;
import Enums.ProductoActivo;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author SDavidLedesma
 */
public interface IProductoDAO {

    public Producto crear(Producto producto) throws PersistenceException;

    public Producto findById(Long id) throws PersistenceException;

    public List<Producto> findAll() throws PersistenceException;

    public Producto actualizar(Producto producto) throws PersistenceException;

    public Producto cambiarEstado(Producto producto, ProductoActivo estadoProducto) throws PersistenceException;

    public Producto EncontrarPorNombre(String nombre) throws PersistenceException;
}
