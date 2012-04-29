package edu.eam.clinica.jpa.entidades;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entidad que representa el laboratorio del medicamento.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name=Laboratorio.FIND_LABORATORIO_BY_CODIGO,query="select lab from Laboratorio lab where lab.codigo=:"+Laboratorio.PARAMETRO_CODIGO),
	@NamedQuery(name=Laboratorio.FIND_ALL,query="select lab from Laboratorio lab")
})
public class Laboratorio implements Serializable {
	
	/**
	 * Constante para la named Query de buscar todos los laboratirios.
	 */
	public static final String FIND_ALL="Laboratorio.findAll";
	
	/**
	 * Constante para la named Query de buscar Laboratorio por codigo.
	 */
	public static final String FIND_LABORATORIO_BY_CODIGO = "Laboratorio.findLaboratorioByCodigo";
	
	/**
	 *Constante para el parametro para buscar por codigo.
	 */
	public static final String PARAMETRO_CODIGO="codigo";
	

	/**
	 * Constante de serealización.
	 */
	private static final long serialVersionUID = 1L;  
	
	/**
	 * Identificador unico de la entidad.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Código del laboratorio.
	 */
	@Basic(optional = false)
	@Column(unique = true)
	private String codigo;
	
	/**
	 * Nombre del laboratorio.
	 */
	@Basic(optional = false)
	private String nombre;
	
	/**
	 * Lista de medicamentos del laboratorio.
	 */
	@OneToMany(mappedBy="laboratorio",fetch=FetchType.LAZY)
	private List<Medicamento> medocamentos;
	
	/**
	 * Constructor sin parametros.
	 */
	public Laboratorio() {
		super();
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
	 * @return the medocamentos
	 */
	public List<Medicamento> getMedocamentos() {
		return medocamentos;
	}

	/**
	 * @param medocamentos the medocamentos to set
	 */
	public void setMedocamentos(List<Medicamento> medocamentos) {
		this.medocamentos = medocamentos;
	}   
	
	public String toString(){
		return nombre;
	}
   
}
