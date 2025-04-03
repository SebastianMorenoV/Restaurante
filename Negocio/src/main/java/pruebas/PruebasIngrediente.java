/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import DTOEntrada.CrearIngredienteDTO;
import DTOSalida.IngredienteDTO;
import Enums.UnidadMedida;
import exception.NegocioException;
import interfaces.IIngredienteBO;
import java.util.List;
import manejadoresDeObjetoNegocio.ManejadorObjetosNegocio;

/**
 *
 * @author Admin
 */
public class PruebasIngrediente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IIngredienteBO ingredienteBO = ManejadorObjetosNegocio.crearIngredientesBO();

        try {
//            CrearIngredienteDTO ingrediente = new CrearIngredienteDTO("Queso parmesano", 1000, UnidadMedida.GRAMOS);
//            CrearIngredienteDTO ingrediente1 = new CrearIngredienteDTO("Leche entera", 10000, UnidadMedida.MILILITROS);
//            CrearIngredienteDTO ingrediente2 = new CrearIngredienteDTO("Tomate", 10, UnidadMedida.PIEZAS);
//            ingredienteBO.agregarIngrediente(ingrediente);
//            ingredienteBO.agregarIngrediente(ingrediente1);
//            ingredienteBO.agregarIngrediente(ingrediente2);
            //ingredienteBO.eliminarIngrediente(12L);
            //ingredienteBO.actualizarStockPorNombre("Leche entera", 0);

            List<IngredienteDTO> ingredientes = ingredienteBO.obtenerTodos();
            for (IngredienteDTO ingredienteE : ingredientes) {
                System.out.println(ingredienteE);
            }

        } catch (NegocioException e) {
            e.printStackTrace();
        }

    }

}
