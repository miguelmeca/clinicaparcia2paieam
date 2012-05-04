package edu.eam.clinica.jpa.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * Entidad que representa los articulos de la clinica.
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name=Articulo.FIND_ARTICULO_BY_CODIGO,query="select art from Articulo art where art.codigo=:"+Articulo.PARAMETRO_CODIGO),
    @NamedQuery(name=Articulo.FIND_ARTICULO_BY_NOMBRE,query="select art from Articulo art where art.nombre LIKE :"+Articulo.PARAMENTRO_NOMBRE),
	@NamedQuery(name=Articulo.FIND_ALL,query="select art from Articulo art "),
	@NamedQuery(name=Articulo.FIND_ARTICULO_BY_NOMBREARTI,query="select art from Articulo art where art.nombre=:"+Articulo.PARAMENTRO_NOMBREART)
})
public class Articulo implements Serializable {
	
	/**
	 * Constante para la named Query de buscar todos los Articulos.
	 */
	public static final String FIND_ALL = "Articulo.findAll";
	
	
	/**
	 * Constante para la named query de buscar articulo por nombre.
	 */
	public static final String FIND_ARTICULO_BY_NOMBRE="Articulo.findArticuloByNombre";
	
	/**
	 * Constantes para buscar un articulo por nombre
	 */
	public static final String FIND_ARTICULO_BY_NOMBREARTI="Articulo.findArticuloByNombreArti";
	/**
	 * Constante para el parametro nombre.
	 */
	public static final String PARAMENTRO_NOMBRE="nombre";
   /**
    * constante para el nombre
    */
	public static final String PARAMENTRO_NOMBREART="nombre";
   /**
	 * Constante para la named Query de buscar Articulo por codigo.
	 */
	public static final String FIND_ARTICULO_BY_CODIGO = "Articulo.findArticuloByCodigo";
	
	/**
	 *Constante para el parametro para buscar por codigo.
	 */
	public static final String PARAMETRO_CODIGO="codigo";

	/**
	 * Constante de serealizaciÃ³n.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador unico de la entidad. 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	/**
	 *Codigo del articulo.
	 */
	@Column(unique = true)
	@Basic(optional = false)
	private String codigo;
	
	/**
	 * Nombre del articulo.
	 */
	@Basic(optional = false)
	private String nombre;
	
	/**
	 * Precio del articulo.
	 */
	@Basic(optional = false)
	private double precio;
        
        @Transient
        private int cantidad;
	
	/**
	 * Lista de detalles de formula medica.
	 */
	@OneToMany(mappedBy="articulo",fetch=FetchType.LAZY)
	private List<DetalleFormulaMedica> detallesFormulaMedica;
	
	/**
	 * Inventario de este articulo.
	 */
	@OneToMany(mappedBy="articulo",fetch=FetchType.LAZY)
	private List<Inventario> inventario;


	/**
	 * Consructor sin paremeros.
	 */
	public Articulo() {
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

	/**
	 * @return the inventario
	 */
	public List<Inventario> getInventario() {
		return inventario;
	}

	/**
	 * @param inventario the inventario to set
	 */
	public void setInventario(List<Inventario> inventario) {
		this.inventario = inventario;
	}   
	
	public String toString(){
		return nombre;
	}

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
        
        
	
}


