package edu.eam.clinica.web.bean.funcionario;

import java.sql.Date;
import java.util.*;

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

	private long nroConsulta;
	private String cedulaPaciente;
	private String docmedico;
	private Date fechaCita;
	private MotivoConsultaEnum motivoConsulta;
	private TipoProcedimietoEnum procedimientoConsulta;
	private boolean unidad;
	private String estadoConsulta;
	private String horaCita;

	private EntityManager em;

	public AsignarCitasBean() {
		em = FactoryEntityManager.getEm();
	}

	public String crearCrita() {
		
		Paciente paciente = em.find(Paciente.class, cedulaPaciente);
		if (paciente != null) {
			Medico medico = em.find(Medico.class, docmedico);
			if (medico != null) {

				Query query = em.createNamedQuery(Consulta.FIND_CONSULTA_BY_REGISTRO_MEDICO);
				query.setParameter(Consulta.PARAMETRO_REGISTRO_MEDICO,docmedico);

				List<Consulta> consultasMedico = query.getResultList();

				for (Consulta consulta : consultasMedico) {

					if (consulta.getFechaHora().equals(fechaCita + "" + horaCita)) {

						JOptionPane.showMessageDialog(null,"la cita ya fue asignada");
						/*
						 * lanzar mensaje indicando que la cita ya fue asignada
						 * en ese dia en esa hora
						 */
					}
				}
				Consulta consulta = new Consulta(nroConsulta, fechaCita,paciente, medico, motivoConsulta, procedimientoConsulta);
				em.getTransaction().begin();
				em.persist(consulta);
				em.getTransaction().commit();
				
			}
		}
		return null;
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

	public Date getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}

	public long getNroConsulta() {
		return nroConsulta;
	}

	public void setNroConsulta(long nroConsulta) {
		this.nroConsulta = nroConsulta;
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
