/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import DAO.IngredienteDAO;
import Entidades.Ingrediente;
import Enums.UnidadMedida;
import exception.PersistenciaException;
import interfaces.IIngredienteDAO;
import java.util.List;

/**
 *
 * @author Admin
 */
public class pruebasIngrediente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IIngredienteDAO ingredienteDAO = IngredienteDAO.getInstanceDAO();

//        //Agregar un ingrediente
//        try {
//            Ingrediente ingrediente = new Ingrediente(1L, "Queso parmesano", 20, UnidadMedida.GRAMOS);
//            ingredienteDAO.guardarIngrediente(ingrediente);
//        } catch (PersistenciaException ex) {
//            ex.printStackTrace();
//        }
//
//        try {
//            Ingrediente ingrediente = new Ingrediente(2L, "Tomate", 13, UnidadMedida.PIEZAS);
//            ingredienteDAO.guardarIngrediente(ingrediente);
//        } catch (PersistenciaException ex) {
//            ex.printStackTrace();
//        }
//
//        try {
//            Ingrediente ingrediente = new Ingrediente(3L, "Leche", 7000, UnidadMedida.MILILITROS);
//            ingredienteDAO.guardarIngrediente(ingrediente);
//        } catch (PersistenciaException ex) {
//            ex.printStackTrace();
//        }

////        //Eliminar un ingrediente
////        try {
////            ingredienteDAO.eliminarIngrediente(18L);
////            ingredienteDAO.eliminarIngrediente(19L);
////            ingredienteDAO.eliminarIngrediente(20L);
////        } catch (PersistenciaException ex) {
////            ex.printStackTrace();
////        }

        //Actualizar un ingrediente
        try {
            Ingrediente ingrediente = ingredienteDAO.buscarPorId(15L);//Queso parmesano
            if (ingrediente != null) {
                ingrediente.setStock(25);
                ingrediente.setUnidadMedida(UnidadMedida.GRAMOS); // En caso de querer cambiar la unidad
                ingredienteDAO.actualizarIngrediente(ingrediente);
            }
        } catch (PersistenciaException ex) {
            ex.printStackTrace();
        }

        try {
            List<Ingrediente> ingredientes = ingredienteDAO.obtenerTodos();

            for (Ingrediente ingrediente : ingredientes) {
                System.out.println(ingrediente);
            }
        } catch (PersistenciaException ex) {
            ex.printStackTrace();
        }

    }

}
