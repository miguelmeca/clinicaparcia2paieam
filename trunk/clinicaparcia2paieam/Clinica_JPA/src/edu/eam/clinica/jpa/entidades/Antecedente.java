/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eam.clinica.jpa.entidades;

import edu.eam.clinica.jpa.enumeraciones.TipoAntecedenteEnum;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Camilo Andres
 */
@Entity
public class Antecedente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    
    @Column(columnDefinition="text")
    private String hipertencion;
    
    @Column(columnDefinition="text")
    private String diabetes;
    
    @Column(columnDefinition="text")
    private String cancer;
    
    @Column(columnDefinition="text")
    private String alergia;
    
    @Column(columnDefinition="text")
    private String cirugias;

    @Column(columnDefinition="text")
    private String toxicas;
    
    @Column(columnDefinition="text")
    private String tratamientos;
    
    @Column(columnDefinition="text")
    private String medicamentosActuales;
    
    
    @JoinColumn(name="HISTORIA")
    @ManyToOne
    private HistoriaClinica historia;

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    public String getCancer() {
        return cancer;
    }

    public void setCancer(String cancer) {
        this.cancer = cancer;
    }

    public String getCirugias() {
        return cirugias;
    }

    public void setCirugias(String cirugias) {
        this.cirugias = cirugias;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    public String getHipertencion() {
        return hipertencion;
    }

    public void setHipertencion(String hipertencion) {
        this.hipertencion = hipertencion;
    }

    public HistoriaClinica getHistoria() {
        return historia;
    }

    public void setHistoria(HistoriaClinica historia) {
        this.historia = historia;
    }

    public String getMedicamentosActuales() {
        return medicamentosActuales;
    }

    public void setMedicamentosActuales(String medicamentosActuales) {
        this.medicamentosActuales = medicamentosActuales;
    }

   

    public String getToxicas() {
        return toxicas;
    }

    public void setToxicas(String toxicas) {
        this.toxicas = toxicas;
    }

    public String getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(String tratamientos) {
        this.tratamientos = tratamientos;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Antecedente)) {
            return false;
        }
        Antecedente other = (Antecedente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.torresquintero.oncologos.entidades.Antecedente[ id=" + id + " ]";
    }
    
}
