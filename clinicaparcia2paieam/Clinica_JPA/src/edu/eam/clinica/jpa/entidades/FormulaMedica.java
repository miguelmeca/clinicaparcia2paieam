package edu.eam.clinica.jpa.entidades;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entidad que representa la formula medica del paciente.
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name=FormulaMedica.FIND_FORMULA_MEDICA_BY_ID_CONSULTA,query="select for from FormulaMedica for where for.consulta.id=:"+FormulaMedica.PARAMETRO_ID_CONSULTA),
	@NamedQuery(name=FormulaMedica.FIND_ALL,query="select for from FormulaMedica for")
})
public class FormulaMedica implements Serializable {
	
	/**
	 * Constante para la named Query de buscar todas las formulas medicas.
	 */
	public static final String FIND_ALL = "FormulaMedica.findAll";
	
	/**
	 * Constante para la named query de buscar FormulaMedica por el id de la consulta.
	 */
	public static final String FIND_FORMULA_MEDICA_BY_ID_CONSULTA="FormulaMedica.findFormulaMedicaByIdConsulta";
	
	/**
	 * Constante para el parametro del id de la consulta.
	 */
	public static final String PARAMETRO_ID_CONSULTA="idConsulta";

	/**
	 * Constante de serialización.
	 */
	private static final long serialVersionUID = 1L; 
	
	/**
	 * Identificador unico de la entidad.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Recomendaciones de la formula medica.
	 */
	@Basic(optional = true)
	private String recomendacion;
	
	/**
	 * Consulta a la que esta asociada la formula medica.
	 */
	@OneToOne(fetch = LAZY)
	@JoinColumn(name = "consulta")
	private Consulta consulta;
	
	/**
	 * Lista de detalles de formula medica.
	 */
	@OneToMany(mappedBy="formulaMedica",fetch=FetchType.LAZY,cascade= CascadeType.ALL)
	private List<DetalleFormulaMedica> detallesFormulaMedica;

	/**
	 * Constructor sin parametros.
	 */
	public FormulaMedica() {
		super();
                detallesFormulaMedica=new ArrayList<DetalleFormulaMedica>();
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
	 * @return the recomendacion
	 */
	public String getRecomendacion() {
		return recomendacion;
	}

	/**
	 * @param recomendacion the recomendacion to set
	 */
	public void setRecomendacion(String recomendacion) {
		this.recomendacion = recomendacion;
	}

	/**
	 * @return the consulta
	 */
	public Consulta getConsulta() {
		return consulta;
	}

	/**
	 * @param consulta the consulta to set
	 */
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	/**
	 * @return the detallesFormulaMedica
	 */
	public List<DetalleFormulaMedica> getDetallesFormulaMedica() {
		return detallesFormulaMedica;
	}

	/**
	 * @param detallesFormulaMedica the detallesFormulaMedica to set
	 */
	public void setDetallesFormulaMedica(List<DetalleFormulaMedica> detallesFormulaMedica) {
		this.detallesFormulaMedica = detallesFormulaMedica;
	}   
	
	//TODO:falta toString
}
