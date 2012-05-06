package edu.eam.clinica.web.bean.funcionario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.validator.*;

import edu.eam.clinica.jpa.entidades.Articulo;
import edu.eam.clinica.jpa.entidades.DetalleFormulaMedica;
import edu.eam.clinica.jpa.entidades.FormulaMedica;
import edu.eam.clinica.jpa.entidades.Funcionario;
import edu.eam.clinica.jpa.entidades.Inventario;
import edu.eam.clinica.jpa.utilidades.FactoryEntityManager;
import edu.eam.clinica.web.autorizacion.SesionFactory;

public class EntregarFormulaMedicaBean {
	/**
	 * codigo de la formula medica
	 */
	@NotNull
	@Pattern(regex = "[0-9]*",message="El codigo solo contiene numeros")
	private long codFormula;
	
	/**
	 * EntityManager para hacer uso de la persistencia
	 */
	private EntityManager em;
	
	/**
	 * Persona (Funcionario) que inicio sesion
	 */

	private Funcionario funcionario;
	
	/**
	 * FactorySesion para obtener la sesion que se ha iniciado
	 */
	
	private SesionFactory sf;
	
	/**
	 * List para guardar los DetalleFormulaMedica que arroje la busqueda de la formula de un paciente
	 */
	List<DetalleFormulaMedica> detalles;
	
	/**
	 * Codigo de barras del articulo ingresado en el modalPanel
	 */
	private String codigoBarras;
	
	/**
	 * List para guardar los medicamentos que se pueden entregar al paciente
	 */
	List<Articulo> medicamentosEntrega;
	
	/**
	 * Atributo String para guardar el motivo de la salida del medicamento
	 */
	private String motivoSalida;
	
	/**
	 * lista para guardar las salidas de inventario que se van a crear al guardar
	 */
	List<Inventario> salidasInventario;
	
	
	public EntregarFormulaMedicaBean(){
		em=FactoryEntityManager.getEm();
	    funcionario=(Funcionario) sf.getValor("persona");	
	    funcionario=em.find(Funcionario.class, "2");
	    medicamentosEntrega=new ArrayList();
	    salidasInventario=new ArrayList();
	}
	
	/**
	 * metodo para buscar la formula medica ingresada por un funcionario para
	 *  la entrega de medicamentos de un paciente
	 * @return regla de navegacion: en esta caso ninguna
	 */
	public String buscarFormulaMedica(){
		
		/*
		 * Buscar la formula medica del paciento con el codigo ingresado por el funcionario
		 */
		FormulaMedica formula=em.find(FormulaMedica.class,codFormula);
		
		/*
		 * verificar si la formula medica existe o no
		 */
		if(formula !=null){
			
			/*
			 * cargar los DetalleFormulaMedica de la formula buscada
			 * query para sacar los detalles con sus medicamentos de la formula encontrada
			 */
			Query consulta=em.createNamedQuery(DetalleFormulaMedica.FIND_DETALLE_FORMULA_MEDICA_BY_ID_FORMULA_MEDICA);
			consulta.setParameter(DetalleFormulaMedica.PARAMETRO_ID_FORMULA_MEDICA,codFormula);
			
			/*
			 * los detalles con sus medicamentos quedan guardados en la lista llamada detalles
			 */
			
			detalles=consulta.getResultList();
			
		}else{
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La Formula No pudo se encontrada", null));
		}
		
		return null;
	}
	

	/**
	 * metodo para verificar si el medicamento tiene stock en el inventario de la clinica
	 * @return
	 */
	public String validarMedicamento(){
		
		/*
		 * query para buscar por codigo de barras el articulo en el inventario que no tenga
		 * salidaInventario
		 */
	     Query query=em.createNamedQuery(Inventario.CONSULTA_FIND_BY_ARTICULO);
	     query.setParameter(Inventario.PARAMETRO_CODIGO, codigoBarras);
	     
	     /*
	      * lista donde se guardan los inventarios encontrados 
	      */
	     List<Inventario> articuloInventario=query.getResultList();
	     
	     /*
	      * variable para guardar la cantidad que se debe entregar del medicamento
	      */
	     int cantidadArticulo=0;
	     
	     
	     for (DetalleFormulaMedica detalle : detalles) {
			
	    	 if(codigoBarras.equals(detalle.getArticulo().getCodigo())){
	    		 cantidadArticulo=detalle.getCantidad();
	    	 }
		}

	    	 /*
	    	  * preguntar si la cantidad es menor o igual a la cantidad que hay en el inventario
	    	  */
	    	 if(cantidadArticulo <= articuloInventario.size()){
	    		 
	    		 /*
	    		  * crear salidas del inventario para la cantidad de medicamentos que se van
	    		  * a entregar al paciente
	    		  */
	    		 for (int i=0; i<= cantidadArticulo-1;i++) {
	    			 /*
	    			  * sacar el inventario de la lista de inventarios encontrada anteriormente
	    			  */
	    			 
	    			 Inventario medicamentoAEntregar=articuloInventario.get(i);
	    			 
	    			 /*
	    			  * validar si el codigo del articulo del inventario es el mismo
	    			  * que se ingreso en la variable codigoBarras
	    			  */
	    			 if(medicamentoAEntregar.getArticulo().getCodigo().equals(codigoBarras)){
	    				
	    				 
	    				 /*
		    			  * la fecha de actual para modificar la fecha de salida del inventario
		    			  */
		    			 Calendar hoy=Calendar.getInstance();
		    			
		    			 
		    			 /*
		    			  * cambiar la hora de la salida 
		    			  */
		    			 medicamentoAEntregar.setFechaSalida(hoy.getTime());
		    			 /*
		    			  * guardar el funcionario que va a entregar el medicamento
		    			  */
		    			 medicamentoAEntregar.setFuncionarioSalida(funcionario);
		    			 /*
		    			  * guardar el motivo por el cual se da salida a ese medicamento
		    			  */
		    			 medicamentoAEntregar.setMotivoSalida(motivoSalida);
		    			 
		    			 medicamentosEntrega.add(medicamentoAEntregar.getArticulo());
		    			 
		    			 for (int j=0;j<detalles.size();j++) {
							
		    				 DetalleFormulaMedica detalle=detalles.get(j);
		    				 if(detalle.getArticulo().getCodigo().equals(medicamentoAEntregar.getArticulo().getCodigo())){
		    					 detalles.remove(j);
		    				 }
						}
		    			 
		    			 salidasInventario.add(medicamentoAEntregar);
		    			 
		    			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"El medicamento esta en la canasta :)", null));
	    			 }
	    			
				}
	    	 
	     }else{
	    	 FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"la formula no puede ser entregada por falta de medicamentos",null));
	     }
		return null;
	}

	public String guardarFormula(){
		
		for (Inventario inventario : salidasInventario) {
			em.getTransaction().begin();
			em.merge(inventario);
			em.getTransaction().commit();		
		}
		
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Se guardo con exito :)",null));
		
		return null;
	}
	
	public long getCodFormula() {
		return codFormula;
	}

	public void setCodFormula(long codFormula) {
		this.codFormula = codFormula;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public SesionFactory getSf() {
		return sf;
	}

	public void setSf(SesionFactory sf) {
		this.sf = sf;
	}

	public List<DetalleFormulaMedica> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleFormulaMedica> detalles) {
		this.detalles = detalles;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public List<Articulo> getMedicamentosEntrega() {
		return medicamentosEntrega;
	}

	public void setMedicamentosEntrega(List<Articulo> medicamentosEntrega) {
		this.medicamentosEntrega = medicamentosEntrega;
	}

	public String getMotivoSalida() {
		return motivoSalida;
	}

	public void setMotivoSalida(String motivoSalida) {
		this.motivoSalida = motivoSalida;
	}

	
	
}
