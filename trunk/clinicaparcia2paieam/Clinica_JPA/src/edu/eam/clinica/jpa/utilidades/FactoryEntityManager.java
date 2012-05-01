/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eam.clinica.jpa.utilidades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *Clase para crear un entitymananger.
 * @author Camilo Andres
 */
public class FactoryEntityManager {
    
    /**
     * Administrador de entidades.
     */
    private static EntityManager em;
    
    private FactoryEntityManager(){
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("tiendaJPAPU");
       
        em=emf.createEntityManager();
       
        
    }
    /**
     * Metodo que retorna el Entitymanager
     * @return EntityManager. 
     */
    public static EntityManager getEm(){
        
        if(em==null){
            new FactoryEntityManager();
        }
        return em;
    }
    
}
