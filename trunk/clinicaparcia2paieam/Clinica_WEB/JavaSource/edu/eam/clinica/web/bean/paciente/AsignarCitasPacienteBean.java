package edu.eam.clinica.web.bean.paciente;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.richfaces.iterator.ForEachIterator;

import com.sun.org.apache.xerces.internal.impl.dv.xs.YearDV;

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
	
	private Calendar fecha;
	private SelectItem horaCita;
	
	private List<SelectItem> medicos;
	private SelectItem medicoAsignado;

	
	public AsignarCitasPacienteBean() {

		em = FactoryEntityManager.getEm();

		HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		Persona persona = (Persona) sesion.getAttribute("persona");
		paciente = em.find(Paciente.class, persona.getDocumento());

	

	}
	
	

	public String asignarCita() {

		List<Consulta> consultas = paciente.getConsultas();

		Consulta consulta = new Consulta();
		consulta.setEstado(EstadoConsultaEnum.EN_ESPERA);
		String hora=horaCita.getValue()+"";
		
		String [] horaYMin=hora.split(":");
		
		fecha.add(Calendar.HOUR_OF_DAY,Integer.parseInt(horaYMin[0]));
		fecha.add(Calendar.MINUTE,Integer.parseInt(horaYMin[1]));
		consulta.setFechaHora(fecha.getTime());//fecha del rich:calendar.
		consulta.setMedico(null);	//medico q se elijio.disponibles.	
		consulta.setPaciente(paciente);
		em.persist(consulta);
		consultas.add(consulta);
		paciente.setConsultas(consultas);
		//em.persist(paciente);
		return null;
	}

	public List<SelectItem> getMedicos() {
		Query query = em.createNamedQuery(Medico.FIND_ALL);
	List<Medico> medico = query.getResultList();
		for (Iterator iterator = medico.iterator(); iterator.hasNext();) {
			Medico medico2 = (Medico) iterator.next();
			
			Query query2=em.createNamedQuery(Consulta.FIND_CONSULTA_BY_MEDICO_AND_FECHAS);
			query2.setParameter(Consulta.PARAMETRO_REGISTRO_MEDICO, medico2.getRegistroMedico());
			query2.setParameter(Consulta.PARAMETRO_MENOR_FECHA, fecha.getTime());
			query2.setParameter(Consulta.PARAMETRO_MAYOR_FECHA, fecha.getTime());
			List<Consulta>consultasMedico=query2.getResultList();
			Consulta consulta=consultasMedico.get(0);
			if(consulta!=null){
			
			SelectItem itemMedico=new SelectItem(medico2.getDocumento(),medico2.getNombre() );
			medicos.add(itemMedico);
			}
		}
		return medicos;
	}

	public void setMedicos(List<SelectItem> medicos) {
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
	
	

	public Calendar getFecha() {
		return fecha;
	}



	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}



	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Consulta getCita() {
		return cita;
	}
	

	public SelectItem getHoraCita() {
		return horaCita;
	}



	public void setHoraCita(SelectItem horaCita) {
		this.horaCita = horaCita;
	}



	public void setCita(Consulta cita) {
		this.cita = cita;
	}

}
