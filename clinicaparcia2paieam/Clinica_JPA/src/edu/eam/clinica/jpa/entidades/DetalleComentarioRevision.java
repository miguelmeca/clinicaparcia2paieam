/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eam.clinica.jpa.entidades;

import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Basic;

import edu.eam.clinica.jpa.enumeraciones.RevisionSistemaEnum;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import static javax.persistence.EnumType.STRING;

/**
 *
 * @author diana
 */

@NamedQueries({
    
                @NamedQuery(name=DetalleComentarioRevision.FIND_DETALLE_COMENTARIO_REVISION_BY_ID_HISTORIA_CLINICA, query= "select deta from  DetalleComentarioRevision deta where deta.historiaClinica.id=:"
                                +DetalleComentarioRevision.PARAMETRO_ID_HISTORIA_CLINICA),
                @NamedQuery(name=DetalleComentarioRevision.FIND_DETALLE_COMENTARIO_REVISION_BY_REVISION, query="select deta from DetalleComentarioRevision deta where deta.revision=:"
                                +DetalleComentarioRevision.PARAMETRO_REVISION),
                @NamedQuery(name=DetalleComentarioRevision.FIND_ALL, query="select deta from DetalleComentarioRevision deta")
    
})


@Entity
public class DetalleComentarioRevision implements Serializable {
    /**
	 * Constante para la named Query de buscar todos los detalle comentario
	 * sistema.
	 */
	public static final String FIND_ALL = "DetalleComentarioRevision.findAll";

	/**
	 * Constante para la named query de buscar DetalleComentarioSistema por
	 * sistema.
	 */
	public static final String FIND_DETALLE_COMENTARIO_REVISION_BY_REVISION = "DetalleComentarioRevision.findDetalleComentarioRevisionByRevision";

	/**
	 * Constante para el parametro de sistema.
	 */
	public static final String PARAMETRO_REVISION = "revision";

	/**
	 * Constante para la named query de buscar DetalleComentarioSistema por ID
	 * historia clinica.
	 */
	public static final String FIND_DETALLE_COMENTARIO_REVISION_BY_ID_HISTORIA_CLINICA = "DetalleComentarioRevision.findDetalleComentarioRevisionByIdHistoriaClinica";

	/**
	 * Constante para el parametro de id historia clinica.
	 */
	public static final String PARAMETRO_ID_HISTORIA_CLINICA = "idHistoriaClinica";

	/**
	 * Constante de serializacion
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    /**
	 * Sistema corporal del que se hace el comentario.
	 */
    @Enumerated(STRING)
    private RevisionSistemaEnum revision;
    
    
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
	public DetalleComentarioRevision() {
		super();
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoria(HistoriaClinica historia) {
        this.historiaClinica = historia;
    }

    public RevisionSistemaEnum getRevision() {
        return revision;
    }

    public void setRevision(RevisionSistemaEnum revision) {
        this.revision = revision;
    }

   
   /*
    *@Override
    *public String toString() {
        return "com.torresquintero.oncologos.entidades.DetalleComentarioRevision[ id=" + id + " ]";
    }*/
    
}
