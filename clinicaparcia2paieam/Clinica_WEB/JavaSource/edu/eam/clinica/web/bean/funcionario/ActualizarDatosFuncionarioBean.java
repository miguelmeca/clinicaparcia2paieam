package edu.eam.clinica.web.bean.funcionario;

import java.util.List;

import javax.persistence.EntityManager;

import edu.eam.clinica.jpa.entidades.Funcionario;
import edu.eam.clinica.jpa.entidades.Telefono;
import edu.eam.clinica.jpa.utilidades.FactoryEntityManager;
import edu.eam.clinica.web.autorizacion.SesionFactory;

public class ActualizarDatosFuncionarioBean {

	/*
	 * funcionaro logueado
	 */
	private Funcionario funcionario;

	private String documento="meme";
	/*
	 * EntityManager para hacer uso de la persistencia
	 */
	private EntityManager em;

	public ActualizarDatosFuncionarioBean() {

		em = FactoryEntityManager.getEm();
		//funcionario = (Funcionario) SesionFactory.getSesion();
		//funcionario=em.find(Funcionario.class, 1234);
		//documento=funcionario.getDocumento();
	}

	public List<Telefono> getTelefonosFuncionario() {
		if (funcionario != null) {
			return funcionario.getTelefonos();
		} else {
			/*
			 * lanzar mensaje global indicando que no hay un funcionario en la
			 * sesion
			 */
			return null;
		}

	}

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
	
	

}
