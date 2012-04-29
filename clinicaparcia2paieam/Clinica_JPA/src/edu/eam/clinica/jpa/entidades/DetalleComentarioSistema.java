package edu.eam.clinica.jpa.entidades;


import edu.eam.clinica.jpa.entidades.HistoriaClinica;
import edu.eam.clinica.jpa.enumeraciones.SistemaEnum;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import static javax.persistence.EnumType.STRING;

/**
 * Entidad que representa los detalles de los comentarios al sistema.
 * 
 */

@NamedQueries({
		@NamedQuery(name = DetalleComentarioSistema.FIND_DETALLE_COMENTARIO_SISTEMA_BY_ID_HISTORIA_CLINICA, query = "select det from DetalleComentarioSistema det where det.historiaClinica.id=:"
				+ DetalleComentarioSistema.PARAMETRO_ID_HISTORIA_CLINICA),
		@NamedQuery(name = DetalleComentarioSistema.FIND_DETALLE_COMENTARIO_SISTEMA_BY_SISTEMA, query = "select det from DetalleComentarioSistema det where det.sistema=:"
				+ DetalleComentarioSistema.PARAMETRO_SISTEMA),
		@NamedQuery(name = DetalleComentarioSistema.FIND_ALL, query = "select det from DetalleComentarioSistema det")
})
@Entity
public class DetalleComentarioSistema implements Serializable {

	/**
	 * Constante para la named Query de buscar todos los detalle comentario
	 * sistema.
	 */
	public static final String FIND_ALL = "DetalleComentarioSistema.findAll";

	/**
	 * Constante para la named query de buscar DetalleComentarioSistema por
	 * sistema.
	 */
	public static final String FIND_DETALLE_COMENTARIO_SISTEMA_BY_SISTEMA = "DetalleComentarioSistema.findDetalleComentarioSistemaBySistema";

	/**
	 * Constante para el parametro de sistema.
	 */
	public static final String PARAMETRO_SISTEMA = "sistema";

	/**
	 * Constante para la named query de buscar DetalleComentarioSistema por ID
	 * historia clinica.
	 */
	public static final String FIND_DETALLE_COMENTARIO_SISTEMA_BY_ID_HISTORIA_CLINICA = "DetalleComentarioSistema.findDetalleComentarioSistemaByIdHistoriaClinica";

	/**
	 * Constante para el parametro de id historia clinica.
	 */
	public static final String PARAMETRO_ID_HISTORIA_CLINICA = "idHistoriaClinica";

	/**
	 * Constante de serializacion
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identificador unico de la entidad.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Sistema corporal del que se hace el comentario.
	 */
	@Enumerated(STRING)
	private SistemaEnum sistema;

	/**
	 * comentario
	 */
	@Basic(optional = false)
	private String comentario;

	/**
	 * Historia clinica asociada
	 */
	@JoinColumn(name = "HISTORIA")
	@ManyToOne
	private HistoriaClinica historiaClinica;

	/**
	 * Constructor sin parametros.
	 */
	public DetalleComentarioSistema() {
		super();
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
	 * @return the sistema
	 */
	public SistemaEnum getSistema() {
		return sistema;
	}

	/**
	 * @param sistema
	 *            the sistema to set
	 */
	public void setSistema(SistemaEnum sistema) {
		this.sistema = sistema;
	}

	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * @param comentario
	 *            the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * @return the historia
	 */
	public HistoriaClinica getHistoria() {
		return historiaClinica;
	}

	/**
	 * @param historia
	 *            the historia to set
	 */
	public void setHistoria(HistoriaClinica historia) {
		this.historiaClinica = historia;
	}

	//TODO:falta ToString
}
