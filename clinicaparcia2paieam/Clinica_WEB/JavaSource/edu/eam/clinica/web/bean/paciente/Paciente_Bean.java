package edu.eam.clinica.web.bean.paciente;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;


import edu.eam.clinica.jpa.entidades.Articulo;
import edu.eam.clinica.jpa.entidades.Inventario;
import edu.eam.clinica.jpa.entidades.Persona;
import edu.eam.clinica.jpa.entidades.Telefono;
import edu.eam.clinica.jpa.entidades.User;
import edu.eam.clinica.jpa.utilidades.FactoryEntityManager;

public class Paciente_Bean {

		


		private EntityManager em;
		
		private Persona persona;
		
		private String primerNombre;
		private String segundoNombre;		
		private String primerApellido;
		private String segundoApellido;
		private String direccion;
		private String email;
		private String usuario;
		private String contraseña;
		private List<Telefono> telefonos;
		

		public Paciente_Bean() {
			
			em=FactoryEntityManager.getEm();
			
		HttpSession sesion=(HttpSession) FacesContext.getCurrentInstance()
			.getExternalContext().getSession(false);
		persona= (Persona)sesion.getAttribute("persona");
		primerNombre=persona.getNombre();
		segundoNombre=persona.getSegundoNombre();
		
		primerApellido=persona.getPrimerApellido();
		segundoApellido=persona.getSegundoApellido();
		
		direccion=persona.getDireccion();
		email=persona.getEmail();
		usuario=persona.getUser().getUser();
		contraseña=persona.getUser().getPassword();
		
		telefonos=persona.getTelefonos();
		
		}
		
		public String actualizarDatos(){
			
			persona.setPrimerNombre(primerNombre);
			persona.setPrimerNombre(segundoNombre);
			persona.setPrimerNombre(primerApellido);
			persona.setPrimerNombre(segundoApellido);
			persona.setDireccion(direccion);
			persona.setDireccion(email);
			
			em.persist(persona);
			return null;
			
		}
		
		public String actualizarInicioSesion(){
			User us=new User();
			us.setPersona(persona);
			us.setUser(usuario);
			us.setPassword(contraseña);
			persona.setUser(us);
			
			return null;
		}
		
		public List<Telefono> getTelefonos(){
			
			Query query= em.createNamedQuery(Telefono.FIND_ALL);
			List<Telefono>tel= query.getResultList();
			return tel;
			
		}
		
		public String iniciarSesion(){
			return null;
		}

		public EntityManager getEm() {
			return em;
		}

		public void setEm(EntityManager em) {
			this.em = em;
		}

		public Persona getPersona() {
			return persona;
		}

		public void setPersona(Persona persona) {
			this.persona = persona;
		}

		public String getNombrePaciente(){
		return	persona.getNombre();
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

		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}

		public String getContraseña() {
			return contraseña;
		}

		public void setContraseña(String contraseña) {
			this.contraseña = contraseña;
		}

		

		public void setTelefonos(List<Telefono> telefonos) {
			this.telefonos = telefonos;
		}
		
		
	
	
}

	
	

