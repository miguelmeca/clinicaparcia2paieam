package edu.eam.clinica.web.bean.medico;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.validator.NotNull;
import org.hibernate.validator.Pattern;

import edu.eam.clinica.jpa.entidades.Articulo;
import edu.eam.clinica.jpa.entidades.Funcionario;
import edu.eam.clinica.jpa.entidades.Persona;
import edu.eam.clinica.jpa.entidades.Telefono;
import edu.eam.clinica.jpa.enumeraciones.TipoDocumentoEnum;
import edu.eam.clinica.web.autorizacion.SesionFactory;

/**
 * 
 * @author Cesar
 * 
 */
public class ActualizarDatosMedicoBean {
	/**
	 * Funcionario que esta en sesion de tipo Medico
	 */
	public Funcionario persona;
	/**
	 * Primer nombre del medico
	 */
	@Pattern(regex = "[A-Za-z]*",message="Ingrese solo numeros")
	public String primerNombre;
	/**
	 * Segundo nombre del medico
	 */
	@Pattern(regex = "[A-Za-z]*",message="Ingrese solo numeros")
	public String segundoNombre;
	/**
	 * Primer apellido del medico
	 */
	@Pattern(regex = "[A-Za-z]*",message="Ingrese solo numeros")
	public String primerApellido;
	/**
	 * segundo apellido del medico
	 */
	@Pattern(regex = "[A-Za-z]*",message="Ingrese solo numeros")
	public String segundoApellido;
	/**
	 * Correo electronico del medico
	 */
	public String correo;
	/**
	 * Lista de numeros telefonicos del medico
	 */
	public List<Telefono> telefonos;
	/**
	 * Direccion de residencia del medico
	 */
	public String direccion;
	/**
	 * Password actual del medico
	 */
	public String pass;
	/**
	 * Password que sera el nuevo al actualizar
	 */
	@NotNull
	@Pattern(regex = "[0-9A-Za-z]*",message="Ingrese solo numeros")
	public String nuevoPass;
	/**
	 * Password para confirmar que sea igual al nuevoPass
	 */
	@NotNull
	@Pattern(regex = "[0-9A-Za-z]*",message="Ingrese solo numeros")
	public String confirmarPass;
	/**
	 * Telefono que se actualiza
	 */
	public String numTelefono;
	/**
	 * Nuevo numero telefonico que se va a actualizar
	 */
	@NotNull
	@Pattern(regex = "[0-9]*",message="Ingrese solo numeros")
	public String nuevoTelefono;
	/**
	 * EntityManager para el manejo de registros con la base de datos
	 */
	public EntityManager em;

	public ActualizarDatosMedicoBean() {
		
		persona = (Funcionario) SesionFactory.getValor("persona");
		
		if (persona != null) {
			/*si la persona en sesion no es null se cargan todos sus datos para mostrarlos en la ventana*/
			primerNombre = persona.getPrimerNombre();
			segundoNombre = persona.getSegundoNombre();
			primerApellido = persona.getPrimerApellido();
			segundoApellido = persona.getSegundoApellido();
			correo = persona.getEmail();
			direccion = persona.getDireccion();
			pass = persona.getPassword();
			llenarTelefonos();
		}
	}

	/**
	 * Metodo que actualiza los datos de un medico que esta en sesion
	 * 
	 * @return
	 */
	public String actualizarDatos() {

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

		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Sus datos se han actualizado correctamente", null));

		return null;
	}

	/**
	 * Metodo que cambia el password de un Medico que esta en sesion
	 * 
	 * @return
	 */
	public String cambiarPassword() {

		/* si la contraseña ingresada no corresponde a la contraseña antigua */
		if (persona.getPassword().equals(pass) == false) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Su contraseña actual no coincide", null));
		} else {
			/*si la nueva contraseña no coincide con la contraseña de confirmacion*/
			if (nuevoPass.equals(confirmarPass) == false) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"La nueva contraseña no coincide con la de confirmacion",null));
			}else{
				/*si todo se cumple se actualiza la contraseña*/
				persona = (Funcionario) SesionFactory.getValor("persona");
				persona.setPassword(nuevoPass);
				em.getTransaction().begin();
				em.merge(persona);
				em.getTransaction().commit();
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Su contraseña se actualizo correctamente",null));
			}
		}
		return null;
	}
	
	/**
	 * Metodo que actualiza un telefono seleccionado del Medico que esta en sesion
	 * buscandolo por el numero telefonico
	 * @return
	 */
	public String actualizarTelefono(){
		
		Query q = em.createNamedQuery(Telefono.FIND_TELEFONO_BY_NUMERO);
		q.setParameter(Telefono.PARAMETRO_NUMERO, numTelefono);
		/*buscar el telefono que se quiere actualizar*/
		em.getTransaction().begin();
		Telefono t = (Telefono) q.getSingleResult();
		em.getTransaction().commit();
		/*cambio de numero telefonico*/
		t.setNumero(nuevoTelefono);
		/*actualizacion del numero*/
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Numero telefonico actualizado correctamente",null));
		
		return null;
	}

	/**
	 * Metodo que lista los telefonos del Medico en sesion
	 */
	public void llenarTelefonos() {
		/*consulta que saca los numeros telefonicos de una persona con el documento y tipo de documento*/
		Query q = em.createNamedQuery(Telefono.FIND_TELEFONO_BY_NUMERO_Y_TIPO_DOCUMENTO);
		/*se le manda a la consulta el documento de la persona*/
		q.setParameter(Telefono.PARAMETRO_NUMERO_DOCUMENTO,persona.getDocumento());
		/*tipo de documento de la persona, CC, TI etc*/
		q.setParameter(Telefono.PARAMETRO_TIPO_DOCUMENTO,TipoDocumentoEnum.CEDULA_CIUDAUDANIA);
		em.getTransaction().begin();
		this.telefonos = q.getResultList();
		em.getTransaction().commit();
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
