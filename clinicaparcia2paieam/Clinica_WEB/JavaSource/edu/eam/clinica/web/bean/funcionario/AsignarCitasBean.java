package edu.eam.clinica.web.bean.funcionario;

import java.sql.Date;
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
import edu.eam.clinica.jpa.enumeraciones.MotivoConsultaEnum;
import edu.eam.clinica.jpa.enumeraciones.TipoProcedimietoEnum;
import edu.eam.clinica.jpa.utilidades.FactoryEntityManager;

public class AsignarCitasBean {

	private String cedulaPaciente;
	private String docmedico;
	private Date fechaSeleccion;
	private MotivoConsultaEnum motivoConsulta;
	private TipoProcedimietoEnum procedimientoConsulta;
	private boolean unidad;
	private String estadoConsulta;
	private String horaCita;

	private EntityManager em;

	public AsignarCitasBean() {
		em = FactoryEntityManager.getEm();
	}

	/**
	 * Verifica la existencia del paciente La disponibilidad del medico Consulta
	 * que no hayan cruses de horarios Finalmente crea una nueva consulta
	 * 
	 * @return
	 */
	public String crearCrita() {
		
		
		//al string de la hora seleccionada para la cita hacer split para
		//separar las horas de los minutos
		String[] campos = horaCita.split(":");
		//el campo en cero guarda las horas
		int hora = Integer.parseInt(campos[0]);
		//el campo en 1 guarda los minutos
		int minutos = Integer.parseInt(campos[1]);
		
		Calendar fechaSelect= Calendar.getInstance();
        fechaSelect.setTime(fechaSeleccion);
        fechaSelect.set(Calendar.HOUR, hora);
        fechaSelect.set(Calendar.MINUTE, minutos);
	    fechaSelect.set(Calendar.SECOND, 0);

		// buscar el paciente que quiere asignar cita
		Paciente paciente = em.find(Paciente.class, cedulaPaciente);

		// validar que el paciente este registrado
		if (paciente != null) {

			// buscar el medico que se desea para la cita
			Medico medico = em.find(Medico.class, docmedico);

			// validar que el medico si exista
			if (medico != null) {
			
				// consulta para buscar por registro del medico sus consultas
				Query query = em
						.createNamedQuery(Consulta.FIND_CONSULTA_BY_MEDICO_AND_FECHAS);
				query.setParameter(Consulta.PARAMETRO_REGISTRO_MEDICO,
						medico.getRegistroMedico());
				
				query.setParameter(Consulta.PARAMETRO_MENOR_FECHA, fechaSelect.getTime());
				query.setParameter(Consulta.PARAMETRO_MAYOR_FECHA, fechaSelect.getTime());


				// lista para guardar las consultas encontradas
				List<Consulta> consultasMedico = query.getResultList();

				//validar que tenga consultas de no tenerlas crea la cita sin dificultades
				if (consultasMedico.size() > 0) {

							FacesContext
									.getCurrentInstance()
									.addMessage(
											null,
											new FacesMessage(
													FacesMessage.SEVERITY_ERROR,
													"la cita ya fue asignada con otro paciente",
													null));


				}else{
					//si no hay consultas con el medico 
					em.getTransaction().begin();
					Consulta consultaNueva = new Consulta(fechaSelect.getTime(), paciente, medico,
					motivoConsulta, procedimientoConsulta);

					em.persist(consultaNueva);
					em.getTransaction().commit();
				}
			}

		}
		return null;
	}

	public List<SelectItem> getMedicos() {
		List<Medico> medicos = em.createNamedQuery(Medico.FIND_ALL)
				.getResultList();
		List<SelectItem> medicItems = new ArrayList();

		for (Medico medico : medicos) {
			SelectItem medicoItem = new SelectItem(medico.getDocumento(),
					medico.toString());
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
	public List<SelectItem> getHorariosMedico() {

		Medico medico = em.find(Medico.class, docmedico);

		Query query = em
				.createNamedQuery(Consulta.FIND_CONSULTA_BY_MEDICO_AND_FECHAS);
		query.setParameter(Consulta.PARAMETRO_REGISTRO_MEDICO, docmedico);

		List<Consulta> consultasMedico = query.getResultList();
		List<SelectItem> horasItems = new ArrayList();

		
		for (Consulta consul : consultasMedico) {
			SelectItem fechoras = new SelectItem(consul.getFechaHora());
			horasItems.add(fechoras);
		}

		return horasItems;
	}
	
	

	/*
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

	public String getEstadoConsulta() {
		return estadoConsulta;
	}

	public void setEstadoConsulta(String estadoConsulta) {
		this.estadoConsulta = estadoConsulta;
	}

	public String getDocmedico() {
		return docmedico;
	}

	public void setDocmedico(String docmedico) {
		this.docmedico = docmedico;
	}

	public String getHoraCita() {
		return horaCita;
	}

	public void setHoraCita(String horaCita) {
		this.horaCita = horaCita;
	}

}
