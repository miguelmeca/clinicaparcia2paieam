package edu.eam.clinica.jpa.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import edu.eam.clinica.jpa.enumeraciones.SexoEnum;
import edu.eam.clinica.jpa.enumeraciones.TipoDocumentoEnum;

/**
 * Entidad que representa a el acudiente de un paciente
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name=Acudiente.FIND_ACUDIENTE_BY_PACIENTE,
        query="select acu from Acudiente acu where acu.paciente.documento=:"+Acudiente.PARAMETRO_ID),
	@NamedQuery(name=Acudiente.FIND_ALL,query="select acu from Acudiente acu")
})
public class Acudiente extends Persona implements Serializable {

	/**
	 * Constante para la named Query de buscar todos los acudientes.
	 */
	public static final String FIND_ALL = "Acudiente.findAll";
	
	/**
	 * Constante para la named Query de buscar Acudiente por numero de identificacion del paciente y tipo de documento.
	 */
	public static final String FIND_ACUDIENTE_BY_NUMERO_Y_TIPO_DOCUMENTO_PACIENTE = "Acudiente.findAcudienteByNumeroYTipoDocumentoPaciente";
	
	/**
	 *Constante para el parametro para buscar por numero documento del paciente.
	 */
	public static final String PARAMETRO_NUMERO_DOCUMENTO_PACIENTE="numeroDocumentoPaciente";
        
        
        /**
	 * Constante para la named Query de buscar Acudiente por numero de identificacion del paciente y tipo de documento.
	 */
	public static final String FIND_ACUDIENTE_BY_PACIENTE = "Acudiente.findAcudienteByPaciente";
	
	/**
	 *Constante para el parametro para buscar por numero documento del paciente.
	 */
	public static final String PARAMETRO_ID="id";

	/**
	 *Constante para el parametro para buscar por tipo Documento del paciente.
	 */
	public static final String PARAMETRO_TIPO_DOCUMENTO_PACIENTE="tipoDocumentoPaciente";

	
	/**
	 * 
	 * Constante de serializacion.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Paciente del que esta entidad es acudiente.
	 */
	@JoinColumn(name="PACIENTE")
	@ManyToOne
	private Paciente paciente;
	
	
	public Acudiente() {
		super();
	}

	/**
	 * @param documento
	 * @param primerNombre
	 * @param segundoNombre
	 * @param primerApellido
	 * @param segundoApellido
	 * @param tipoDocumento
	 * @param direccion
	 * @param email
	 * @param sexo
	 * @param paciente
	 */
	public Acudiente(String documento, String primerNombre,
			String segundoNombre, String primerApellido,
			String segundoApellido, TipoDocumentoEnum tipoDocumento,
			String direccion, String email, SexoEnum sexo, Paciente paciente) {
		super(documento, primerNombre, segundoNombre, primerApellido,
				segundoApellido, tipoDocumento, direccion, email, sexo);
		this.paciente = paciente;
	}

	/**
	 * @return the paciente
	 */
	public Paciente getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente the paciente to set
	 */
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	//TODO:falta toString
	
}
