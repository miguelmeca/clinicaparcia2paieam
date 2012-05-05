package edu.eam.clinica.web.bean.funcionario;

import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.eam.clinica.jpa.entidades.Ciudad;
import edu.eam.clinica.jpa.entidades.Funcionario;
import edu.eam.clinica.jpa.entidades.Telefono;
import edu.eam.clinica.jpa.enumeraciones.SexoEnum;
import edu.eam.clinica.jpa.enumeraciones.TipoDocumentoEnum;
import edu.eam.clinica.jpa.enumeraciones.TipoTelefonoEnum;
import edu.eam.clinica.jpa.utilidades.FactoryEntityManager;
import edu.eam.clinica.web.autorizacion.SesionFactory;
import edu.eam.clinica.web.filtros.FiltroFuncionario;

public class ActualizarDatosFuncionarioBean {

	/*
	 * funcionaro logueado
	 */
	private Funcionario funcionario;
	/*
	 * documento del funcionario
	 */
	private String documento;
	/*
	 * el tipo de documento del funcionario
	 */
	private TipoDocumentoEnum tipoDocumento;
	/*
	 * primer nombre del funcionario
	 */
	private String primerNombre;
	/*
	 * segundo nombre del funcionario
	 */
	private String segundoNombre;
	/*
	 * primer apellido del funcionario
	 */
	private String primerApellido;
	/*
	 * segundo apellido del funcionario
	 */
	private String segundoApellido;
	/*
	 * direccion del funcionario
	 */
	private String direccion;
	/*
	 * ciudad del funcionario
	 */
	private Ciudad ciudad;
	/*
	 * sexo del funcionario (masculino) o (femenino)
	 */
	private SexoEnum sexo;
	/*
	 * correo electronico (E-Mail) del funcionario
	 */ 
	private String eMail;
	/*
	 * telefonos del funcionario
	 */
	private List<Telefono> telefonos;
	/**
	 * telefono  del funcionario
	 */
	private Telefono telefono;
	/**
	 * tipo del telefono del funcionario
	 */
	private TipoTelefonoEnum tipoTelefono;
	/**
	 * Telefono a agregar del funcionario
	 */
	private String telefonoAgregar;
	/**
	 * telefono a eliminar el funcionario
	 */
	private String telefonoBorrar;
	/*
	 * nombre de usuario del funcionario
	 */
	private String logIn;
	/*
	 * contraseña de acceso del funcionario 
	 */
	private String passViejo;
	/**
	 * Contraseña nueva para el funcionario
	 */
	private String passNuevo;
	/**
	 * contraseña para verificar si la nueva es igual a esta 
	 */
	private String passConfirmacion;
	
	
	/*
	 * EntityManager para hacer uso de la persistencia
	 */
	private EntityManager em;

	/**
	 * constructor
	 */
	public ActualizarDatosFuncionarioBean() {
		em = FactoryEntityManager.getEm();
		funcionario=em.find(Funcionario.class, "1234");
		SesionFactory.agregarASesion("persona", funcionario);
		documento = funcionario.getDocumento();
		tipoDocumento = funcionario.getTipoDocumento();
		primerNombre = funcionario.getPrimerNombre();
		segundoNombre = funcionario.getSegundoNombre();
		primerApellido = funcionario.getPrimerApellido();
		segundoApellido = funcionario.getSegundoApellido();
		direccion = funcionario.getDireccion();
		ciudad = funcionario.getCiudad();
		sexo = funcionario.getSexo();
		eMail = funcionario.getEmail();
		
		funcionario = (Funcionario) SesionFactory.getValor("persona");
		
		
		
	}

	/**
	 * metodo con el cual se actualizaran los datos personales de un funcionario
	 */
	public void actualizarDatosPersonalesFuncionario(){
		
	
		funcionario = (Funcionario) SesionFactory.getValor("persona");
		
			em.getTransaction().begin();
			
			
			funcionario.setCiudad(ciudad);
			funcionario.setDireccion(direccion);
			//funcionario.setDocumento(documento);
			funcionario.setEmail(eMail);
			funcionario.setPrimerApellido(primerApellido);
			funcionario.setPrimerNombre(primerNombre);
			funcionario.setSegundoApellido(segundoApellido);
			funcionario.setSegundoNombre(segundoNombre);
			funcionario.setSexo(sexo);
			//funcionario.setTipoDocumento(tipoDocumento);
			
			
			em.merge(funcionario);
			
			
			em.getTransaction().commit();
			SesionFactory.agregarASesion("funcionario", funcionario);
			
			//mandar un mesnaje global con el exito.
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"El funcionario se a actualizado con exito",null));
		
		
		
	}
	/**
	 * metodo para agregar un telefono al funcionario
	 */
	public void agregarTelefono(){
		
		em.getTransaction().begin();
		telefono=new Telefono();
		telefono.setNumero(telefonoAgregar);
		telefono.setPersona(funcionario);
		telefono.setTipo(TipoTelefonoEnum.FIJO);
		em.persist(telefono);
		em.getTransaction().commit();
		
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage
				(FacesMessage.SEVERITY_INFO,"Se agrego telefono al funcionario con exito",null));

	}
	
	/**
	 * metodo para eliminar un telefono del funcionario
	 */
	public void borrarTelefono(){
		em.getTransaction().begin();
		
		Query q=em.createNamedQuery(Telefono.FIND_TELEFONO_BY_NUMERO);
		q.setParameter(Telefono.PARAMETRO_NUMERO, telefonoBorrar);
		em.remove(telefonoBorrar);
		
		em.getTransaction().commit();
	}
	
	/**
	 * listar los telefonos del funcionario
	 */
	public List<Telefono> getTelefonosFuncionario() {
		
		
		funcionario = (Funcionario) SesionFactory.getValor("persona");
		
		
		Query q=em.createNamedQuery(Telefono.FIND_TELEFONO_BY_NUMERO_Y_TIPO_DOCUMENTO);
		q.setParameter(Telefono.PARAMETRO_NUMERO_DOCUMENTO, funcionario.getDocumento());
		q.setParameter(Telefono.PARAMETRO_TIPO_DOCUMENTO, funcionario.getTipoDocumento());
		telefonos=q.getResultList();
		
		
		
		return telefonos;
	}
	/*
	 * metodo con el cual se actualizaran los datos del usuario funcionario
	 */
	public void actualizarDatosUsuarioFuncionario(){
		funcionario = (Funcionario) SesionFactory.getValor("persona");
		
		em.getTransaction().begin();
		
		
		//se validad q el password digitado sea el mismo al anterior, para poderlo actualizar
		if(funcionario.getPassword().equals(passViejo)){
			if(passNuevo.equals(passConfirmacion)){
			funcionario.setPassword(passNuevo);
			
			em.merge(funcionario);
			em.getTransaction().commit();
			SesionFactory.agregarASesion("funcionario", funcionario);
			
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"El password se ha actualizado con exito",null));

			}else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Las contraseñas no son iguales, intente de nuevo",null));

			}
			
		}else{
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Las contraseñas no son iguales, intente de nuevo",null));
		}
		
				
	}

	/*
	 *Getters and Setter
	 */
	
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public String getPassConfirmacion() {
		return passConfirmacion;
	}

	public void setPassConfirmacion(String passConfirmacion) {
		this.passConfirmacion = passConfirmacion;
	}

	public String getTelefonoAgregar() {
		return telefonoAgregar;
	}

	public void setTelefonoAgregar(String telefonoAgregar) {
		this.telefonoAgregar = telefonoAgregar;
	}

	public String getTelefonoBorrar() {
		return telefonoBorrar;
	}

	public void setTelefonoBorrar(String telefonoBorrar) {
		this.telefonoBorrar = telefonoBorrar;
	}

	public Telefono getTelefono() {
		return telefono;
	}

	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}


	public String getLogIn() {
		return logIn;
	}

	public void setLogIn(String logIn) {
		this.logIn = logIn;
	}

	public String getPassViejo() {
		return passViejo;
	}

	public void setPassViejo(String passViejo) {
		this.passViejo = passViejo;
	}

	public String getPassNuevo() {
		return passNuevo;
	}

	public void setPassNuevo(String passNuevo) {
		this.passNuevo = passNuevo;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public TipoDocumentoEnum getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoEnum tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public List<Telefono> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<Telefono> telefonos) {
		this.telefonos = telefonos;
	}

	
	
	

}
