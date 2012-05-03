package edu.eam.clinica.web.bean.funcionario;

import java.util.List;

import javax.persistence.EntityManager;

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
	/*
	 * nombre de usuario del funcionario
	 */
	private String logIn;
	/*
	 * contraseña de acceso del funcionario
	 */
	private String pass;
	
	
	/*
	 * EntityManager para hacer uso de la persistencia
	 */
	private EntityManager em;

	/*
	 * constructor
	 */
	public ActualizarDatosFuncionarioBean() {
		em = FactoryEntityManager.getEm();
		funcionario = (Funcionario) SesionFactory.getSesion();
	}

	/*
	 * metodo con el cual se actualizaran los datos personales de un funcionario
	 */
	public void actualizarDatosPersonalesFuncionario(){
		
	
		funcionario = (Funcionario) SesionFactory.getValor("persona");
		
			em.getTransaction().begin();
			
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
			
			em.merge(funcionario);
			
			
			em.getTransaction().commit();
			SesionFactory.agregarASesion("funcionario", funcionario);
		
		
		
	}
	
	/*
	 * listar los telefonos del funcionario
	 */
	public List<Telefono> getTelefonosFuncionario() {
		
		return em.createNamedQuery(funcionario.FIND_ALL).getResultList();

	}
	/*
	 * metodo con el cual se actualizaran los datos del usuario funcionario
	 */
	public void actualizarDatosUsuarioFuncionario(){
		funcionario = (Funcionario) SesionFactory.getValor("persona");
		
		em.getTransaction().begin();
		
		funcionario.setLogin(logIn);
		funcionario.setPassword(pass);
		
		em.merge(funcionario);
		
		
		em.getTransaction().commit();
		SesionFactory.agregarASesion("funcionario", funcionario);
		
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
