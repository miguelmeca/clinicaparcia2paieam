package edu.eam.clinica.web.bean.paciente;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.net.QCodec;
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
	
	private Date fecha;
	private String horaCita;
	
	private List<SelectItem> medicos;
	private String medicoAsignado;
	
	private List<Consulta>consultasAtendias;
	private List<Consulta>consultaSinAtender;

	private long idCOnsultaCancelar;
	
	
	public  AsignarCitasPacienteBean() {

		em = FactoryEntityManager.getEm();

		HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		paciente = (Paciente) sesion.getAttribute("persona");
		
		
	

	}
	
	
	
	

	public String asignarCita() {

		List<Consulta> consultas = paciente.getConsultas();

		Consulta consulta = new Consulta();
		consulta.setEstado(EstadoConsultaEnum.EN_ESPERA);
		String hora=horaCita+"";
		
		String [] horaYMin=hora.split(":");
		
		Calendar cal= Calendar.getInstance();
		if(fecha.equals(null)==true){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"no se ha especificado la fecha",null ));
			
		}
		cal.setTime(fecha);
		
		cal.add(Calendar.HOUR_OF_DAY,Integer.parseInt(horaYMin[0]));
		cal.add(Calendar.MINUTE,Integer.parseInt(horaYMin[1]));
		consulta.setFechaHora(cal.getTime());//fecha del rich:calendar.
		
		Query query=	em.createNamedQuery(Medico.FIND_MEDICO_BY_NIT);
		query.setParameter(Medico.PARAMETRO_NIT, medicoAsignado);
		Medico med=(Medico) query.getSingleResult();
		if(med.equals(null)==true){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"no se ha especificado el medico",null ));
		
			
		}
		consulta.setMedico((Medico)query.getSingleResult());	//medico q se elijio.disponibles.	
		consulta.setPaciente(paciente);
		em.persist(consulta);
		consultas.add(consulta);
		paciente.setConsultas(consultas);
		em.persist(paciente);
		//em.persist(paciente);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
				(FacesMessage.SEVERITY_INFO,"su cita se ha asignado con exito.",null ));
	
		return null;
	}

	public List<SelectItem> getMedicos() {
		Query query = em.createNamedQuery(Medico.FIND_ALL);
		
		
	List<Medico> medico = query.getResultList();
		for (Medico medico2 : medico) {
			
			fecha.setSeconds(0);
			Query query2=em.createNamedQuery(Consulta.FIND_CONSULTA_BY_MEDICO_AND_FECHAS);
			query2.setParameter(Consulta.PARAMETRO_REGISTRO_MEDICO, medico2.getRegistroMedico());
			query2.setParameter(Consulta.PARAMETRO_MENOR_FECHA, fecha.getTime());
			query2.setParameter(Consulta.PARAMETRO_MAYOR_FECHA, fecha.getTime());
			
		
			List<Consulta>consultasMedico=query2.getResultList();
			
			for (Consulta consultax : consultasMedico) {
				if(consultax==null|| consultax.getEstado().equals(EstadoConsultaEnum.CANCELADA)==true){
				
					SelectItem itemMedico=new SelectItem(medico2.getNIT(),medico2.getNombre() );
					medicos.add(itemMedico);	
				}
			}
			
		
		}
		
		if(medicos.size()==0){
			
		
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
				(FacesMessage.SEVERITY_WARN,"no hay medicos disponibles para esta fecha.",null ));
			
					   
		}
		
		SelectItem seleccionarDefecto=new SelectItem("0000", "elija fecha y hora");
		medicos.add(seleccionarDefecto);
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
	
	

	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Consulta getCita() {
		return cita;
	}
	

	public String getHoraCita() {
		return horaCita;
	}



	public void setHoraCita(String horaCita) {
		this.horaCita = horaCita;
	}



	public void setCita(Consulta cita) {
		this.cita = cita;
	}





	public String getMedicoAsignado() {
		return medicoAsignado;
	}





	public void setMedicoAsignado(String medicoAsignado) {
		this.medicoAsignado = medicoAsignado;
	}



 public String cancelarConsulta(){
	 
	 
	 if(consultaSinAtender.size()==0){
		 
		
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_WARN,"no hay consultas para cancelar.",null ));
			
					   
	 }
	 for (Consulta consultaCancelar : consultaSinAtender) {
		if(consultaCancelar.getId()==idCOnsultaCancelar){
			
			consultaCancelar.setEstado(EstadoConsultaEnum.CANCELADA);
			em.getTransaction().begin();
			em.merge(consultaCancelar);
			em.getTransaction().commit();
			
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"su consulta ha sido cancelada con exito.",null ));
			
					   
			return null;
		}
	}
	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
				(FacesMessage.SEVERITY_ERROR,"elija la consulta que desea cancelar.",null ));
	
	 return null;
	 
 }

	public List<Consulta> getConsultasAtendias() {
		
		Query query=em.createNamedQuery(Consulta.FIND_CONSULTA_BY_NUMERO_Y_TIPO_DOCUMENTO);
		query.setParameter(Consulta.PARAMETRO_NUMERO_DOCUMENTO, paciente.getDocumento());
		query.setParameter(Consulta.PARAMETRO_TIPO_DOCUMENTO, paciente.getTipoDocumento());
		List<Consulta>consultas=query.getResultList();
		if(consultas.size()>0){
			
		
		
		
		for (Consulta consulta : consultas) {
			
			if(consulta.getEstado().equals(EstadoConsultaEnum.TOMADA)==true){
				
				consultasAtendias.add(consulta);
				
			}
		}
		}
		return consultasAtendias;
		
	}





	public void setConsultasAtendias(List<Consulta> consultasAtendias) {
		this.consultasAtendias = consultasAtendias;
	}





	public List<Consulta> getConsultaSinAtender() {
		Query query=em.createNamedQuery(Consulta.FIND_CONSULTA_BY_NUMERO_Y_TIPO_DOCUMENTO);
		query.setParameter(Consulta.PARAMETRO_NUMERO_DOCUMENTO, paciente.getDocumento());
		query.setParameter(Consulta.PARAMETRO_TIPO_DOCUMENTO, paciente.getTipoDocumento());
		
		List<Consulta>consultas=query.getResultList();
		
		for (Consulta consulta : consultas) {
			
			if(consulta.getEstado().equals(EstadoConsultaEnum.EN_ESPERA)==true){
				
				consultaSinAtender.add(consulta);
				
			}
		}
		
		return consultaSinAtender;
		
	}





	public void setConsultaSinAtender(List<Consulta> consultaSinAtender) {
		this.consultaSinAtender = consultaSinAtender;
	}

	public String cancelarCita(){
		Consulta consultaCancelar=em.find(Consulta.class, idCOnsultaCancelar);
		if(consultaCancelar.equals(null)==false){
		
			consultaCancelar.setEstado(EstadoConsultaEnum.CANCELADA);
			em.getTransaction().begin();
			em.merge(consultaCancelar);
			em.getTransaction().commit();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
			(FacesMessage.SEVERITY_INFO,"la cita se ha cancelado con exito.",null ));
		
		return null;
	
			
				   
		}
		else{
			
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
			(FacesMessage.SEVERITY_ERROR,"no hay consultas para cancelar.",null ));
			return null;
					   
		}
		
	}





	public long getIdCOnsultaCancelar() {
		return idCOnsultaCancelar;
	}





	public void setIdCOnsultaCancelar(long idCOnsultaCancelar) {
		this.idCOnsultaCancelar = idCOnsultaCancelar;
	}
	
	
	
	
}
