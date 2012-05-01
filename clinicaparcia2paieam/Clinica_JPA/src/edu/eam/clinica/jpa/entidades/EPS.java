package edu.eam.clinica.jpa.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entidad para la EPS
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name=EPS.FIND_EPS_BY_CODIGO,query="select ep from EPS ep where ep.codigo=:"+EPS.PARAMETRO_CODIGO),
	@NamedQuery(name=EPS.FIND_ALL,query="select ep from EPS ep")
})
public class EPS implements Serializable {
	
	/**
	 * Constante para la named Query de buscar todos las EPS.
	 */
	public static final String FIND_ALL="EPS.findAll";
	
	/**
	 * Constante para la named Query de buscar EPS por codigo.
	 */
	public static final String FIND_EPS_BY_CODIGO = "EPS.findEPSByCodigo";
	
	/**
	 *Constante para el parametro para buscar por codigo.
	 */
	public static final String PARAMETRO_CODIGO="codigo";
	

	/**
	 * constante de serailizacion
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identificador unico de la entidad.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	/**
	 * Codigo de la EPS
	 */
	@Column(unique=true)
	@Basic(optional=false)
	private String codigo;
	
	/**
	 * Nombre de la EPS.
	 */
	@Basic(optional=false)
	private String nombre;
	
	@OneToMany(mappedBy="eps",fetch=FetchType.LAZY)
	private List<Paciente> pacientes;   
        
        

	
	/**
	 * Constructor sin parametros
	 */
	public EPS() {
		super();
	}
	/**
	 * @param id
	 * @param codigo
	 * @param nombre
	 */
	public EPS(long id, String codigo, String nombre) {
		super();
		this.id = id;
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
	 * @return the pacientes
	 */
	public List<Paciente> getPacientes() {
		return pacientes;
	}
	/**
	 * @param pacientes the pacientes to set
	 */
	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	
   
	public String toString(){
		return codigo+"-"+nombre;
	}

    @Override
    public boolean equals(Object obj) {
        EPS eps=(EPS) obj;
        return codigo.equals(eps.codigo);
    }
        
        
	
}
