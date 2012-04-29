/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eam.clinica.jpa.enumeraciones;

/**
 *
 * @author diana
 */
public enum RevisionSistemaEnum {
             
        CARDIOVASCULAR("CardioVascular"),
        NERVIOSO("Nervioso"),
        ESTOMACAL("Estomacall"),
        LINFATICO("Linfatico"),
        RESPIRATORIO("Respiratorio"),
        HEPATICO("Hepatico");
        private String descripcion;
        
        private RevisionSistemaEnum(String descripcion){
            this.descripcion = descripcion;
            
        }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
        
        
    
        @Override
    public String toString() {
        return descripcion;
    }
}
