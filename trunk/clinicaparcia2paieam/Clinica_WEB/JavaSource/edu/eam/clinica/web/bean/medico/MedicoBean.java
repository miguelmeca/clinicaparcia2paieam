package edu.eam.clinica.web.bean.medico;

import java.util.List;

import javax.persistence.EntityManager;

import edu.eam.clinica.jpa.entidades.Medico;
import edu.eam.clinica.jpa.utilidades.FactoryEntityManager;

public class MedicoBean {

	//Atributos
	private long especialidad;
	private String nit;
	private String registroMedico;
	
	private EntityManager em;
	
	public MedicoBean() {
		em=FactoryEntityManager.getEm();
	}

	/**
	 * Listar todos los medicos
	 * @return
	 */
	public List<Medico> getMedicos(){
		return em.createNamedQuery(Medico.FIND_ALL).getResultList();
	}
	
	/*
	 * getters and setters de los atributos
	 */
	
	public long getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(long especialidad) {
		this.especialidad = especialidad;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getRegistroMedico() {
		return registroMedico;
	}

	public void setRegistroMedico(String registroMedico) {
		this.registroMedico = registroMedico;
	}
	
}
