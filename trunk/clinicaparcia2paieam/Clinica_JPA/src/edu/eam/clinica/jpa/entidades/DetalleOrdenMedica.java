package edu.eam.clinica.jpa.entidades;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Entidad que representa el detalle de la orden medica.
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name=DetalleOrdenMedica.FIND_DETALLE_ORDEN_MEDICA_BY_CODIGO_PROCEDIMIENTO,query="select det from DetalleOrdenMedica det where det.procedimiento.codigo=:"+DetalleOrdenMedica.PARAMETRO_CODIGO_PROCEDIMIENTO),
	@NamedQuery(name=DetalleOrdenMedica.FIND_DETALLE_ORDEN_MEDICA_BY_ID_ORDEN_MEDICA,query="select det from DetalleOrdenMedica det where det.ordenMedica.id=:"+DetalleOrdenMedica.PARAMETRO_ID_ORDEN_MEDICA),
	@NamedQuery(name=DetalleOrdenMedica.FIND_ALL,query="select det from DetalleOrdenMedica det"),
        
        @NamedQuery(name=DetalleOrdenMedica.FIND_BY_PACIENTE_NOMBRE,
        query="select det from DetalleOrdenMedica det where det.ordenMedica.consulta.paciente.documento=:"+
        DetalleOrdenMedica.PARAMETRO_PACIENTE+" and "+
        "det.procedimiento.nombre like :"+DetalleOrdenMedica.PARAMETRO_NOMBRE        
        + " order by det.ordenMedica.consulta.fechaHora")

        
})
public class DetalleOrdenMedica implements Serializable {
    
     public static final String PARAMETRO_NOMBRE="nombre";
	
        public static final String PARAMETRO_PACIENTE="paciente";
        /**
        * Consutla procedimientos por paciente.
        */
        public static final String FIND_BY_PACIENTE_NOMBRE="DetalleOrdenMedica.findByPaciente";
        /**
	 * Constante para la named Query de buscar todos los detalles de la orden medica.
	 */
	public static final String FIND_ALL = "DetalleOrdenMedica.findAll";
   
	/**
	 * Constante para la named query de buscar DetalleOrdenMedica por id de la orden medica.
	 */
	public static final String FIND_DETALLE_ORDEN_MEDICA_BY_ID_ORDEN_MEDICA="DetalleOrdenMedica.findDetalleOrdenMedicaByIdOrdenMedica";
	
	/**
	 * Constante para el parametro de id de la orden medica.
	 */
	public static final String PARAMETRO_ID_ORDEN_MEDICA="idOrdenMedica";
	
	/**
	 * Constante para la named query de buscar DetalleOrdenMedica por codigo del procedimiento.
	 */
	public static final String FIND_DETALLE_ORDEN_MEDICA_BY_CODIGO_PROCEDIMIENTO="DetalleOrdenMedica.findDetalleOrdenMedicaByCodigoProcedimiento";
	
	/**
	 * Constante para el parametro del codigo del procedimiento.
	 */
	public static final String PARAMETRO_CODIGO_PROCEDIMIENTO="codigoProcedimiento";
	
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
	 * Cantidad del detalle de la orden medica.
	 */
	@Basic(optional = false)
	private Integer cantidad;
	
	/**
	 * Observación del detalle de la orden medica.
	 */
	@Basic(optional = true)
	private String observacion;
	
	/**
	 * Orden medica a la cual esta asociada el detalle.
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ORDENMEDICA")
	private OrdenMedica ordenMedica;

	/**
	 * Procedimiento que tiene el detalle de la orden medica.
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROCEDIMIENTO")
	private Procedimiento procedimiento;
	
	/**
	 * Constructor sin parametros.
	 */
	public DetalleOrdenMedica() {
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
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the ordenMedica
	 */
	public OrdenMedica getOrdenMedica() {
		return ordenMedica;
	}

	/**
	 * @param ordenMedica the ordenMedica to set
	 */
	public void setOrdenMedica(OrdenMedica ordenMedica) {
		this.ordenMedica = ordenMedica;
	}

	/**
	 * @return the procedimento
	 */
	public Procedimiento getProcedimiento() {
		return procedimiento;
	}

	/**
	 * @param procedimento the procedimento to set
	 */
	public void setProcedimiento(Procedimiento procedimiento) {
		this.procedimiento = procedimiento;
	}   
	
	//TODO:falta toString
}
