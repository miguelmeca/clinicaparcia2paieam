package edu.eam.clinica.web.bean.funcionario;

import javax.persistence.EntityManager;

import edu.eam.clinica.jpa.entidades.Funcionario;
import edu.eam.clinica.jpa.utilidades.FactoryEntityManager;
import edu.eam.clinica.web.autorizacion.SesionFactory;

public class InicioSesionFuncionario {
	
	/*
	 * EntityManager para hacer uso de la persistencia
	 */
	private EntityManager em;
	
	private String logIn;
	private String pass;
	private Funcionario funcionario;
	
	
	
	public InicioSesionFuncionario() {
		em= FactoryEntityManager.getEm();
		funcionario= (Funcionario) SesionFactory.getSesion();
	}
	
	public String getLogIn() {
		return logIn;
	}
	public void setLogIn(String logIn) {
		this.logIn = logIn;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	

}
