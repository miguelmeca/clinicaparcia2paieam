package edu.eam.clinica.web.autorizacion;

import edu.eam.clinica.jpa.entidades.Funcionario;
import edu.eam.clinica.jpa.entidades.Medico;
import edu.eam.clinica.jpa.entidades.Paciente;
import edu.eam.clinica.jpa.entidades.Persona;
import edu.eam.clinica.jpa.entidades.User;
import edu.eam.clinica.jpa.utilidades.FactoryEntityManager;

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
	 * Persona que inicio sesion.
	 */
	private Persona personaLoggedIn;

	public LoginBean() {
		// TODO Auto-generated constructor stubfaco
		FactoryEntityManager.getEm();
		System.out.println("creadno em.");
	}

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

		User user = FactoryEntityManager.getEm().find(User.class, nombreUser);

		if (user != null) {
			
			SesionFactory.agregarASesion("persona", user.getPersona());
			personaLoggedIn=user.getPersona();
			if (user.getPersona() instanceof Medico) {
				
				System.out.println("medico");
				return "inicioMedico";
			}

			if (user.getPersona() instanceof Paciente) {
				System.out.println("paciente");
				return "?";
			}

			if (user.getPersona() instanceof Funcionario) {
				System.out.println("funcionario");
				return "inicioFuncionario";
			}
		}

		return null;

	}
	/**
	 * Metodo para determinar si ya hay session.
	 * @return true si ya hay y false si no.
	 */
	public boolean isLoggedIn(){
		System.out.println(personaLoggedIn!=null);
		return personaLoggedIn!=null;
	}

	public String getNombreUser() {
		System.out.println(FactoryEntityManager.getEm().find(Persona.class,
				"1234"));
		return nombreUser;
	}

	public void setNombreUser(String nombreUser) {
		this.nombreUser = nombreUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Persona getPersonaLoggedIn() {
		return personaLoggedIn;
	}

	public void setPersonaLoggedIn(Persona personaLoggedIn) {
		this.personaLoggedIn = personaLoggedIn;
	}
	
	

}
