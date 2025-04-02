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
import java.util.logging.Level;
import java.util.logging.Logger;

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

        Ingrediente ingrediente = new Ingrediente(0L, ingredienteDTO.getNombre(),ingredienteDTO.getStock(), ingredienteDTO.getUnidadMedida());//Primero creamos el objeto de Ingrediente a partir del DTO

        try {
            ingredientesDAO.guardarIngrediente(ingrediente); // Primero llamamos al DAO para guardar el ingrediente             
            return new IngredienteDTO(ingrediente.getNombre(), ingrediente.getStock(), ingrediente.getUnidadMedida()); // Aqui estamos devolviendo un DTO con la información del ingrediente registrado
        } catch (PersistenciaException ex) {
            Logger.getLogger(IngredienteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al guardar el ingrediente: " + ex.getMessage(), ex);
        }
    }

}
