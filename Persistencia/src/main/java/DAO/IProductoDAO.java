/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Producto;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public interface IProductoDAO {

    void crear(Producto producto);

    Producto findById(Long id);

    List<Producto> findAll();

    void actualizar(Producto producto);

    void eliminar(Producto producto);
    
    Producto EncontrarPorNombre (String nombre);
}
