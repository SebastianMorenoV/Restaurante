/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author Sebastian Moreno.
 */
public class Conexion {

    /*
    OJO, esto NO es singleton:
        private static EntityManagerFactory emf; //opcional, la fabrica ya gestiona esto

    de hecho eso ya lo gestiona el EMF internamente, es parte de sus propiedades. Me refiero a la gestion de instancias.
    El patrón Singleton busca garantizar que solo haya una instancia de una clase en toda la aplicación. 
    Sin embargo, el EntityManagerFactory (EMF) ya gestiona internamente la creación y reutilización de instancias 
    de fábrica. En otras palabras, no necesitas implementar un Singleton manualmente porque el propio EMF se comporta 
    de manera similar.
     */
    // Vamos a manejarlo asi por lo pronto:
    
    //EntityManagerFactory se crea solo una vez y se reutiliza en toda la aplicación
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexionPU");

    //Se obtiene un nuevo EntityManager en cada llamada a getEntityManager(), pero sin recrear la fábrica.
    public static EntityManager crearConexion() {
        return emf.createEntityManager(); // Se reutiliza la fábrica y se obtiene un nuevo EntityManager
    }

    // cerrar() para liberar recursos al finalizar el proceso
    public static void cerrar() {
        if (emf.isOpen()) {
            emf.close();
        }
        // no es necesario un else
    }

    // Recuerden, no siempre la mejor solucion es la primera que se nos ocurre o lo ultimo que aprendemos, siempre hay que adaptarnos a buscar las "mejores opciones"
    // Sin embargo, es un gusto que siempre lleguen con ideas nuevas(:
}
