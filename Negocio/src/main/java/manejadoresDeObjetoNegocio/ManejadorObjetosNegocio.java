/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadoresDeObjetoNegocio;

import BO.ClienteBO;
import BO.ComandaBO;
import BO.IngredienteBO;
import BO.MesaBO;
import BO.ProductoBO;
import DAO.ClienteDAO;
import DAO.ComandaDAO;
import DAO.IngredienteDAO;
import DAO.MesaDAO;
import DAO.ProductoDAO;
import interfaces.IClienteBO;
import interfaces.IClienteDAO;
import interfaces.IComandaBO;
import interfaces.IComandaDAO;
import interfaces.IIngredienteBO;
import interfaces.IIngredienteDAO;
import interfaces.IMesaBO;
import interfaces.IMesaDAO;
import interfaces.IProductoBO;
import interfaces.IProductoDAO;

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
     
     public static IProductoBO crearProductosBO(){
         IProductoDAO productoDAO = ProductoDAO.getInstanceDAO();
         IProductoBO productoBO = new ProductoBO(productoDAO);
         
         return productoBO;
     }
     
     public static IComandaBO crearComandaBO(){
         IComandaDAO comandaDAO = ComandaDAO.getInstanceDAO();
         IComandaBO comandaBO = new ComandaBO(comandaDAO);
         
         return comandaBO;
     }
}
