package edu.eam.clinica.web.bean.paciente;

import javax.persistence.Basic;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import edu.eam.clinica.jpa.enumeraciones.TipoDocumentoEnum;

public class PacienteBean {

	 	private String documento;
	    private String primerNombre;
	    private String segundoNombre;
	    private String primerApellido;
	    private String segundoApellido;
	    private String direccion;
	    private String email;
	
	public PacienteBean(){
		
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
