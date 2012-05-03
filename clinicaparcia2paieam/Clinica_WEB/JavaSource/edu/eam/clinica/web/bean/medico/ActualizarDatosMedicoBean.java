package edu.eam.clinica.web.bean.medico;

import java.util.List;

import javax.persistence.EntityManager;

import edu.eam.clinica.jpa.entidades.Funcionario;
import edu.eam.clinica.jpa.entidades.Persona;
import edu.eam.clinica.jpa.entidades.Telefono;
import edu.eam.clinica.web.autorizacion.SesionFactory;

/**
 * 
 * @author Cesar
 *
 */
public class ActualizarDatosMedicoBean {

	public Funcionario persona;
	public String primerNombre;
	public String segundoNombre;
	public String primerApellido;
	public String segundoApellido;
	public String correo;
	public List<Telefono> telefonos;
	public String direccion;
	public String pass;
	public String nuevoPass;
	public EntityManager em;

	public ActualizarDatosMedicoBean() {
		/* instanciar EntityManeger */
		persona = (Funcionario) SesionFactory.getValor("persona");
		if (persona != null) {
			primerNombre = persona.getPrimerNombre();
			segundoNombre = persona.getSegundoNombre();
			primerApellido = persona.getPrimerApellido();
			segundoApellido = persona.getSegundoApellido();
			correo = persona.getEmail();
			direccion = persona.getDireccion();
			pass = persona.getPassword();
			telefonos = persona.getTelefonos();
		}
	}
	
	/**
	 * Metodo que actualiza los datos de un medico que esta en sesion
	 * @return
	 */
	public String actualizarDatos(){
		
		persona = (Funcionario) SesionFactory.getValor("persona");
		
		persona.setDireccion(direccion);
		persona.setEmail(correo);
		persona.setPrimerApellido(primerApellido);
		persona.setPrimerNombre(primerNombre);
		persona.setSegundoApellido(segundoApellido);
		persona.setSegundoNombre(segundoNombre);
		em.getTransaction().begin();
		em.merge(persona);
		em.getTransaction().commit();
		SesionFactory.agregarASesion("medico", persona);
		
		return null;
	}
	
	/**
	 * Metodo que cambia el password de un Medico que esta en sesion
	 * @return
	 */
	public String cambiarPassword(){
		persona = (Funcionario) SesionFactory.getValor("persona");
		persona.setPassword(nuevoPass);
		em.getTransaction().begin();
		em.merge(persona);
		em.getTransaction().commit();
		return null;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Funcionario persona) {
		this.persona = persona;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public List<Telefono> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<Telefono> telefonos) {
		this.telefonos = telefonos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNuevoPass() {
		return nuevoPass;
	}

	public void setNuevoPass(String nuevoPass) {
		this.nuevoPass = nuevoPass;
	}

	
}
