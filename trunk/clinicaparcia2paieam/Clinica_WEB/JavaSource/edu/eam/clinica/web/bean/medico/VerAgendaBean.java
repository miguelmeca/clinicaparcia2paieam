package edu.eam.clinica.web.bean.medico;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.eam.clinica.jpa.entidades.Consulta;
import edu.eam.clinica.jpa.entidades.Medico;
import edu.eam.clinica.jpa.utilidades.FactoryEntityManager;
import edu.eam.clinica.web.autorizacion.SesionFactory;

public class VerAgendaBean {

	/*
	 * Parametro que permite acceder a los datos de la base de datos
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
	 * Medico  utilizado para la simulacion de session
	 */
	private Medico medico;
	/*
	 * Parametro que permite utilizar la variable en session
	 */
	private SesionFactory s;
	/*
	 * Id de la consulta seleccionada en la tabla
	 */
	private long idC;

	public VerAgendaBean() {
		fecha1 = new Date();
		em = FactoryEntityManager.getEm();
/*
 * Estas dos lineas se usan para la simulacion de la variable medico en session
 */
//		medico = em.find(Medico.class, "1234");
//		SesionFactory.agregarASesion("persona", medico);

		 medico=(Medico) SesionFactory.getValor("persona");

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
				medico.getRegistroMedico());
		return q.getResultList();

	}
	
	/*
	 * Metodo que  optiene las consultas de un medico en el dia actual
	 * 
	 */
	public List<Consulta> getConsultasD() {

		Date fechaM = new Date();
//		fechaM.setYear(fecha1.getYear());
//		fechaM.setMonth(fecha1.getMonth());
//		fechaM.setDate(fecha1.getDate());
//		fechaM.setHours(0);
//		fechaM.setMinutes(00);
//		fecha1.setHours(23);
//		fecha1.setMinutes(59);
		Calendar c=Calendar.getInstance();
		//c.set(Calendar.MINUTE, 34);
		c.getTime();//date del calendar.


		Query q = em
				.createNamedQuery(Consulta.FIND_CONSULTA_BY_MEDICO_AND_FECHAS);
		q.setParameter(Consulta.PARAMETRO_MENOR_FECHA, fechaM);
		q.setParameter(Consulta.PARAMETRO_MAYOR_FECHA, fecha1);
		q.setParameter(Consulta.PARAMETRO_REGISTRO_MEDICO,
				medico.getRegistroMedico());
		return q.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}
	
	/*
	 * Metodo que permite  enviar la variable consulta
	 * en session, y ademas permite navegar a la pagina
	 * atenderConsulta
	 */
	public String actionConsulta() {
		Consulta c = em.find(Consulta.class, idC);
		//crear un hoy y compararalo solo por dia,
		//if (fecha == c.getFechaHora() || fecha1 == c.getFechaHora()) {

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