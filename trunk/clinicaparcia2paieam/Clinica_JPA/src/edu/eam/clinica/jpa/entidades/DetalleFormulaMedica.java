package edu.eam.clinica.jpa.entidades;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entidad que representa el detalle de la formula medica.
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name=DetalleFormulaMedica.FIND_DETALLE_FORMULA_MEDICA_BY_CODIGO_ATICULO,query="select det from DetalleFormulaMedica det where det.articulo.codigo=:"+DetalleFormulaMedica.PARAMETRO_CODIGO_ARTICULO),
	@NamedQuery(name=DetalleFormulaMedica.FIND_DETALLE_FORMULA_MEDICA_BY_ID_FORMULA_MEDICA,query="select det from DetalleFormulaMedica det where det.formulaMedica.id=:"+DetalleFormulaMedica.PARAMETRO_ID_FORMULA_MEDICA),
	@NamedQuery(name=DetalleFormulaMedica.FIND_ALL,query="select det from DetalleFormulaMedica det"),
        
        @NamedQuery(name=DetalleFormulaMedica.FIND_BY_PACIENTE,
        query="select det from DetalleFormulaMedica det where det.formulaMedica.consulta.paciente.documento=:"+
        DetalleFormulaMedica.PARAMETRO_PACIENTE+
        " and det.articulo.nombre like :"+DetalleFormulaMedica.PARAMETRO_NOMBRE+
        " order by det.formulaMedica.consulta.fechaHora")

        
})
public class DetalleFormulaMedica implements Serializable {
	
         public static final String PARAMETRO_NOMBRE="nombre";
   
    
         public static final String PARAMETRO_PACIENTE="paciente";
        /**
        * Consutla procedimientos por paciente.
        */
        public static final String FIND_BY_PACIENTE="DetalleFormulaMedica.findByPaciente";

        
        /**
	 * Constante para la named Query de buscar todos los detalle formula medica.
	 */
	public static final String FIND_ALL = "DetalleFormulaMedica.findAll";
	
	/**
	 * Constante para la named query de buscar DetalleFormulaMedica por codigo articulo.
	 */
	public static final String FIND_DETALLE_FORMULA_MEDICA_BY_CODIGO_ATICULO="DetalleFormulaMedica.findDetalleFormulaMedicaByCodigoArticulo";
	
	/**
	 * Constante para el parametro de codigo articulo.
	 */
   public static final String PARAMETRO_CODIGO_ARTICULO="codigoArticulo";
   
   /**
    * Constante para la named query de buscar DetalleFormulaMedica por id de la formula medica.
    */
   public static final String FIND_DETALLE_FORMULA_MEDICA_BY_ID_FORMULA_MEDICA="DetalleFormulaMedica.findDetalleFormulaMedicaByIdFormulaMedica";
   
   /**
    * Constante para el parametro de id de la formula medica.
    */
   public static final String PARAMETRO_ID_FORMULA_MEDICA="idFormulaMedica";

	/**
	 * Constante de serialización.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identicador unico de la entidad.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Cantidad que se debe suministrar.
	 */
	@Basic(optional=false)
	private Integer cantidad;
	
	/**
	 * Prescripción del articulo.
	 */
	@Basic(optional=false)
	private String prescripcion;
	
	/**
	 * Articulo del detalle de la formuna medica.
	 */
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="ARTICULO")
	private Articulo articulo;
	
	/**
	 * Formula Medica a la cual esta asociada el detalle.
	 */
	@ManyToOne
	@JoinColumn(name="FORMULAMEDICA")
	private FormulaMedica formulaMedica;
	
	/**
	 * Constructor sin parametros.
	 */
	public DetalleFormulaMedica() {
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
	 * @return the prescripcion
	 */
	public String getPrescripcion() {
		return prescripcion;
	}


	/**
	 * @param prescripcion the prescripcion to set
	 */
	public void setPrescripcion(String prescripcion) {
		this.prescripcion = prescripcion;
	}


	/**
	 * @return the articulo
	 */
	public Articulo getArticulo() {
		return articulo;
	}


	/**
	 * @param articulo the articulo to set
	 */
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}


	/**
	 * @return the formulaMedica
	 */
	public FormulaMedica getFormulaMedica() {
		return formulaMedica;
	}


	/**
	 * @param formulaMedica the formulaMedica to set
	 */
	public void setFormulaMedica(FormulaMedica formulaMedica) {
		this.formulaMedica = formulaMedica;
	} 
	
	//TODO:falta toString
	
	
}
