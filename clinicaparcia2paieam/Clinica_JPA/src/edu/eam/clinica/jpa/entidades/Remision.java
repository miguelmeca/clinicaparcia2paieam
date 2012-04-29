package edu.eam.clinica.jpa.entidades;

import edu.eam.clinica.jpa.entidades.Consulta;
import edu.eam.clinica.jpa.entidades.Especialidad;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Remision
 *
 */
@Entity
@NamedQuery(name=Remision.CONSULTA_BUSCAR_POR_CONSULTA,
            query="select r from Remision r where r.consulta.id=:"
            +Remision.PARAMETRO_CONSULTA)
public class Remision implements Serializable {

	
       public static final String CONSULTA_BUSCAR_POR_CONSULTA="Remision.findByConsulta";
       
       public static final String PARAMETRO_CONSULTA="id";
    
	@ManyToOne
	@JoinColumn(name="ESPECIALDAD")
	private Especialidad especialidad;
	
	
	private String Observacion;
	
	@ManyToOne
	@JoinColumn(name="CONSULTA")
	private Consulta consulta;   
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private static final long serialVersionUID = 1L;

	public Remision() {
		super();
	}   
	public Especialidad getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}   
	public String getObservacion() {
		return this.Observacion;
	}

	public void setObservacion(String Observacion) {
		this.Observacion = Observacion;
	}   
	public Consulta getConsulta() {
		return this.consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}   
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
   
}
