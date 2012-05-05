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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class VerAgendaBean {
	/**
	 * Parametro que permite acceder a los datos de la base de datos
	 */
	private EntityManager em;
	/**
	 * fecha que se selecciona del rich:calendar para ver las consultas de la
	 * misma
	 */
	private Date fecha;
	/**
	 * fecha de hoy para ver las consultas del mismo
	 */
	private Calendar fecha1;
	/**
	 * Medico utilizado para la simulacion de session
	 */
	private Medico medico;
	/**
	 * Parametro que permite utilizar la variable en session
	 */
	private SesionFactory s;
	/**
	 * Id de la consulta seleccionada en la tabla
	 */
	private long idC;
	/**
	 * Consultas que se van a listar en la tabla
	 */
	private List<Consulta> consultas;

	public VerAgendaBean() {
		fecha1 = Calendar.getInstance();
		em = FactoryEntityManager.getEm();
		/*
		 * Estas dos lineas se usan para la simulacion de la variable medico en
		 * session
		 */
		// medico = em.find(Medico.class, "1234");
		// SesionFactory.agregarASesion("persona", medico);
		//

		medico = (Medico) SesionFactory.getValor("persona");

	}

	/**
	 * * Metodo que me optiene las consultas de un medico en una fecha en
	 * particular
	 * 
	 * @return null ya que no requiere usar reglas de navegacion
	 */
	public String consultasFecha() {

		Calendar fecha0 = Calendar.getInstance();
		Calendar fecha2 = Calendar.getInstance();

		fecha0.setTime(fecha);
		fecha0.set(Calendar.HOUR_OF_DAY, 0);
		fecha0.set(Calendar.MINUTE, 0);
		fecha0.set(Calendar.SECOND, 0);

		fecha2.setTime(fecha);
		fecha2.set(Calendar.HOUR_OF_DAY, 23);
		fecha2.set(Calendar.MINUTE, 59);
		fecha2.set(Calendar.SECOND, 59);

		Query q = em
				.createNamedQuery(Consulta.FIND_CONSULTA_BY_MEDICO_AND_FECHAS);
		q.setParameter(Consulta.PARAMETRO_MENOR_FECHA, fecha0.getTime());
		q.setParameter(Consulta.PARAMETRO_MAYOR_FECHA, fecha2.getTime());
		q.setParameter(Consulta.PARAMETRO_REGISTRO_MEDICO,
				medico.getRegistroMedico());

		consultas = q.getResultList();
		return null;

	}

	/**
	 * * Metodo que me optiene las consultas de un medico en el dia de actual
	 * 
	 * @return null ya que no requiere usar reglas de navegacion
	 */
	public String consultasDia() {

		Calendar fechaM = Calendar.getInstance();
		fechaM.set(fecha1.get(Calendar.YEAR), fecha1.get(Calendar.MONTH),
				fecha1.get(Calendar.DATE), 0, 00, 00);
		fecha1.set(fecha1.get(Calendar.YEAR), fecha1.get(Calendar.MONTH),
				fecha1.get(Calendar.DATE), 23, 59, 00);

		Query q = em
				.createNamedQuery(Consulta.FIND_CONSULTA_BY_MEDICO_AND_FECHAS);
		q.setParameter(Consulta.PARAMETRO_MENOR_FECHA, fechaM.getTime());
		q.setParameter(Consulta.PARAMETRO_MAYOR_FECHA, fecha1.getTime());
		q.setParameter(Consulta.PARAMETRO_REGISTRO_MEDICO,
				medico.getRegistroMedico());
		System.out.println(fechaM);
		consultas = q.getResultList();

		return null;
	}

	/**
	 * Metodo que permite enviar la variable consulta en session, y ademas
	 * permite navegar a la pagina atenderConsulta	  
	 * @return la regla de navegacion para ir a la pagina de atender las consul
	 */
	public String actionConsulta() {
		Consulta c = em.find(Consulta.class, idC);

		if (c.getFechaHora().getDay() == fecha1.getTime().getDay()) {
			s = new SesionFactory();
			s.agregarASesion("consulta", c);

			return "atenderConsulta";
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"Esta consulta no se puede atender debido a que hoy no es el dia indicado",
									null));
		}
		return null;

	}

	public EntityManager getEm() {
		return em;
	}

	public long getIdC() {
		return idC;
	}

	public void setIdC(long idC) {
		this.idC = idC;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

}
