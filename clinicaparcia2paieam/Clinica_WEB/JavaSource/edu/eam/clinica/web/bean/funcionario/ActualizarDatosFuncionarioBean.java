package edu.eam.clinica.web.bean.funcionario;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.eam.clinica.jpa.entidades.Ciudad;
import edu.eam.clinica.jpa.entidades.Funcionario;
import edu.eam.clinica.jpa.entidades.Telefono;
import edu.eam.clinica.jpa.enumeraciones.SexoEnum;
import edu.eam.clinica.jpa.enumeraciones.TipoDocumentoEnum;
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
	private Telefono telefono;
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
	
	
	/*
	 * EntityManager para hacer uso de la persistencia
	 */
	private EntityManager em;

	/**
	 * constructor
	 */
	public ActualizarDatosFuncionarioBean() {
		em = FactoryEntityManager.getEm();
		funcionario = (Funcionario) SesionFactory.getValor("persona");
	}

	/**
	 * metodo con el cual se actualizaran los datos personales de un funcionario
	 */
	public void actualizarDatosPersonalesFuncionario(){
		
	
		funcionario = (Funcionario) SesionFactory.getValor("persona");
		
			em.getTransaction().begin();
			
			/*
			funcionario.setCiudad(ciudad);
			funcionario.setDireccion(direccion);
			funcionario.setDocumento(documento);
			funcionario.setEmail(eMail);
			funcionario.setPrimerApellido(primerApellido);
			funcionario.setPrimerNombre(primerNombre);
			funcionario.setSegundoApellido(segundoApellido);
			funcionario.setSegundoNombre(segundoNombre);
			funcionario.setSexo(sexo);
			funcionario.setTipoDocumento(tipoDocumento);
			*/
			
			em.merge(funcionario);
			
			
			em.getTransaction().commit();
			SesionFactory.agregarASesion("funcionario", funcionario);
			
			//mandar un mesnaje global con el exito.
		
		
		
	}
	
	/**
	 * listar los telefonos del funcionario
	 */
	public List<Telefono> getTelefonosFuncionario() {
		
		//buscar los telefonos por persona... hacer la consulta.
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
			funcionario.setPassword(passNuevo);
			
			em.merge(funcionario);
			em.getTransaction().commit();
			SesionFactory.agregarASesion("funcionario", funcionario);
		}
		
		
		
		//mensaje global con el exito de la operacion.
		
	}

	/*
	 *Getters and Setter
	 */
	public Funcionario getFuncionario() {
		return funcionario;
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
