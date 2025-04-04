/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import Entidades.Ingrediente;
import Enums.UnidadMedida;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IIngredienteDAO {

    public Ingrediente guardarIngrediente(Ingrediente ingrediente) throws PersistenciaException;

    public List<Ingrediente> obtenerTodos() throws PersistenciaException;

    public boolean eliminarIngrediente(Long id) throws PersistenciaException;

    public Ingrediente actualizarIngrediente(Ingrediente ingrediente) throws PersistenciaException;

    public Ingrediente buscarPorId(Long id) throws PersistenciaException;
    
    public boolean existeIngrediente(String nombre, UnidadMedida unidadMedida) throws PersistenciaException;
    
    public Ingrediente buscarPorNombre(String nombre) throws PersistenciaException;
    
    public List<Ingrediente> buscarIngredientes(Ingrediente ingredienteFiltro) throws PersistenciaException;
     
    
}
