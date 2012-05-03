package edu.eam.clinica.web.bean.medico;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.management.loading.PrivateClassLoader;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.validator.Length;
import org.hibernate.validator.Pattern;

import edu.eam.clinica.jpa.entidades.Consulta;
import edu.eam.clinica.jpa.entidades.Medico;
import edu.eam.clinica.jpa.entidades.Persona;
import edu.eam.clinica.jpa.utilidades.FactoryEntityManager;
import edu.eam.clinica.web.autorizacion.SesionFactory;

import java.util.Date;
import java.util.List;

public class VerAgendaBean {

	/*
	 * 
	 */
	private EntityManager em;
	/*
	 * fecha que se selecciona del rich:calendar para ver las consultas de la
	 * misma
	 */
	private Date fecha;
	/*
	 * fecha de hoy para ver las consultas del mimo
	 */
	private Date fecha1;
	/*
	 * Medico en session
	 */
	private Medico m;
	/*
	 * 
	 */
	private SesionFactory s;
	/*
	 * Id de la consulta seleccionada en la tabla
	 */
	private long idC;

	public VerAgendaBean() {
		fecha1 = new Date();
		em = FactoryEntityManager.getEm();

		m = em.find(Medico.class, "1234");
		SesionFactory.agregarASesion("persona", m);

		// m=(Medico) SesionFactory.getValor("persona");

	}

	/*
	 * Metodo que me optiene las consultas de un medico en una fecha en
	 * particular
	 */
	public List<Consulta> getConsultasM() {

		Date fechaM = new Date();
		fechaM.setYear(fecha.getYear());
		fechaM.setMonth(fecha.getMonth());
		fechaM.setDate(fecha.getDate());
		fechaM.setHours(0);
		fechaM.setMinutes(00);
		fecha.setHours(23);
		fecha.setMinutes(59);

		Query q = em
				.createNamedQuery(Consulta.FIND_CONSULTA_BY_MEDICO_AND_FECHAS);
		q.setParameter(Consulta.PARAMETRO_MENOR_FECHA, fechaM);
		q.setParameter(Consulta.PARAMETRO_MAYOR_FECHA, fecha);
		q.setParameter(Consulta.PARAMETRO_REGISTRO_MEDICO,
				m.getRegistroMedico());
		return q.getResultList();

	}

	public List<Consulta> getConsultasD() {

		Date fechaM = new Date();
		fechaM.setYear(fecha1.getYear());
		fechaM.setMonth(fecha1.getMonth());
		fechaM.setDate(fecha1.getDate());
		fechaM.setHours(0);
		fechaM.setMinutes(00);
		fecha1.setHours(23);
		fecha1.setMinutes(59);

		Query q = em
				.createNamedQuery(Consulta.FIND_CONSULTA_BY_MEDICO_AND_FECHAS);
		q.setParameter(Consulta.PARAMETRO_MENOR_FECHA, fechaM);
		q.setParameter(Consulta.PARAMETRO_MAYOR_FECHA, fecha1);
		q.setParameter(Consulta.PARAMETRO_REGISTRO_MEDICO,
				m.getRegistroMedico());
		return q.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}

	public String actionConsulta() {
		Consulta c = em.find(Consulta.class, idC);

	//	if (fecha == c.getFechaHora() || fecha1 == c.getFechaHora()) {

			s = new SesionFactory();
			s.agregarASesion("consulta", c);

			return "atenderConsulta";
//		} else {
//			FacesContext
//					.getCurrentInstance()
//					.addMessage(
//							null,
//							new FacesMessage(
//									FacesMessage.SEVERITY_ERROR,
//									"Esta consulta no se puede atender debido a que hoy no es el dia indicado",
//									null));
//		}
//		return null;

	}

	public long getIdC() {
		return idC;
	}

	public void setIdC(long idC) {
		this.idC = idC;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
