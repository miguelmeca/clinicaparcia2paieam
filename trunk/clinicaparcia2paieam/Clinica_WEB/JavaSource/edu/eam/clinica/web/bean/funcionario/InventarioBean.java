package edu.eam.clinica.web.bean.funcionario;

import java.util.List;

import javax.persistence.EntityManager;

import edu.eam.clinica.jpa.entidades.Funcionario;
import edu.eam.clinica.jpa.entidades.Inventario;
import edu.eam.clinica.jpa.entidades.Medicamento;
import edu.eam.clinica.jpa.enumeraciones.GrupoTerapeuticoEnum;
import edu.eam.clinica.jpa.enumeraciones.PresentacionEnum;
import edu.eam.clinica.jpa.utilidades.FactoryEntityManager;
import edu.eam.clinica.web.autorizacion.SesionFactory;

public class InventarioBean  {

	
	private GrupoTerapeuticoEnum grupoTerapeutico;
	private String registroInvima;
	private PresentacionEnum presentacion;
	private EntityManager em;
	private Funcionario funcionario;
	
	
	public InventarioBean(){
		
		em=FactoryEntityManager.getEm();
		funcionario=(Funcionario)SesionFactory.getSesion();
	}
	
	public List<Medicamento> getMedicamentos(){
		
		return em.createNamedQuery("MedicamentosAll").getResultList();
		
	}
	
	

	public GrupoTerapeuticoEnum getGrupoTerapeutico() {
		return grupoTerapeutico;
	}

	public void setGrupoTerapeutico(GrupoTerapeuticoEnum grupoTerapeutico) {
		this.grupoTerapeutico = grupoTerapeutico;
	}

	public String getRegistroInvima() {
		return registroInvima;
	}

	public void setRegistroInvima(String registroInvima) {
		this.registroInvima = registroInvima;
	}

	public PresentacionEnum getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(PresentacionEnum presentacion) {
		this.presentacion = presentacion;
	}
	
	
	
	
}
