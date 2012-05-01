package edu.eam.clinica.web.autorizacion;

import edu.eam.clinica.jpa.entidades.Funcionario;
import edu.eam.clinica.jpa.entidades.Medico;
import edu.eam.clinica.jpa.entidades.Paciente;
import edu.eam.clinica.jpa.entidades.User;

/**
 * Clase que implementa el login de la aplicacion.
 * 
 * @author caferrerb@gmail.com
 * 
 */
public class LoginBean {

	/**
	 * Nombre de usuario del usuario.
	 */
	private String nombreUser;
	/**
	 * Password del usuario.
	 */
	private String password;

	/**
	 * Este metodo hace uso de la entidad user. esta entidad esta relacionada
	 * con persona. todas las personas tienen un user en la aplicacion que es
	 * unico.
	 * 
	 * @return
	 */
	public String login() {

		// busque el user por nombre y password, dependiendo de que sea vaya a
		// la pagina que le corresponda.
		User user=null;//buscar aqui.
		
		/*
		 * Agrega a sesion el que ingreso al sistema.
		 */
		SesionFactory.agregarASesion("persona", user.getPersona());
		
		if(user.getPersona() instanceof Medico){
			return "?";
		}
		
		if(user.getPersona() instanceof Paciente){
			return "?";
		}
		
		if(user.getPersona() instanceof Funcionario){
			return "?";
		}
		
		
		return null;

	}

}
