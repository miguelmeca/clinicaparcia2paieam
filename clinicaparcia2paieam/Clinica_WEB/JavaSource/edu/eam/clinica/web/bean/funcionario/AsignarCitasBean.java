package edu.eam.clinica.web.bean.funcionario;

import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import edu.eam.clinica.jpa.entidades.Consulta;
import edu.eam.clinica.jpa.entidades.Medico;
import edu.eam.clinica.jpa.entidades.Paciente;
import edu.eam.clinica.jpa.entidades.Persona;
import edu.eam.clinica.jpa.enumeraciones.MotivoConsultaEnum;
import edu.eam.clinica.jpa.enumeraciones.SexoEnum;
import edu.eam.clinica.jpa.enumeraciones.TipoDocumentoEnum;
import edu.eam.clinica.jpa.enumeraciones.TipoProcedimietoEnum;
import edu.eam.clinica.jpa.utilidades.FactoryEntityManager;

public class AsignarCitasBean {

	/**
	 * Guarda la fecha que se desea buscar
	 */
	private Date fechaBuscar;

	/**
	 * contiene la cedula del paciente
	 */
	private String cedulaPaciente;

	/**
	 * Documento de identificacion del medico
	 */
	private String docmedico;

	/**
	 * nit del medico
	 */
	private String nitmedico;

	/**
	 * Guarda fechas a buscar
	 */
	private Date fechaSeleccion;

	/**
	 * enumeracion de los motivos de las consultas
	 */
	private MotivoConsultaEnum motivoConsulta;

	/**
	 * Enumeracion de los diferentes tipos de procedimiento a realizar en una
	 * consulta
	 */
	private TipoProcedimietoEnum procedimientoConsulta;

	/**
	 * enumeracion de los diferentes tipos de documentos existentes
	 */
	private TipoDocumentoEnum tipoDocumento;

	public void setTipoDocumento(TipoDocumentoEnum tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * campo por defecto en la bd = null
	 */
	private boolean unidad;

	/**
	 * guarda en stringo la hora seleccionada por un funcionario desde la
	 * aplicacion
	 */
	private String horaCita;

	/**
	 * contiene el nombre del medico
	 */
	private String nombreMedico;

	/**
	 * obtiene todos los registros de medicos para ser mostrados en una tabla
	 */
	List<Consulta> consultasMedico;

	/**
	 * guarda todas las consultas de un paciente.
	 */
	List<Consulta> consulPacienteCita;

	/**
	 * ATRIBUTOS PARA EDITAR LA CONSULTA DE UN PACIENTE
	 */

	private long id;
	private Date fecha;
	private Paciente paciente;
	private Medico medico;
	private MotivoConsultaEnum motivo;
	private TipoProcedimietoEnum procedimiento;
	private String tiempo;

	private EntityManager em;

	public AsignarCitasBean() {
		em = FactoryEntityManager.getEm();
	}

	/**
	 * metodo para mostrar los atributos de la consulta de un paciente en el
	 * modalPanel para editar
	 * 
	 * @return regla de navegacion: null
	 */
	public String verConsulta() {
		Consulta con = em.find(Consulta.class, id);
		fecha = con.getFechaHora();
		paciente = con.getPaciente();
		medico = con.getMedico();
		motivo = con.getMotivo();
		procedimiento = con.getProcedimiento();

		return null;
	}

	/**
	 * metodo para editar la consulta de un paciente
	 * @return reglanavegacion:null
	 */
	public String editarConsulta() {

		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		String[] campostiempo = tiempo.split(":");
		int hora = Integer.parseInt(campostiempo[0]);
		int mins = Integer.parseInt(campostiempo[1]);
		c.set(Calendar.HOUR, hora);
		c.set(Calendar.MINUTE, mins);
		c.set(Calendar.SECOND, 0);

		return null;
	}

	/**
	 * Verifica la existencia del paciente La disponibilidad del medico Consulta
	 * que no hayan cruses de horarios Finalmente crea una nueva consulta
	 * 
	 * @return
	 */
	public String crearCita() {

		// al string de la hora seleccionada para la cita hacer split para
		// separar las horas de los minutos
		String[] campos = horaCita.split(":");
		// el campo en cero guarda las horas
		int hora = Integer.parseInt(campos[0]);
		// el campo en 1 guarda los minutos
		int minutos = Integer.parseInt(campos[1]);

		Calendar fechaSelect = Calendar.getInstance();
		fechaSelect.setTime(fechaSeleccion);
		fechaSelect.set(Calendar.HOUR, hora);
		fechaSelect.set(Calendar.MINUTE, minutos);
		fechaSelect.set(Calendar.SECOND, 0);

		// buscar el paciente que quiere asignar cita
		Paciente paciente = em.find(Paciente.class, cedulaPaciente);

		// validar que el paciente este registrado
		if (paciente != null) {

			// buscar el medico que se desea para la cita
			Medico medico = em.find(Medico.class, nitmedico);

			// validar que el medico si exista
			if (medico != null) {

				// consulta para buscar por registro del medico sus consultas
				Query query = em
						.createNamedQuery(Consulta.FIND_CONSULTA_BY_MEDICO_AND_FECHAS);
				query.setParameter(Consulta.PARAMETRO_REGISTRO_MEDICO,
						medico.getRegistroMedico());

				query.setParameter(Consulta.PARAMETRO_MENOR_FECHA,
						fechaSelect.getTime());
				query.setParameter(Consulta.PARAMETRO_MAYOR_FECHA,
						fechaSelect.getTime());

				// lista para guardar las consultas encontradas
				List<Consulta> consultasMedico = query.getResultList();

				// validar que tenga consultas de no tenerlas crea la cita sin
				// dificultades
				if (consultasMedico.size() > 0) {

					FacesContext
							.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_ERROR,
											"la cita ya fue asignada con otro paciente",
											null));

				} else {
					// si no hay consultas con el medico
					em.getTransaction().begin();

					Consulta consultaNueva = new Consulta(
							fechaSelect.getTime(), paciente, medico,
							MotivoConsultaEnum.PRIMERA_VEZ,
							TipoProcedimietoEnum.EXAMEN);

					em.persist(consultaNueva);
					em.getTransaction().commit();

					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"se ha creado la cita", null));
				}
			}

		}
		return null;
	}

	/**
	 * lista todos los motivos de una cita
	 * 
	 * @return listado de motivos
	 */
	public List<SelectItem> getMotivosConsulta() {

		List<SelectItem> lista = new ArrayList();

		for (MotivoConsultaEnum motivoConsultaEnum : MotivoConsultaEnum
				.values()) {
			lista.add(new SelectItem(motivoConsultaEnum, motivoConsultaEnum
					.getDescripcion()));
		}

		return lista;
	}

	/**
	 * lista los todos los posibles procedimientos de una cita
	 * 
	 * @return listado de procedimientos
	 */
	public List<SelectItem> getTipoProcedimiento() {

		List<SelectItem> lista = new ArrayList();

		for (TipoProcedimietoEnum tipo : TipoProcedimietoEnum.values()) {
			lista.add(new SelectItem(tipo, tipo.getDescripcion()));
		}

		return lista;
	}

	/**
	 * lista todos los tipos de documentos existentes
	 * 
	 * @return listado de documentos de indentificacion
	 */
	public List<SelectItem> getTipoDocumento() {

		List<SelectItem> lista = new ArrayList();

		for (TipoDocumentoEnum tipo : TipoDocumentoEnum.values()) {
			lista.add(new SelectItem(tipo, tipo.getDescripcion()));
		}

		return lista;
	}

	/**
	 * lista todos los medicos disponibles para atender una cita
	 * 
	 * @return listado de medicos con sus respectivos registros
	 */
	public List<SelectItem> getMedicos() {

		List<Medico> medicos = em.createNamedQuery(Medico.FIND_ALL)
				.getResultList();
		List<SelectItem> medicItems = new ArrayList();

		for (Medico medico : medicos) {
			SelectItem medicoItem = new SelectItem(medico.getDocumento(),
					medico.getNombre());
			medicItems.add(medicoItem);
		}

		return medicItems;
	}

	/**
	 * Verifica que el medico exista Realiza una consulta para obetener las
	 * citas en una fecha del mismo medico
	 * 
	 * @return
	 */
	public String mostrarHorariosMedico() {

		Calendar mayorfecha = Calendar.getInstance();
		mayorfecha.setTime(fechaBuscar);
		mayorfecha.set(Calendar.HOUR, 20);
		mayorfecha.set(Calendar.MINUTE, 0);

		Calendar menorfecha = Calendar.getInstance();
		menorfecha.setTime(fechaBuscar);
		menorfecha.set(Calendar.HOUR, 6);
		menorfecha.set(Calendar.MINUTE, 0);

		Query query = em
				.createNamedQuery(Consulta.FIND_CONSULTA_BY_MEDICO_AND_FECHAS);
		query.setParameter(Consulta.PARAMETRO_REGISTRO_MEDICO, nitmedico);
		query.setParameter(Consulta.PARAMETRO_MAYOR_FECHA, mayorfecha.getTime());
		query.setParameter(Consulta.PARAMETRO_MENOR_FECHA, menorfecha.getTime());

		consultasMedico = query.getResultList();

		if (consultasMedico.size() == 0) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"El medico no tiene consultas en esta fecha",
									null));
		}

		return null;
	}

	/**
	 * lista las citas de un paciente ingresado con la cedula
	 * 
	 * @return List<Consulta>:consultas encontradas
	 * 
	 */
	public String listarCitasPaciente() {

		Query quero = em
				.createNamedQuery(Consulta.FIND_CONSULTA_BY_NUMERO_Y_TIPO_DOCUMENTO);
		quero.setParameter(Consulta.PARAMETRO_NUMERO_DOCUMENTO, cedulaPaciente);
		quero.setParameter(Consulta.PARAMETRO_TIPO_DOCUMENTO, tipoDocumento);
		consulPacienteCita = quero.getResultList();

		if (consulPacienteCita.size() == 0) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"El paciente no tiene citas", null));
		}

		return null;
	}

	/**
	 * Getters and setters
	 */

	public String getCedulaPaciente() {
		return cedulaPaciente;
	}

	public void setCedulaPaciente(String cedulaPaciente) {
		this.cedulaPaciente = cedulaPaciente;
	}

	public Date getFechaSeleccion() {
		return fechaSeleccion;
	}

	public void setFechaSeleccion(Date fechaSeleccion) {
		this.fechaSeleccion = fechaSeleccion;
	}

	public MotivoConsultaEnum getMotivoConsulta() {
		return motivoConsulta;
	}

	public void setMotivoConsulta(MotivoConsultaEnum motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}

	public TipoProcedimietoEnum getProcedimientoConsulta() {
		return procedimientoConsulta;
	}

	public void setProcedimientoConsulta(
			TipoProcedimietoEnum procedimientoConsulta) {
		this.procedimientoConsulta = procedimientoConsulta;
	}

	public boolean isUnidad() {
		return unidad;
	}

	public void setUnidad(boolean unidad) {
		this.unidad = unidad;
	}

	public String getHoraCita() {
		return horaCita;
	}

	public void setHoraCita(String horaCita) {
		this.horaCita = horaCita;
	}

	public String getNombreMedico() {
		return nombreMedico;
	}

	public void setNombreMedico(String nombreMedico) {
		nombreMedico = nombreMedico;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public String getDocmedico() {
		return docmedico;
	}

	public void setDocmedico(String docmedico) {
		this.docmedico = docmedico;
	}

	public String getNitmedico() {
		return nitmedico;
	}

	public void setNitmedico(String nitmedico) {
		this.nitmedico = nitmedico;
	}

	public Date getFechaBuscar() {
		return fechaBuscar;
	}

	public void setFechaBuscar(Date fechaBuscar) {
		this.fechaBuscar = fechaBuscar;
	}

	public List<Consulta> getConsultasMedico() {
		return consultasMedico;
	}

	public void setConsultasMedico(List<Consulta> consultasMedico) {
		this.consultasMedico = consultasMedico;
	}

	public List<Consulta> getConsulPacienteCita() {
		return consulPacienteCita;
	}

	public void setConsulPacienteCita(List<Consulta> consulPacienteCita) {
		this.consulPacienteCita = consulPacienteCita;
	}

}
