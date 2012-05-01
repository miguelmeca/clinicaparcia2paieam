package edu.eam.clinica.web.bean.funcionario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.validator.*;

import edu.eam.clinica.jpa.entidades.DetalleFormulaMedica;
import edu.eam.clinica.jpa.entidades.FormulaMedica;
import edu.eam.clinica.jpa.entidades.Funcionario;
import edu.eam.clinica.jpa.utilidades.FactoryEntityManager;
import edu.eam.clinica.web.autorizacion.SesionFactory;

public class EntregarFormulaMedicaBean {
	/*
	 * codigo de la formula medica
	 */
	@NotNull
	@Pattern(regex = "[0-9]*",message="El codigo solo contiene numeros")
	private long codFormula;
	
	/*
	 * EntityManager para hacer uso de la persistencia
	 */
	private EntityManager em;
	
	/*
	 * lista de detallesFormulaMedica para guardar los medicamentos de la formula
	 */
	
	private List<DetalleFormulaMedica> detalles;
	
	/*
	 * Persona (Funcionario) que inicio sesion
	 */

	private Funcionario funcionario;
	
	/*
	 * FactorySesion para obtener la sesion que se ha iniciado
	 */
	
	private SesionFactory sf;
	
	public EntregarFormulaMedicaBean(){
		em=FactoryEntityManager.getEm();
	    funcionario=(Funcionario) sf.getSesion();	
	}
	
	public String buscarFormulaMedica(){
		
		FormulaMedica formula=em.find(FormulaMedica.class,codFormula);
		
		/*
		 * verificar si la formula medica existe o no
		 */
		if(formula !=null){
			
			/*
			 * cargar los medicamentos que esten en la formula encontrada
			 */
			
			/*
			 * consulta para sacar los medicamentos de la formula medica encontrada
			 */
			Query consulta=em.createNamedQuery(DetalleFormulaMedica.FIND_DETALLE_FORMULA_MEDICA_BY_ID_FORMULA_MEDICA);
			consulta.setParameter(DetalleFormulaMedica.PARAMETRO_ID_FORMULA_MEDICA,codFormula);
			
			/*
			 * los medicamentos quedan guardados en la lista llamada detalles
			 */
			detalles=consulta.getResultList();
			
		}else{
			/*
			 * lanzar mensajes globales inidicandole al funcionario que la formula ingresada no existe
			 */
		}
		
		return null;
	}

	public long getCodFormula() {
		return codFormula;
	}

	public void setCodFormula(long codFormula) {
		this.codFormula = codFormula;
	}

	public List<DetalleFormulaMedica> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleFormulaMedica> detalles) {
		this.detalles = detalles;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	
}
