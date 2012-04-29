package edu.eam.clinica.jpa.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * Entidad que representa el detalle historia clinica.
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name = DetalleHistoriaClinica.FIND_DETALLE_HISTORIA_CLINICA_BY_ID_CONSULTA, query = "select det from DetalleHistoriaClinica det where det.consulta.id=:" + DetalleHistoriaClinica.PARAMETRO_ID_CONSULTA),
    @NamedQuery(name = DetalleHistoriaClinica.FIND_DETALLE_HISTORIA_CLINICA_BY_ID_HISTORIA_CLINICA,
    query = "select det from DetalleHistoriaClinica det where det.historia.id=:"
    + DetalleHistoriaClinica.PARAMETRO_ID_HISTORIA_CLINICA + " "
    + "order by det.consulta.fechaHora"),
    @NamedQuery(name = DetalleHistoriaClinica.FIND_DETALLE_HISTORIA_CLINICA_ORDENADO_POR_FECHA, query = "SELECT det FROM DetalleHistoriaClinica det ORDER BY det.historia.fechaApertura ASC"),
    @NamedQuery(name = DetalleHistoriaClinica.FIND_ALL, query = "select det from DetalleHistoriaClinica det")
//TODO:NamedQuery de todos los detalles de una historia ordenados por fecha de mayor a menor.
})
public class DetalleHistoriaClinica implements Serializable {

    /**
     * Constante para la named Query de buscar todos los detalles de historias clinicas.
     */
    public static final String FIND_ALL = "DetalleHistoriaClinica.findAll";
    /**
     * Constante para la named query de buscar todos los dealles de la historia clinica ordenador por fecha de mayor a menor.
     */
    public static final String FIND_DETALLE_HISTORIA_CLINICA_ORDENADO_POR_FECHA = "DetalleHistoriaClinica.findDetalleHistoriaClinicaOrdenadoPorFecha";
    /**
     * Constante para la named query de buscar DetalleHistoriaClinica por id historia clinica.
     */
    public static final String FIND_DETALLE_HISTORIA_CLINICA_BY_ID_HISTORIA_CLINICA = "DetalleHistoriaClinica.findDetalleHistoriaClinicaByIdHistoriaClinica";
    /**
     * Constante para el parametro de id de la historia clinica de un paciente.
     */
    public static final String PARAMETRO_ID_HISTORIA_CLINICA = "idHistoriaClinica";
    /**
     * Constante para la named query de buscar DetalleHistoriaClinica por id de la consulta.
     */
    public static final String FIND_DETALLE_HISTORIA_CLINICA_BY_ID_CONSULTA = "DetalleHistoriaClinica.findDetalleHistoriaClinicaByIdConsulta";
    /**
     * Constante para el parametro de id de la consulta.
     */
    public static final String PARAMETRO_ID_CONSULTA = "idConsulta";
    /**
     * Constante de serializacion
     */
    private static final long serialVersionUID = 1L;
    /**
     * Identificador unico e la entidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Historia clinica.
     */
    @JoinColumn(name = "HISTORIA_CLINICA")
    @ManyToOne
    private HistoriaClinica historia;
    /**
     * Consulta donde se escribio este detalle.
     */
    @JoinColumn(name = "CONSULTA")
    @OneToOne(optional = true)
    private Consulta consulta;


    /**
     * Comentario del historial.
     */
    @Basic(optional = true)
    @Column(columnDefinition = "text")
    private String comentario;
    @Column(columnDefinition = "text")
    @Basic(optional = false)
    private String procedimientos;

    public DetalleHistoriaClinica() {
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
     * @return the historia
     */
    public HistoriaClinica getHistoria() {
        return historia;
    }

    /**
     * @param historia the historia to set
     */
    public void setHistoria(HistoriaClinica historia) {
        this.historia = historia;
    }

    /**
     * @return the consulta
     */
    public Consulta getConsulta() {
        return consulta;
    }

    /**
     * @param consulta the consulta to set
     */
    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

   
    public String getProcedimientos() {

        procedimientos = "";

        procedimientos = "<b>Procedimientos:</b><br/>";
        OrdenMedica orden = consulta.getOrdenMedica();
        int i = 0;
        if (orden != null) {
            procedimientos += "<OL>";
            for (DetalleOrdenMedica det : orden.getDetalleOrdenMedica()) {
                System.out.println("detalleorden id:" + det.getId());

                procedimientos += "<LI>" + det.getProcedimiento().getCodigo() + ":"
                        + det.getProcedimiento().getNombre() + ":"
                        + det.getProcedimiento().getTipoProcedimiento().
                        getDescripcion();

            }
            procedimientos += "</OL><br/>";
        }

        FormulaMedica formula = consulta.getFormulanMedica();
        if (formula != null) {
            i = 0;
            procedimientos += "<b><Formula Medica:</b>";
            procedimientos += "<OL>";

            for (DetalleFormulaMedica det : formula.getDetallesFormulaMedica()) {
                System.out.println("detalle formula id:" + det.getId());
                procedimientos += "<LI>" + det.getArticulo().getCodigo() + ":" + det.getArticulo().getNombre() + "<br/>"
                        + "    Prescripcion:" + det.getPrescripcion() + "<br/>";


            }
            procedimientos += "</OL>";
        }

        List<Remision> remisiones = consulta.getRemisiones();
        if (remisiones.isEmpty() != true) {
            procedimientos += "<b>Remisiones</b>:<br/>";
            procedimientos += "<OL>";
            i = 0;
            for (Remision remision : remisiones) {

                procedimientos += "<LI>";
                procedimientos += "<b>Especialidad:</b>" + remision.getEspecialidad().getNombre();
                procedimientos += "<br/><b>Observacion:</b>" + remision.getObservacion();

            }
            procedimientos += "</OL>";


        }
//        if (imagen != null) {
//            procedimientos += "<br/><b>Imagenes Diagnostica</b><br/>";
//            procedimientos+="<img src"+imagen.getImagenDiagnostica()+"/>";
//        }
        return procedimientos;



    }

    public void setProcedimientos(String procedimientos) {
        this.procedimientos = procedimientos;




    }

   
}
