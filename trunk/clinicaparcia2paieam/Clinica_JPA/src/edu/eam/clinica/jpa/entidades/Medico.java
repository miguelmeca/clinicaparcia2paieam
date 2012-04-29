package edu.eam.clinica.jpa.entidades;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Medico
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Medico.FIND_ALL, query = "select med from Medico med"),
    @NamedQuery(name = Medico.FIND_MEDICO_BY_NIT, query = "select med from Medico med where med.NIT=:" + Medico.PARAMETRO_NIT)
})
public class Medico extends Funcionario implements Serializable {

    /**
     * Constante para la named Query de buscar todos los Medicos.
     */
    public static final String FIND_ALL = "Medico.findAll";
    /**
     * Constante para la named query de buscar medico por NIT.
     */
    public static final String FIND_MEDICO_BY_NIT = "Medico.findMedicoByNIT";
    /**
     * Constante para el parametro de NIT del Medico.
     */
    public static final String PARAMETRO_NIT = "NIT";
    /**
     * Constante de serializacion.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Numero unico que identifica al medico.   
     */
    @Column(unique = true)
    private String NIT;
    /**
     * Registro del medico, tarjeta profesional
     */
    //TODO: es null en la BD, ojo.
    @Basic(optional = false)
    @Column(unique = true)
    private String registroMedico;
    /**
     * Especialidad del medico
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ESPECIALIDAD")
    private Especialidad especialidad;
   
   
	
	
    
    
    /**
     * Constructor sin parametros.
     */
    public Medico() {
        super();
    }

    /**
     * @param nIT
     * @param nombre
     * @param apellido
     * @param registroMedico
     */
    public Medico(String nIT,
            String registroMedico) {
        super();
        NIT = nIT;

        this.registroMedico = registroMedico;
    }

    /**
     * @return the nIT
     */
    public String getNIT() {
        return NIT;
    }

    /**
     * @param nIT the nIT to set
     */
    public void setNIT(String nIT) {
        NIT = nIT;
    }

    /**
     * @return the registroMedico
     */
    public String getRegistroMedico() {
        return registroMedico;
    }

    /**
     * @param registroMedico the registroMedico to set
     */
    public void setRegistroMedico(String registroMedico) {
        this.registroMedico = registroMedico;
    }

    /**
     * @return the especialidad
     */
    public Especialidad getEspecialidad() {
        return especialidad;
    }

    /**
     * @param especialidad the especialidad to set
     */
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

   

    @Override
    public String toString() {
        return getPrimerNombre()+" "+getPrimerApellido();
    }
    
   
}
