/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadoresDeObjetoNegocio;

import BO.ClienteBO;
import BO.IngredienteBO;
import BO.MesaBO;
import DAO.ClienteDAO;
import DAO.IngredienteDAO;
import DAO.MesaDAO;
import interfaces.IClienteBO;
import interfaces.IClienteDAO;
import interfaces.IIngredienteBO;
import interfaces.IIngredienteDAO;
import interfaces.IMesaBO;
import interfaces.IMesaDAO;

/**
 *
 * @author Sebastian Moreno
 */
public class ManejadorObjetosNegocio {
     public static IClienteBO crearClientesBO() {
        // 1. Creamos la instancia del DAO a utilizar.
        // Se obtiene la única instancia disponible debido al uso del patrón Singleton.
        IClienteDAO clientesDAO = ClienteDAO.getInstanceDAO();

        // 2. Creamos la instancia del BO, inyectando el DAO como dependencia.
        IClienteBO clientesBO = new ClienteBO(clientesDAO);

        // 3. Retornamos la instancia del BO lista para ser utilizada.
        return clientesBO;
    }
     
     public static IIngredienteBO crearIngredientesBO(){
         IIngredienteDAO ingredienteDAO = IngredienteDAO.getInstanceDAO();
         
         IIngredienteBO ingredienteBO = new IngredienteBO(ingredienteDAO);
         
         return ingredienteBO;
         
     }
     public static IMesaBO crearMesasBO(){
         IMesaDAO mesasDAO = MesaDAO.getInstanceDAO();
         IMesaBO mesasBO = new MesaBO(mesasDAO);
         
         return mesasBO;
         
     }
}
