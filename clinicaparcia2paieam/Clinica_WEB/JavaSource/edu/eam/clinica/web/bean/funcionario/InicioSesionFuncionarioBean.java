package edu.eam.clinica.web.bean.funcionario;

import javax.persistence.EntityManager;

import edu.eam.clinica.jpa.entidades.Funcionario;
import edu.eam.clinica.jpa.utilidades.FactoryEntityManager;
import edu.eam.clinica.web.autorizacion.SesionFactory;

public class InicioSesionFuncionarioBean {
	
	/*
	 * EntityManager para hacer uso de la persistencia
	 */
	private EntityManager em;
	
	/*
	 * usuario del funcionario
	 */
	private String logIn;
	/*
	 * contraseña de la cuenta del funcionario
	 */
	private String pass;
	private Funcionario funcionario;
	
	
	/*
	 * constructor
	 */
	public InicioSesionFuncionarioBean() {
		em= FactoryEntityManager.getEm();
		funcionario= (Funcionario) SesionFactory.getSesion();
	}
	
	public void irActualizarDatosFuncionario(){
		
	}
	public void irInventario(){
		
	}
	public void irAsiganrCitas(){
		
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
