package edu.eam.clinica.jpa.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

import edu.eam.clinica.jpa.enumeraciones.TipoProcedimietoEnum;
import static javax.persistence.EnumType.STRING;

/**
 * Entidad que representa el procedimiento.
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name=Procedimiento.FIND_PROCEDIMIENTO_BY_CODIGO,query="select proc from Procedimiento proc where proc.codigo=:"+Procedimiento.PARAMETRO_CODIGO),
	@NamedQuery(name=Procedimiento.FIND_PROCEDIMIENTO_BY_TIPO_PROCEDIMIENTO,query="select proc from Procedimiento proc where proc.tipoProcedimiento=:"+Procedimiento.PARAMETRO_TIPO_PROCEDIMIENTO),
	@NamedQuery(name=Procedimiento.FIND_PROCEDIMIENTO_BY_NOMBRE,query="select proc from Procedimiento proc where proc.nombre like :"+Procedimiento.PARAMETRO_NOMBRE_PROC+" order by proc.nombre"),
        @NamedQuery(name=Procedimiento.FIND_ALL,query="select proc from Procedimiento proc")
})
public class Procedimiento implements Serializable {
	
	/**
	 * Constante para la named Query de buscar todos los procedimientos.
	 */
	public static final String FIND_ALL="Procedimiento.findAll";
	
	/**
	 * Constante para la named Query de buscar procedimiento por codigo.
	 */
	public static final String FIND_PROCEDIMIENTO_BY_CODIGO = "Procedimiento.findProcedimientoByCodigo";
	
	/**
	 *Constante para el parametro para buscar por codigo.
	 */
	public static final String PARAMETRO_CODIGO="codigo";
	
	/**
	 * Constante para la named Query de buscar procedimiento por tipo de procedimiento.
	 */
	public static final String FIND_PROCEDIMIENTO_BY_TIPO_PROCEDIMIENTO = "Procedimiento.findProcedimientoByTipoProcedimiento";
	
	/**
	 *Constante para el parametro para buscar por tipo procedimiento.
	 */
	public static final String PARAMETRO_TIPO_PROCEDIMIENTO="tipoProcedimiento";

        
        /**
	 * Constante para la named Query de buscar procedimiento por nombre de procedimiento.
	 */
	public static final String FIND_PROCEDIMIENTO_BY_NOMBRE = "Procedimiento.findProcedimientoByNombre";
	
	/**
	 *Constante para el parametro para buscar por nombre procedimiento.
	 */
	public static final String PARAMETRO_NOMBRE_PROC="nombreProcedimiento";

        

	/**
	 * Constante de serializacion
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Identificador unico de la entidad.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	/**
	 * Tipo de procedimiento.
	 */
	@Basic(optional = false)
	@Enumerated(STRING)
	private TipoProcedimietoEnum tipoProcedimiento;
	
	/**
	 * Codigo unico del procedimiento.
	 */
	@Basic(optional = false)
	@Column(unique=true)
	private String codigo;
	
	
	@Basic(optional = false)
	private String nombre;
        
        @Basic(optional=false)
        private double precio;
        
	
	/**
	 * Lista de detalles que pueden tener este procedimiento.
	 */
	@OneToMany(mappedBy="procedimiento",fetch=FetchType.LAZY)
	private List<DetalleOrdenMedica> detalleOrdenMedica;

	
	/**
	 * Constructor sin parametros.
	 */
	public Procedimiento() {
		super();
	}

	/**
	 * @param id
	 * @param tipoProcedimiento
	 * @param codigo
	 * @param nombre
	 */
	public Procedimiento(long id, TipoProcedimietoEnum tipoProcedimiento,
			String codigo, String nombre) {
		super();
		this.id = id;
		this.tipoProcedimiento = tipoProcedimiento;
		this.codigo = codigo;
		this.nombre = nombre;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the tipoProcedimiento
	 */
	public TipoProcedimietoEnum getTipoProcedimiento() {
		return tipoProcedimiento;
	}

	/**
	 * @param tipoProcedimiento the tipoProcedimiento to set
	 */
	public void setTipoProcedimiento(TipoProcedimietoEnum tipoProcedimiento) {
		this.tipoProcedimiento = tipoProcedimiento;
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
	 * @return the detalleOrdenMedica
	 */
	public List<DetalleOrdenMedica> getDetalleOrdenMedica() {
		return detalleOrdenMedica;
	}

	/**
	 * @param detalleOrdenMedica the detalleOrdenMedica to set
	 */
	public void setDetalleOrdenMedica(List<DetalleOrdenMedica> detalleOrdenMedica) {
		this.detalleOrdenMedica = detalleOrdenMedica;
	} 
	
	public String toString(){
		return nombre;
	}

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
	
	
	
}
