package edu.eam.clinica.jpa.entidades;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

/**
 * Entidad que representa la orden medica
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = OrdenMedica.FIND_ORDENMEDICA_BY_ID_CONSULTA, query = "SELECT orden from OrdenMedica orden where orden.consulta.id=:"
		+ OrdenMedica.PARAMETRO_ID_CONSULTA)
})
public class OrdenMedica implements Serializable {

	/**
	 * Constante que representa la Named Query que buca una orden por consulta.
	 */
	public static final String FIND_ORDENMEDICA_BY_ID_CONSULTA = "OrdenMedica.findByConsula";

	/**
	 * Parametro id de la consulta de la namdeQuery
	 * FIND_ORDENMEDICA_BY_ID_CONSULTA
	 */
	public static final String PARAMETRO_ID_CONSULTA = "idConsulta";

	/**
	 * Constante de serialización.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identificador unico de la entidad.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Número de autorización del la orden medica.
	 */
	@Column(unique = true)
	@Basic(optional = false)
	private String numeroAutirizacion;

	

	/**
	 * Consulta asociada a la orden medica.
	 */
	@OneToOne(fetch = LAZY)
	@JoinColumn(name = "consulta")
	private Consulta consulta;

	/**
	 * Diagnostico de la consulta.
	 */
	@Basic(optional = false)
	private String diagnostico;

	/**
	 * Lista de detalles de la orden medica.
	 */
	@OneToMany(mappedBy = "ordenMedica", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<DetalleOrdenMedica> detalleOrdenMedica;

	/**
	 * Constructor sin parametros.
	 */
	public OrdenMedica() {
		super();
                detalleOrdenMedica=new ArrayList<DetalleOrdenMedica>();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the numeroAutirizacion
	 */
	public String getNumeroAutirizacion() {
		return numeroAutirizacion;
	}

	/**
	 * @param numeroAutirizacion
	 *            the numeroAutirizacion to set
	 */
	public void setNumeroAutirizacion(String numeroAutirizacion) {
		this.numeroAutirizacion = numeroAutirizacion;
	}

	
	/**
	 * @return the consulta
	 */
	public Consulta getConsulta() {
		return consulta;
	}

	/**
	 * @param consulta
	 *            the consulta to set
	 */
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	/**
	 * @return the diagnostico
	 */
	public String getDiagnostico() {
		return diagnostico;
	}

	/**
	 * @param diagnostico
	 *            the diagnostico to set
	 */
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	/**
	 * @return the detalleOrdenMedica
	 */
	public List<DetalleOrdenMedica> getDetalleOrdenMedica() {
		return detalleOrdenMedica;
	}

	/**
	 * @param detalleOrdenMedica
	 *            the detalleOrdenMedica to set
	 */
	public void setDetalleOrdenMedica(
			List<DetalleOrdenMedica> detalleOrdenMedica) {
		this.detalleOrdenMedica = detalleOrdenMedica;
	}

	// TODO:falta toString

}
