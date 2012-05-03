package edu.eam.clinica.web.bean.funcionario;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import edu.eam.clinica.jpa.entidades.Funcionario;
import edu.eam.clinica.jpa.utilidades.FactoryEntityManager;


public class FuncionarioBean {
	
	/*
	 * Atributos de la Entidad Funcionario
	 */
	//CODIGO DEL FUNCIONARIO
	private String codigoFun;
	//PRIMER NOMBRE DE LA PERSONA FUNCIONARIO
	private String primerNombre;
	//SEGUNDO NOMBRE DE LA PERSONA FUNCIONARIO
    private String segundoNombre;
    //PRIMER APELLIDO DE LA PERSONA FUNCIONARIO
    private String primerApellido;
    //SEGUNDO APELLIDO DE LA PERSONA FUNCIONARIO
    private String segundoApellido;
    //DIRECCION DE LA PERSONA FUNCIONARIO
    private String direccion;
    //EMAIL DE LA PERSONA FUNCIONARIO
    private String email;
	
    /*
     * EntityManager para hacer uso de la persistencia
     */
	private EntityManager em;
	

	public FuncionarioBean(){
		em=FactoryEntityManager.getEm();
	}
	
	/**
	 * metodo para buscar el funcionario
	 * @return
	 */
	public String funcionariosBuscar(){
		
		/*
		 * funcionario buscado
		 */
		Funcionario fun=em.find(Funcionario.class,codigoFun);
		
		/*
		 * validar si el funcionario si es alguien
		 */
		if(fun != null){
			/*
			 * guardar los valores del funcionario en las variables del Bean
			 */
			primerNombre=fun.getPrimerNombre();
			segundoNombre=fun.getSegundoNombre();
			primerApellido=fun.getPrimerApellido();
			primerApellido=fun.getSegundoApellido();
			direccion=fun.getDireccion();
			email=fun.getEmail();
		}else{
			/*
			 * mensaje global que indica que el funcionario no existe
			 */
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El Funcionario No pudo se encontrada", null));
		}

		return null;
	}
	


	/*
	 * GETTERS AND SETTERS***
	 */
	public String getCodigoFun() {
		return codigoFun;
	}

	public void setCodigoFun(String codigoFun) {
		this.codigoFun = codigoFun;
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

	public String getPrimerNombre() {
		return primerNombre;
	}


	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	
}
