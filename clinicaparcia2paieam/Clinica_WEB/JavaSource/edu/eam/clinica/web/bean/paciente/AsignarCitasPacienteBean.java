package edu.eam.clinica.web.bean.paciente;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import edu.eam.clinica.jpa.entidades.Consulta;
import edu.eam.clinica.jpa.entidades.Medico;
import edu.eam.clinica.jpa.entidades.Paciente;
import edu.eam.clinica.jpa.entidades.Persona;
import edu.eam.clinica.jpa.enumeraciones.EstadoConsultaEnum;
import edu.eam.clinica.jpa.utilidades.FactoryEntityManager;

public class AsignarCitasPacienteBean {

	private EntityManager em;

	private Paciente paciente;

	private Consulta cita;

	private List<Medico> medicos;

	public AsignarCitasPacienteBean() {

		em = FactoryEntityManager.getEm();

		HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		Persona persona = (Persona) sesion.getAttribute("persona");
		paciente = em.find(Paciente.class, persona.getDocumento());

		Query query = em.createNamedQuery(Medico.FIND_ALL);
		medicos = query.getResultList();

	}

	public String asignarCita() {

		List<Consulta> consultas = paciente.getConsultas();

		Consulta consulta = new Consulta();
		consulta.setEstado(EstadoConsultaEnum.EN_ESPERA);
		consulta.setFechaHora(null);//fecha del rich:calendar.
		consulta.setMedico(null);	//medico q se elijio.disponibles.	
		consulta.setPaciente(paciente);
		em.persist(consulta);
		consultas.add(consulta);
		paciente.setConsultas(consultas);
		//em.persist(paciente);
		return null;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Consulta getCita() {
		return cita;
	}

	public void setCita(Consulta cita) {
		this.cita = cita;
	}

}
