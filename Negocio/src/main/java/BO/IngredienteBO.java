/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTOEntrada.CrearIngredienteDTO;
import DTOSalida.IngredienteDTO;
import Entidades.Ingrediente;
import exception.NegocioException;
import exception.PersistenciaException;
import interfaces.IIngredienteBO;
import interfaces.IIngredienteDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Admin
 */
public class IngredienteBO implements IIngredienteBO {

    private IIngredienteDAO ingredientesDAO;

    public IngredienteBO(IIngredienteDAO ingredienteDAO) {
        this.ingredientesDAO = ingredienteDAO;
    }

    @Override
    public IngredienteDTO agregarIngrediente(CrearIngredienteDTO ingredienteDTO) throws NegocioException {

        if (ingredienteDTO.getNombre().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente no debe estar vacio");
        }
        if (ingredienteDTO.getStock() <= 0) {
            throw new NegocioException("El stock del ingrediente debe ser mayor a cero");
        }
        if (ingredienteDTO.getUnidadMedida() == null) {
            throw new NegocioException("La unidad de medida no puede ser nula");
        }

        Ingrediente ingrediente = new Ingrediente(ingredienteDTO.getNombre(), ingredienteDTO.getStock(), ingredienteDTO.getUnidadMedida());//Primero creamos el objeto de Ingrediente a partir del DTO

        try {
            if (ingredientesDAO.existeIngrediente(ingredienteDTO.getNombre(), ingredienteDTO.getUnidadMedida())) {
                throw new NegocioException("Ingrediente ya registrado");
            }

            ingredientesDAO.guardarIngrediente(ingrediente); // Primero llamamos al DAO para guardar el ingrediente             
            return new IngredienteDTO(ingrediente.getId(), ingrediente.getNombre(), ingrediente.getStock(), ingrediente.getUnidadMedida()); // Aqui estamos devolviendo un DTO con la información del ingrediente registrado
        } catch (PersistenciaException ex) {
            Logger.getLogger(IngredienteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al guardar el ingrediente: " + ex.getMessage(), ex);
        }
    }

    @Override
    public IngredienteDTO actualizarPorID(Long id, int nuevoStock) throws NegocioException {
        if (id == null || id <= 0) {
            throw new NegocioException("El ID del ingrediente no es valido");
        }
        if (nuevoStock < 0) {
            throw new NegocioException("El stock del ingrediente no puede ser negativo");
        }

        try {
            Ingrediente ingredienteExistente = ingredientesDAO.buscarPorId(id);
            if (ingredienteExistente == null) {
                throw new NegocioException("No se encontro un ingrediente con el ID: " + id);
            }

            ingredienteExistente.setStock(nuevoStock); //seteamos el nuevo stock al ingrediente encontrado
            Ingrediente ingredienteActualizado = ingredientesDAO.actualizarIngrediente(ingredienteExistente); //actualizamos el stock
            return new IngredienteDTO(ingredienteActualizado.getId(), ingredienteActualizado.getNombre(), ingredienteActualizado.getStock(), ingredienteActualizado.getUnidadMedida());

        } catch (PersistenciaException ex) {
            Logger.getLogger(IngredienteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al actualizar el stock del ingrediente: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void eliminarIngrediente(Long id) throws NegocioException {
        if (id == null || id <= 0) {
            throw new NegocioException("El ID del ingrediente no es valido");
        }

        try {
            Ingrediente ingredienteExistente = ingredientesDAO.buscarPorId(id);
            if (ingredienteExistente == null) {
                throw new NegocioException("No se encontro un ingrediente con el ID: " + id);
            }
            boolean eliminado = ingredientesDAO.eliminarIngrediente(id);
            if (!eliminado) {
                throw new NegocioException("No se pudo eliminar el ingrediente");
            }

        } catch (PersistenciaException ex) {
            Logger.getLogger(IngredienteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al eliminar el ingrediente: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<IngredienteDTO> obtenerTodos() throws NegocioException {
        try {
            List<Ingrediente> ingredientes = ingredientesDAO.obtenerTodos();

            return ingredientes.stream()//aqui se convierte la lista en un stream  para poder procesar los elementos de la lista(List<Ingrediente> a Stream<Ingrediente>)
                    .map(i -> new IngredienteDTO(i.getId(), i.getNombre(), i.getStock(), i.getUnidadMedida()))//aqui se mapea cada Ingrediente a un IngredienteDTO
                    .collect(Collectors.toList());//Ahora es Stream<IngredienteDTO> y se convierte a una lista List<IngredienteDTO>

        } catch (PersistenciaException ex) {
            Logger.getLogger(IngredienteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al obtener la lista de ingredientes: " + ex.getMessage(), ex);
        }
    }

    @Override
    public IngredienteDTO obtenerPorId(Long id) throws NegocioException {
        if (id == null || id <= 0) {
            throw new NegocioException("El ID del ingrediente no es válido");
        }

        try {
            Ingrediente ingrediente = ingredientesDAO.buscarPorId(id);
            if (ingrediente == null) {
                throw new NegocioException("No se encontró un ingrediente con el ID: " + id);
            }

            return new IngredienteDTO(ingrediente.getId(), ingrediente.getNombre(), ingrediente.getStock(), ingrediente.getUnidadMedida());

        } catch (PersistenciaException ex) {
            Logger.getLogger(IngredienteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al obtener el ingrediente: " + ex.getMessage(), ex);
        }
    }

}
