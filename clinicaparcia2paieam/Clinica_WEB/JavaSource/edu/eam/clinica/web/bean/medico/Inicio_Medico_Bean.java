package edu.eam.clinica.web.bean.medico;

import javax.persistence.EntityManager;

import edu.eam.clinica.jpa.entidades.Persona;

public class Inicio_Medico_Bean {

	public EntityManager em;
	
	public Persona persona;
	
	public String login;
	
	public String password;

	public Inicio_Medico_Bean() {
		/*falta inicializar el FactoryEntityManager*/
	}
	
	public String iniciarSesion(){
		return null;
	}
}
