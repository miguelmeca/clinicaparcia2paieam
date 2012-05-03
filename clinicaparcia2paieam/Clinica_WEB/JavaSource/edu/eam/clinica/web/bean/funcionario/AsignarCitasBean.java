package edu.eam.clinica.web.bean.funcionario;

import java.sql.Date;
import java.util.*;

import javax.persistence.EntityManager;

import edu.eam.clinica.jpa.entidades.Medico;
import edu.eam.clinica.jpa.utilidades.FactoryEntityManager;

public class AsignarCitasBean {

	private int nroConsulta;
	private String cedulaPaciente;
	private String medico;
	private Date fechaCita;
	private String motivoConsulta;
	private String procedimientoConsulta;
	private boolean unidad;
	private String estadoConsulta;
	
	private EntityManager em;
	
	public AsignarCitasBean(){
		em = FactoryEntityManager.getEm();
	}
	
	public String crearCrita(){
		
		return null;
	}

	public int getNroConsulta() {
		return nroConsulta;
	}

	public void setNroConsulta(int nroConsulta) {
		this.nroConsulta = nroConsulta;
	}

	public String getCedulaPaciente() {
		return cedulaPaciente;
	}

	public void setCedulaPaciente(String cedulaPaciente) {
		this.cedulaPaciente = cedulaPaciente;
	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public Date getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}

	public String getMotivoConsulta() {
		return motivoConsulta;
	}

	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}

	public String getProcedimientoConsulta() {
		return procedimientoConsulta;
	}

	public void setProcedimientoConsulta(String procedimientoConsulta) {
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
	
}
