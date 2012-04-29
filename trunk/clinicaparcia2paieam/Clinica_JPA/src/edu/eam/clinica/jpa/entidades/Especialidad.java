package edu.eam.clinica.jpa.entidades;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

/**
 * Entitad que representa la especialidad de un medico.
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name=Especialidad.FIND_ESPECIALIDAD_BY_CODIGO,query="select esp from Especialidad esp where esp.codigo=:"+Especialidad.PARAMETRO_CODIGO),
	@NamedQuery(name=Especialidad.FIND_ALL,query="select esp from Especialidad esp")
})
public class Especialidad implements Serializable {
	/**
	 * Constante para la named Query de buscar todos las especialidades.
	 */
	public static final String FIND_ALL="Especialidad.findAll";
	
	/**
	 * Constante para la named Query de buscar especialidad por codigo.
	 */
	public static final String FIND_ESPECIALIDAD_BY_CODIGO = "Especialidad.findEspecialidadByCodigo";
	
	/**
	 *Constante para el parametro para buscar por codigo.
	 */
	public static final String PARAMETRO_CODIGO="codigo";
	
	/**
	 * Constante de serializacion
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Codigo del medico dentro de la IPS
	 */
	@Basic(optional = true)
	@Column(unique=true)
	private String codigo; 
	/**
	 * LLave primaria del medico.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	/**
	 * Nombre del medico.
	 */
	@Basic(optional = true)
	private String nombre;
	
	/**
	 * Medicos de esta especialidad.
	 *
	 **/
	@OneToMany(fetch = LAZY, mappedBy = "especialidad")
	private List<Medico> medicos;
	
	
	/**
	 * Remisiones de esta especialidad.
	 *
	 **/
	@OneToMany(fetch = LAZY, mappedBy = "especialidad")
	private List<Remision> remision;
	
	/**
	 * @return the remision
	 */
	public List<Remision> getRemision() {
		return remision;
	}

	/**
	 * @param remision the remision to set
	 */
	public void setRemision(List<Remision> remision) {
		this.remision = remision;
	}

	/**
	 * Cosntructor sin parametros.
	 */
	public Especialidad() {
		super();
	}

	/**
	 * @param codigo
	 * @param id
	 * @param nombre
	 */
	public Especialidad(String codigo, Long id, String nombre) {
		super();
		this.codigo = codigo;
		this.id = id;
		this.nombre = nombre;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the medicos
	 */
	public List<Medico> getMedicos() {
		return medicos;
	}

	/**
	 * @param medicos the medicos to set
	 */
	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}   
	
	public String toString(){
		return nombre;
	}
	
}
