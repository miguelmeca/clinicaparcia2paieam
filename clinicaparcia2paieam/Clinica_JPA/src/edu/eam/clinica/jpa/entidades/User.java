package edu.eam.clinica.jpa.entidades;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity

public class User implements Serializable {

	
	@OneToOne
	@JoinColumn(name="PERSONA")
	private Persona persona;   
	
	@Id
	private String user;
	
	private String password;
	
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}   
	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}   
	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
   
}
