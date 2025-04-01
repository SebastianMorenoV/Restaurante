/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import DAO.MesaDAO;
import Entidades.Mesa;
import exception.PersistenciaException;
import interfaces.IMesaDAO;

/**
 *
 * @author Lap-064
 */
public class pruebasMesa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IMesaDAO mesaDAO = MesaDAO.getInstanceDAO();
        
        //agregar una mesa
//        try {
//            Mesa mesa = new Mesa(1L,1);
//            mesaDAO.guardarMesa(mesa);
//        } catch (PersistenciaException e) {
//            e.printStackTrace();
//        }
        
          //agregar una mesa
        try {
            Mesa mesa = new Mesa(1L,1);
            mesaDAO.eliminarMesa(1L);
        } catch (PersistenciaException e) {
            e.printStackTrace();
        }
        
    }
    
}
