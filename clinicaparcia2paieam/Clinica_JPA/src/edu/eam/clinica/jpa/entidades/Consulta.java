package edu.eam.clinica.jpa.entidades;

import static javax.persistence.EnumType.STRING;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

import edu.eam.clinica.jpa.enumeraciones.EstadoConsultaEnum;
import edu.eam.clinica.jpa.enumeraciones.MotivoConsultaEnum;
import edu.eam.clinica.jpa.enumeraciones.TipoProcedimietoEnum;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Consulta
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Consulta.FIND_CONSULTA_BY_NUMERO_Y_TIPO_DOCUMENTO, query = "select con from Consulta con where con.paciente.documento=:" + Consulta.PARAMETRO_NUMERO_DOCUMENTO + " and con.paciente.tipoDocumento=:" + Consulta.PARAMETRO_TIPO_DOCUMENTO),
    @NamedQuery(name = Consulta.FIND_CONSULTA_BY_REGISTRO_MEDICO, query = "select con from Consulta con where con.medico.registroMedico=:" + Consulta.PARAMETRO_REGISTRO_MEDICO),
    @NamedQuery(name = Consulta.FIND_CONSULA_BY_TIPO_PROCEDIMIENTO, query = "select con from Consulta con where con.procedimiento=:" + Consulta.PARAMETRO_TIPO_PROCEDIMIENTO),
    @NamedQuery(name = Consulta.FIND_CONSULTA_BY_ESTADO_ACTIVO, query = "select con from Consulta con "),//TODO
    @NamedQuery(name = Consulta.FIND_CONSULTA_BY_MEDICO_AND_FECHAS, query = "select con from Consulta con where con.medico.registroMedico=:" + Consulta.PARAMETRO_REGISTRO_MEDICO + " and con.fechaHora between :" + Consulta.PARAMETRO_MENOR_FECHA + " and :" + Consulta.PARAMETRO_MAYOR_FECHA),
    @NamedQuery(name = Consulta.FIND_ALL, query = "select con from Consulta con")
})
public class Consulta implements Serializable {

    /**
     * Constante para la named Query de buscar todas las Consultas.
     */
    public static final String FIND_ALL = "Consulta.findAll";
    /**
     * Constante para la named query de buscar consulta por medico y entre dos fechas.
     */
    public static final String FIND_CONSULTA_BY_MEDICO_AND_FECHAS = "Consulta.findConsultaByMedicoAndEntreFechas";
    /**
     * Constante para el parametro de menor fecha.
     */
    public static final String PARAMETRO_MENOR_FECHA = "menorFecha";
    /**
     * Constante para el parametro de mayor fecha.
     */
    public static final String PARAMETRO_MAYOR_FECHA = "mayorFecha";
    /**
     * Constante para la named query de buscar consulta por estado=ACTIVO.
     */
    public static final String FIND_CONSULTA_BY_ESTADO_ACTIVO = "Consulta.findConsultaByEstadoActivo";
    /**
     * Constante para la named Query de buscar Consula por tipo de procedimiento.
     */
    public static final String FIND_CONSULA_BY_TIPO_PROCEDIMIENTO = "Consulta.findConsultaByTipoPrcedimiento";
    /**
     * Constante para el parametro de tipo de procedimiento.
     */
    public static final String PARAMETRO_TIPO_PROCEDIMIENTO = "tipoProcedimiento";
    /**
     * Constante para la named Query de buscar medico por registro medico.
     */
    public static final String FIND_CONSULTA_BY_REGISTRO_MEDICO = "Consulta.findConsultaByRegistroMedico";
    /**
     * Constante para el parametro de registro medico.
     */
    public static final String PARAMETRO_REGISTRO_MEDICO = "registroMedico";
    /**
     * Constante para la named Query de buscar Consulta por numero de identificacion de la persona y tipo de documento.
     */
    public static final String FIND_CONSULTA_BY_NUMERO_Y_TIPO_DOCUMENTO = "Consulta.findConsultaByNumeroYTipoDocumento";
    /**
     *Constante para el parametro para buscar por numero documento de la persona.
     */
    public static final String PARAMETRO_NUMERO_DOCUMENTO = "numeroDocumento";
    /**
     *Constante para el parametro para buscar por tipo Documento de la persona.
     */
    public static final String PARAMETRO_TIPO_DOCUMENTO = "tipoDocumento";
    /**
     * Constante de serializacion
     */
    private static final long serialVersionUID = 1L;
    /**
     * Identificador unico de la entidad.  
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    /**
     * fecha y hora de la consulta.
     */
    @Temporal(TIMESTAMP)
    @Basic(optional = false)
    private Date fechaHora;
    /**
     * paciente que expide la consulta.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PACIENTE")
    private Paciente paciente;
    /**
     * Medico que atendera la cita.
     */
    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "MEDICO")
    private Medico medico;
    /**
     * Motivo de la consulta.
     */
    @Basic(optional = false)
    @Enumerated(STRING)
    private MotivoConsultaEnum motivo;
    /**
     * Procedimiento que se llevara a cabo en la consulta.
     */
    @Basic(optional = true)
    @Enumerated(STRING)
    private TipoProcedimietoEnum procedimiento;
    
    @Column(columnDefinition="BOOL")
    private boolean unidad;
    
    /**
     * Remision de la consulta.
     */
    
    @OneToMany(mappedBy="consulta",fetch=LAZY,cascade=CascadeType.ALL)
    private List<Remision> remisiones;
    
    
	/**
     * Orden medica que tiene la consulta.
     */
    @OneToOne(mappedBy = "consulta", fetch = LAZY,cascade= CascadeType.ALL)
    private OrdenMedica ordenMedica;
    /**
     * Formula medica que tiene la consulta.
     */
    @OneToOne(mappedBy = "consulta", fetch = LAZY,cascade= CascadeType.ALL)
    private FormulaMedica formulanMedica;
    /**
     * detalleHistoria que se escribe en esta cita.
     */
    @OneToOne(mappedBy = "consulta", fetch = LAZY,cascade= CascadeType.ALL)
    private DetalleHistoriaClinica detalleHistoriaClinica;
    /**
     * estado de la consulta.
     */
    @Enumerated(STRING)
    private EstadoConsultaEnum estado;

    public Consulta() {
        super();
    }

    /**
     * @param id
     * @param fechaHora
     * @param paciente
     * @param medico
     * @param motivo
     * @param procedimiento
     */
    public Consulta(long id, Date fechaHora, Paciente paciente, Medico medico,
            MotivoConsultaEnum motivo, TipoProcedimietoEnum procedimiento) {
        super();
        this.id = id;
        this.fechaHora = fechaHora;
        this.paciente = paciente;
        this.medico = medico;
        this.motivo = motivo;
        this.procedimiento = procedimiento;
    }
    
    /**
	 * @return the remision
	 */
	public List<Remision> getRemisiones() {
		return remisiones;
	}

	/**
	 * @param remision the remision to set
	 */
	public void setRemisiones(List<Remision> remision) {
		this.remisiones = remision;
	}

	/**
	 * @return the unidad
	 */
	public boolean isUnidad() {
		return unidad;
	}

	/**
	 * @param unidad the unidad to set
	 */
	public void setUnidad(boolean unidad) {
		this.unidad = unidad;
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
     * @return the fechaHora
     */
    public Date getFechaHora() {
        return fechaHora;
    }

    /**
     * @param fechaHora the fechaHora to set
     */
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
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

    /**
     * @return the medico
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     * @param medico the medico to set
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    /**
     * @return the motivo
     */
    public MotivoConsultaEnum getMotivo() {
        return motivo;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(MotivoConsultaEnum motivo) {
        this.motivo = motivo;
    }

    /**
     * @return the procedimiento
     */
    public TipoProcedimietoEnum getProcedimiento() {
        return procedimiento;
    }

    /**
     * @param procedimiento the procedimiento to set
     */
    public void setProcedimiento(TipoProcedimietoEnum procedimiento) {
        this.procedimiento = procedimiento;
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
     * @return the formulanMedica
     */
    public FormulaMedica getFormulanMedica() {
        return formulanMedica;
    }

    /**
     * @param formulanMedica the formulanMedica to set
     */
    public void setFormulanMedica(FormulaMedica formulanMedica) {
        this.formulanMedica = formulanMedica;
    }

    /**
     * @return the detalleHistoriaClinica
     */
    public DetalleHistoriaClinica getDetalleHistoriaClinica() {
        return detalleHistoriaClinica;
    }

    /**
     * @param detalleHistoriaClinica the detalleHistoriaClinica to set
     */
    public void setDetalleHistoriaClinica(DetalleHistoriaClinica detalleHistoriaClinica) {
        this.detalleHistoriaClinica = detalleHistoriaClinica;
    }

    //TODO:falta toString
    public EstadoConsultaEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoConsultaEnum estado) {
        this.estado = estado;
    }
}
