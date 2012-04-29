package edu.eam.clinica.jpa.entidades;


import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import edu.eam.clinica.jpa.enumeraciones.SexoEnum;
import edu.eam.clinica.jpa.enumeraciones.TipoDocumentoEnum;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
@NamedQueries({
	@NamedQuery(name=Paciente.FIND_ALL,query="select pac from Paciente pac"),
	@NamedQuery(name=Paciente.FIND_PACIENTE_BY_NUMERO_Y_TIPO_DOCUMENTO_PACIENTE,query="select pac from Paciente pac where pac.documento=:"+Paciente.PARAMETRO_NUMERO_DOCUMENTO_PACIENTE+" and pac.tipoDocumento=:"+Paciente.PARAMETRO_TIPO_DOCUMENTO_PACIENTE)
})
public class Paciente extends Persona {
	
	/**
	 * Constante para la named Query de buscar todos los pacientes.
	 */
	public static final String FIND_ALL = "Paciente.findAll";

	/**
	 * Constante para la named Query de buscar Paciente por numero de identificacion del paciente y tipo de documento.
	 */
	public static final String FIND_PACIENTE_BY_NUMERO_Y_TIPO_DOCUMENTO_PACIENTE = "Paciente.findPacienteByNumeroYTipoDocumento";
	
	/**
	 *Constante para el parametro para buscar por numero documento del paciente.
	 */
	public static final String PARAMETRO_NUMERO_DOCUMENTO_PACIENTE="numeroDocumentoPaciente";

	/**
	 *Constante para el parametro para buscar por tipo Documento del paciente.
	 */
	public static final String PARAMETRO_TIPO_DOCUMENTO_PACIENTE="tipoDocumentoPaciente";

	/**
	 * Constante de serializacion.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * EPS del paciente.
	 */
	@JoinColumn(name="EPS")
	@ManyToOne(fetch=FetchType.LAZY)
	private EPS eps;
	
	@OneToOne(mappedBy="paciente",fetch= FetchType.LAZY,cascade= CascadeType.ALL)
        private HistoriaClinica historiaClinica;
        
        /**
	 * Lista de acudientes de la persona.
	 */
	@OneToMany(mappedBy="paciente",fetch= FetchType.LAZY,cascade= CascadeType.ALL)
	private List<Acudiente> acudientes;
        
        /**
	 * Lista de acudientes de la persona.
	 */
	@OneToMany(mappedBy="paciente",fetch= FetchType.LAZY,cascade= CascadeType.ALL)
	private List<Consulta> consultas;
	/**
	 * Ocupacion del paciente.
	 */
	@Basic(optional = true)
	private String ocupacion;
	
        @Temporal(javax.persistence.TemporalType.DATE)
	private Date fechaNacimiento;
	
	/**
	 * Constructor sin parametros.
	 */
	public  Paciente() {
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
	 * @param eps
	 * @param ocupacion
	 */
	public Paciente(String documento, String primerNombre,
			String segundoNombre, String primerApellido,
			String segundoApellido, TipoDocumentoEnum tipoDocumento,
			String direccion, String email, SexoEnum sexo, EPS eps,
			String ocupacion) {
		super(documento, primerNombre, segundoNombre, primerApellido,
				segundoApellido, tipoDocumento, direccion, email, sexo);
		this.eps = eps;
		this.ocupacion = ocupacion;
	}



	/**
	 * @return the eps
	 */
	public EPS getEps() {
		return eps;
	}



	/**
	 * @param eps the eps to set
	 */
	public void setEps(EPS eps) {
		this.eps = eps;
	}



	/**
	 * @return the acudientes
	 */
	public List<Acudiente> getAcudientes() {
		return acudientes;
	}



	/**
	 * @param acudientes the acudientes to set
	 */
	public void setAcudientes(List<Acudiente> acudientes) {
		this.acudientes = acudientes;
	}



	/**
	 * @return the ocupacion
	 */
	public String getOcupacion() {
		return ocupacion;
	}



	/**
	 * @param ocupacion the ocupacion to set
	 */
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
        
        
	
	//TODO:falta toString

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }
    
    

}
