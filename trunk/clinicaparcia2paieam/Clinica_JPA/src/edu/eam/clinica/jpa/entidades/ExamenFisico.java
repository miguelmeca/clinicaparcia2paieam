package edu.eam.clinica.jpa.entidades;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

import edu.eam.clinica.jpa.enumeraciones.ECEnum;
import edu.eam.clinica.jpa.enumeraciones.EstadificacionMEnum;
import edu.eam.clinica.jpa.enumeraciones.EstadificacionNEnum;
import edu.eam.clinica.jpa.enumeraciones.EstadificacionTEnum;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Entity que representa el examen fisico.
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name=ExamenFisico.FIND_EXAMEN_FISICO_BY_ID_HISTORIA_CLINICA,query="select exa from ExamenFisico exa where exa.historiaClinica.id=:"+ExamenFisico.PARAMETRO_ID_HISTORIA_CLINICA),
	@NamedQuery(name=ExamenFisico.FIND_ALL,query="select exa from ExamenFisico exa")
})
public class ExamenFisico implements Serializable {
	
	/**
	 * Constante para la named Query de buscar todos los examenes fisicos.
	 */
	public static final String FIND_ALL = "ExamenFisico.findAll";
	
	/**
	 * Constante para la named query de buscar ExamenFisico por id de la historia clinica.
	 */
	public static final String FIND_EXAMEN_FISICO_BY_ID_HISTORIA_CLINICA="ExamenFisico.findExamenFisicoByIdHistoriaClinica";
	
	/**
	 * Constante para el parametro de id de la historia clinica.
	 */
	public static final String PARAMETRO_ID_HISTORIA_CLINICA="idHistoriaClinica";
	
	/**
	 * Constante de serializacion
	 */
	private static final long serialVersionUID = 1L; 
	
	/**
	 * Identificador unico de la entidad.
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	/**
	 * Presion Arterial del paciente.
	 */
	@Basic(optional = false)
	private String presionArterial;
	
	/**
	 * Frecuencia cardiaca del paciente.
	 */
	@Basic(optional = false)
	private String frecuenciaCardiaca;
	
	/**
	 * Frecuencia respiratoria del paciente.
	 */
	@Basic(optional = false)
	private String frecuenciaRespiratoria;
	
	/**
	 * talla del paciente.
	 */
	@Basic(optional = false)
	private double talla;
	
	/**
	 *Temperatura del paciente.
	 */
	@Basic(optional = false)
	private String temperatura;
	
	/**
	 * peso  del paciente.
	 */
	@Basic(optional = false)
	private double peso;
	
	/**
	 * estadificacio  del paciente.
	 */
	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private EstadificacionTEnum estadificacionT;
        
        @Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private EstadificacionMEnum estadificacionM;
        
        @Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private EstadificacionNEnum estadificacionN;
        
         @Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private ECEnum EC;
         @Column(columnDefinition="text")
         private String observacion;

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public ECEnum getEC() {
        return EC;
    }

    public void setEC(ECEnum EC) {
        this.EC = EC;
    }

    public EstadificacionMEnum getEstadificacionM() {
        return estadificacionM;
    }

    public void setEstadificacionM(EstadificacionMEnum estadificacionM) {
        this.estadificacionM = estadificacionM;
    }

    public EstadificacionNEnum getEstadificacionN() {
        return estadificacionN;
    }

    public void setEstadificacionN(EstadificacionNEnum estadificacionN) {
        this.estadificacionN = estadificacionN;
    }

    public EstadificacionTEnum getEstadificacionT() {
        return estadificacionT;
    }

    public void setEstadificacionT(EstadificacionTEnum estadificacionT) {
        this.estadificacionT = estadificacionT;
    }
	
	/**
	 * conducto del paciente.
	 */
	@Basic(optional = false)
        @Column(columnDefinition="text")
	private String conducta;
	
	/**
	 * historia clinica asociada al examen.
	 */
	@OneToOne(optional = false)
	@JoinColumn(name="HISTORIA_CLINICA")
	private HistoriaClinica historiaClinica;

	/**
	 * Constructtor sin parametros.
	 */
	public ExamenFisico() {
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
	 * @return the presionArterial
	 */
	public String getPresionArterial() {
		return presionArterial;
	}

	/**
	 * @param presionArterial the presionArterial to set
	 */
	public void setPresionArterial(String presionArterial) {
		this.presionArterial = presionArterial;
	}

	/**
	 * @return the frecuenciaCardiaca
	 */
	public String getFrecuenciaCardiaca() {
		return frecuenciaCardiaca;
	}

	/**
	 * @param frecuenciaCardiaca the frecuenciaCardiaca to set
	 */
	public void setFrecuenciaCardiaca(String frecuenciaCardiaca) {
		this.frecuenciaCardiaca = frecuenciaCardiaca;
	}

	/**
	 * @return the frecuenciaRespiratoria
	 */
	public String getFrecuenciaRespiratoria() {
		return frecuenciaRespiratoria;
	}

	/**
	 * @param frecuenciaRespiratoria the frecuenciaRespiratoria to set
	 */
	public void setFrecuenciaRespiratoria(String frecuenciaRespiratoria) {
		this.frecuenciaRespiratoria = frecuenciaRespiratoria;
	}

	/**
	 * @return the talla
	 */
	public double getTalla() {
		return talla;
	}

	/**
	 * @param talla the talla to set
	 */
	public void setTalla(double talla) {
		this.talla = talla;
	}

	/**
	 * @return the temperatura
	 */
	public String getTemperatura() {
		return temperatura;
	}

	/**
	 * @param temperatura the temperatura to set
	 */
	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}

	

	/**
	 * @return the conducta
	 */
	public String getConducta() {
		return conducta;
	}

	/**
	 * @param conducta the conducta to set
	 */
	public void setConducta(String conducta) {
		this.conducta = conducta;
	}

	/**
	 * @return the historiaClinica
	 */
	public HistoriaClinica getHistoriaClinica() {
		return historiaClinica;
	}

	/**
	 * @param historiaClinica the historiaClinica to set
	 */
	public void setHistoriaClinica(HistoriaClinica historiaClinica) {
		this.historiaClinica = historiaClinica;
	}   
	
	//TODO:falta toString
  
}
