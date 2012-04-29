package edu.eam.clinica.jpa.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

/**
 * Entidad que representa la historia clinica.
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name = HistoriaClinica.FIND_ALL, query = "select his from HistoriaClinica his"),
    @NamedQuery(name = HistoriaClinica.FIND_HISTORIA_CLINICA_BY_NUMERO_Y_TIPO_DOCUMENTO_PACIENTE, query = "select his from HistoriaClinica his where his.paciente.documento=:" + HistoriaClinica.PARAMETRO_NUMERO_DOCUMENTO_PACIENTE + " and his.paciente.tipoDocumento=:" + HistoriaClinica.PARAMETRO_TIPO_DOCUMENTO_PACIENTE)
})
public class HistoriaClinica implements Serializable {

    /**
     * Constante para la named Query de buscar todas las historias clinicas.
     */
    public static final String FIND_ALL = "HistoriaClinica.findAll";
    /**
     * Constante para la named Query de buscar historia clinica por numero de identificacion del paciente y tipo de documento.
     */
    public static final String FIND_HISTORIA_CLINICA_BY_NUMERO_Y_TIPO_DOCUMENTO_PACIENTE = "HistoriaClinica.findHistoriaClinicaByNumeroYTipoDocumentoPaciente";
    /**
     *Constante para el parametro para buscar por numero documento del paciente.
     */
    public static final String PARAMETRO_NUMERO_DOCUMENTO_PACIENTE = "numeroDocumentoPaciente";
    /**
     *Constante para el parametro para buscar por tipo Documento del paciente.
     */
    public static final String PARAMETRO_TIPO_DOCUMENTO_PACIENTE = "tipoDocumentoPaciente";
    /**
     * Constante de Serializacion
     */
    private static final long serialVersionUID = 1L;
    /**
     * identificador unico de la entidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Paciente dueño de la historia clinica
     */
    @JoinColumn(name = "PACIENTE")
    @OneToOne(optional = false)
    private Paciente paciente;
    /**
     * fecha de apertura de la historia clinica.
     */
    @Temporal(TIMESTAMP)
    @Basic(optional = false)
    private Date fechaApertura;
    /**
     * Examen asociado a la historia clinica.
     */
    @OneToOne(mappedBy = "historiaClinica", cascade = ALL, fetch = FetchType.LAZY)
    private ExamenFisico examen;
    /**
     * antecedentes en la historia clinica.
     */
    @Basic(optional = false)
    private String antecedentes;
    /**
     * lista de comentarios del sistema corporal.
     */
    @OneToMany(fetch = LAZY, cascade = ALL, mappedBy = "historiaClinica")
    private List<DetalleComentarioSistema> detalleComentarioSistema;
    /**
     * lista de comentarios del sistema corporal.
     */
    
    @OneToMany (fetch = LAZY, cascade = ALL, mappedBy = "historiaClinica")
    private List<DetalleComentarioRevision> detalleComentarioRevision;
    
   
    @OneToMany(mappedBy = "historia",cascade=ALL,fetch= FetchType.LAZY)
    private List<Antecedente> antecedentesMedicos;
    
    
   
    
    /**
     * lista de detalles de la historia clinica.
     */
    @OneToMany(fetch = LAZY, cascade = ALL, mappedBy = "historia")
    private List<DetalleHistoriaClinica> detalleHistoriaClinica;

    public HistoriaClinica() {
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
     * @return the fechaApertura
     */
    public Date getFechaApertura() {
        return fechaApertura;
    }

    /**
     * @param fechaApertura the fechaApertura to set
     */
    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    /**
     * @return the examen
     */
    public ExamenFisico getExamen() {
        return examen;
    }

    /**
     * @param examen the examen to set
     */
    public void setExamen(ExamenFisico examen) {
        this.examen = examen;
    }

    /**
     * @return the antecedentes
     */
    public String getAntecedentes() {
        return antecedentes;
    }

    /**
     * @param antecedentes the antecedentes to set
     */
    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    /**
     * @return the detalleComentarioSistema
     */
    public List<DetalleComentarioSistema> getDetalleComentarioSistema() {
        return detalleComentarioSistema;
    }

   

    public List<Antecedente> getAntecedentesMedicos() {
        return antecedentesMedicos;
    }

    public void setAntecedentesMedicos(List<Antecedente> antecedentesMedicos) {
        this.antecedentesMedicos = antecedentesMedicos;
    }

    /**
     * @param detalleComentarioSistema the detalleComentarioSistema to set
     */
    public void setDetalleComentarioSistema(
            List<DetalleComentarioSistema> detalleComentarioSistema) {
        this.detalleComentarioSistema = detalleComentarioSistema;
    }
    
    public void setDetallecomenterioRevision(
            List<DetalleComentarioRevision>detalleComentarioRevision){
            this.detalleComentarioRevision = detalleComentarioRevision;
    }

    /**
     * @return the detalleHistoriaClinica
     */
    public List<DetalleHistoriaClinica> getDetalleHistoriaClinica() {
        return detalleHistoriaClinica;
    }

    /**
     * @param detalleHistoriaClinica the detalleHistoriaClinica to set
     */
    public void setDetalleHistoriaClinica(List<DetalleHistoriaClinica> detalleHistoriaClinica) {
        this.detalleHistoriaClinica = detalleHistoriaClinica;
    }
    //TODO:falta toString
}
