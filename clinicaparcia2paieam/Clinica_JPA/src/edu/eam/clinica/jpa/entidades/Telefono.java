/**
 * 
 */
package edu.eam.clinica.jpa.entidades;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import edu.eam.clinica.jpa.enumeraciones.TipoTelefonoEnum;

/**
 * Entidad para almacenar el telefono de una persona.
 * @author Camilo Andres
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name=Telefono.FIND_TELEFONO_BY_NUMERO,query="select tel from Telefono tel where tel.numero=:"+Telefono.PARAMETRO_NUMERO),
	@NamedQuery(name=Telefono.FIND_TELEFONO_BY_NUMERO_Y_TIPO_DOCUMENTO,query="select tel from Telefono tel where tel.persona.documento=:"+Telefono.PARAMETRO_NUMERO_DOCUMENTO+" and tel.persona.tipoDocumento=:"+Telefono.PARAMETRO_TIPO_DOCUMENTO),
    @NamedQuery(name=Telefono.FIND_ALL,query="select tel from Telefono tel")
})
public class Telefono {
	
	/**
	 * Constante para la named Query de buscar todos los telefonos.
	 */
	public static final String FIND_ALL="Telefono.findAll";
	
	/**
	 * Constante para la named Query de buscar Telefono por numero de identificacion de la persona y tipo de documento.
	 */
	public static final String FIND_TELEFONO_BY_NUMERO_Y_TIPO_DOCUMENTO = "Telefono.findTelefonoByNumeroYTipoDocumento";
	
	/**
	 *Constante para el parametro para buscar por numero documento de la persona.
	 */
	public static final String PARAMETRO_NUMERO_DOCUMENTO="numeroDocumento";

	/**
	 *Constante para el parametro para buscar por tipo Documento de la persona.
	 */
	public static final String PARAMETRO_TIPO_DOCUMENTO="tipoDocumento";

	
	/**
	 * Constante para la named Query de buscar Telefono por numero.
	 */
	public static final String FIND_TELEFONO_BY_NUMERO = "Telefono.findTelefonoByNumero";
	
	/**
	 *Constante para el parametro para buscar por numero.
	 */
	public static final String PARAMETRO_NUMERO="numero";

	/**
	 * Identificado unico del telefono.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	/**
	 * Numero de telefono.
	 */
	@Basic(optional=false)
	private String numero;
	
	/**
	 * Persona dueña del telefono.
	 */
	@ManyToOne
	@JoinColumn(name="PERSONA")
	private Persona persona;
	
	/**
	 * Tipo de telefono.
	 */
	@Enumerated(EnumType.STRING)
	private TipoTelefonoEnum tipo;
	
	
	
	
	public Telefono() {
		super();
	}

	/**
	 * Constructor
	 * @param numero numero de telefono
	 * @param persona persona dueña del telefono
	 * @param tipo tipo de telefono.
	 */
	public Telefono(String numero, Persona persona, TipoTelefonoEnum tipo) {
		super();
		this.numero = numero;
		this.persona = persona;
		this.tipo = tipo;
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
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona the persona to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/**
	 * @return the tipo
	 */
	public TipoTelefonoEnum getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoTelefonoEnum tipo) {
		this.tipo = tipo;
	}
	
	
	public String toString(){
		return numero;
	}

}
