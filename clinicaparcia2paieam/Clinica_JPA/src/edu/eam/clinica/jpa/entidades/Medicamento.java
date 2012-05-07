package edu.eam.clinica.jpa.entidades;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

import edu.eam.clinica.jpa.enumeraciones.GrupoTerapeuticoEnum;
import edu.eam.clinica.jpa.enumeraciones.PresentacionEnum;

/**
 * Entidad que representa el medicamento.
 *
 */
@Entity
/**
 * @ciza93@gmail.com cree esta query para buscar lso medicamentos por nombre y por ende dos constantes
 */
@NamedQuery(name=Medicamento.FIND_MEDICAMENTO_BY_NOMBRE,query="select med from Medicamento med where med.nombre LIKE :"+Medicamento.PARAMENTRO_NOMBRE)

public class Medicamento extends Articulo {

	/**
	 * Constante de serialización.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constante para el parametro nombre.
	 */
	public static final String PARAMENTRO_NOMBRE="nombre";
	/**
	 * Constante para la named query de buscar medicamento por nombre.
	 */
	public static final String FIND_MEDICAMENTO_BY_NOMBRE="Medicamento.findMedicamentoByNombre";
	/**
	 * Grupo terapeutico del medicamento.
	 */
	@Basic(optional=false)
	@Enumerated(EnumType.STRING)
	private GrupoTerapeuticoEnum grupoTerapeutico;
	
	/**
	 * Registro Invima del medicamento.
	 */
	@Basic(optional=false)
	private String registroInvima;
	
	/**
	 * Presentación del medicamento.
	 */
	@Basic(optional=false)
	@Enumerated(EnumType.STRING)
	private PresentacionEnum presentacion;
	
	/**
	 * Consentración del medicamento.
	 */
	@Basic(optional=false)
	private String consentracion;
	
	/**
	 * Laboratirio donde se creo el medicamento.
	 */
	@ManyToOne
	@JoinColumn(name="LABORATORIO")
	private Laboratorio laboratorio;
	
	
	
	/**
	 * Fecha de vencimiento del medicamento.
	 */
	@Temporal(TIMESTAMP)
	@Basic(optional=false)
	private Date fechaVencimieno;
	
	/**
	 * Si el medicamento se tiene que autorizar o no.
	 */
	@Basic(optional=false)
	private boolean noPos;
	
	/**
	 * Constructor sin parametros.
	 */
	public Medicamento() {
		super();
	}

    public Medicamento(GrupoTerapeuticoEnum grupoTerapeutico, String registroInvima, PresentacionEnum presentacion, String consentracion, Laboratorio laboratorio, Date fechaVencimieno, boolean noPos) {
        this.grupoTerapeutico = grupoTerapeutico;
        this.registroInvima = registroInvima;
        this.presentacion = presentacion;
        this.consentracion = consentracion;
        this.laboratorio = laboratorio;
        this.fechaVencimieno = fechaVencimieno;
        this.noPos = noPos;
    }
	
        
        
	/**
	 * @return the grupoTerapeutico
	 */
	public GrupoTerapeuticoEnum getGrupoTerapeutico() {
		return grupoTerapeutico;
	}

	/**
	 * @param grupoTerapeutico the grupoTerapeutico to set
	 */
	public void setGrupoTerapeutico(GrupoTerapeuticoEnum grupoTerapeutico) {
		this.grupoTerapeutico = grupoTerapeutico;
	}

	/**
	 * @return the registroInvima
	 */
	public String getRegistroInvima() {
		return registroInvima;
	}

	/**
	 * @param registroInvima the registroInvima to set
	 */
	public void setRegistroInvima(String registroInvima) {
		this.registroInvima = registroInvima;
	}

	/**
	 * @return the presentacion
	 */
	public PresentacionEnum getPresentacion() {
		return presentacion;
	}

	/**
	 * @param presentacion the presentacion to set
	 */
	public void setPresentacion(PresentacionEnum presentacion) {
		this.presentacion = presentacion;
	}

	/**
	 * @return the consentracion
	 */
	public String getConsentracion() {
		return consentracion;
	}

	/**
	 * @param consentracion the consentracion to set
	 */
	public void setConsentracion(String consentracion) {
		this.consentracion = consentracion;
	}

	/**
	 * @return the laboratorio
	 */
	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	/**
	 * @param laboratorio the laboratorio to set
	 */
	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}

	/**
	 * @return the detallesFabricante
	 */
	
	

	/**
	 * @return the fechaVencimieno
	 */
	public Date getFechaVencimieno() {
		return fechaVencimieno;
	}

	/**
	 * @param fechaVencimieno the fechaVencimieno to set
	 */
	public void setFechaVencimieno(Date fechaVencimieno) {
		this.fechaVencimieno = fechaVencimieno;
	}

	/**
	 * @return the noPos
	 */
	public boolean isNoPos() {
		return noPos;
	}

	/**
	 * @param noPos the noPos to set
	 */
	public void setNoPos(boolean noPos) {
		this.noPos = noPos;
	} 
	
	//TODO:falta toString
	
}
