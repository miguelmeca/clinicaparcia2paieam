package edu.eam.clinica.web.bean.funcionario;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

import edu.eam.clinica.jpa.entidades.Articulo;
import edu.eam.clinica.jpa.entidades.Consulta;
import edu.eam.clinica.jpa.entidades.Funcionario;
import edu.eam.clinica.jpa.entidades.Inventario;
import edu.eam.clinica.jpa.entidades.Medicamento;
import edu.eam.clinica.jpa.enumeraciones.GrupoTerapeuticoEnum;
import edu.eam.clinica.jpa.enumeraciones.PresentacionEnum;
import edu.eam.clinica.jpa.utilidades.FactoryEntityManager;
import edu.eam.clinica.web.autorizacion.SesionFactory;

public class InventarioBean  {

/**
 * parametros del InvetarioBean
 */
	private EntityManager em;
	private Funcionario funcionario;
	private String codigoArticulo;
	private String codigoBarras;
	private Date fechaIngreso;
	private Articulo articulo;
	private String nombre;
	
	
	
	/**
	 * Constructor InventarioBean
	 */
	public InventarioBean(){
		
		em=FactoryEntityManager.getEm();
		funcionario=(Funcionario)SesionFactory.getSesion();
	}
	
	/**
	 * metodo que lista los medicamentos utulizando una query que proyecta nombre precio y un count que cuenta por nombres de un grupo por nombre
	 * @return
	 */
	public List<Articulo> getMedicamentos(){
		
		Query query= em.createNamedQuery(Inventario.CONSULTA_FIND_BY_MEDICAMENTOS);
		List<Articulo>medicamentos= query.getResultList();
		return medicamentos;
		
	}
	/**
	 * metodo para crear el medicamento en el inventario
	 * utilizo el codigoarticulo que me digita el usuario y busco un articulo con el y si encuentra algo procedo 
	 * a buscar el codigo de barra de inventario y si no encutro nada lo creo
	 */
	public void crearInventario(){
	
		Articulo a=em.find(Articulo.class, codigoArticulo);
		
		if(a!=null){
			
			Inventario i=em.find(Inventario.class,codigoBarras);
			if(i==null){
				
				em.getTransaction().begin();
				Inventario inven= new Inventario(a, fechaIngreso, null, codigoBarras, null, funcionario,null);
				em.persist(inven);
				em.flush();
				em.getTransaction().commit();
			}
			
			
		}
		
		
		
	}

	/**
	 * metodo que filtra los medicamentos por el nombre entrdas y salidas
	 * @return
	 */
	public List<Inventario>getfiltrando(){
		
		Query query= em.createNamedQuery(Inventario.CONSULTA_FIND_BY_NOMBRE);
		query.setParameter(Inventario.PARAMENTRO_NOMBRE,nombre);
		
		List<Inventario>inventario= query.getResultList();
		
		return inventario;
		
		
		
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getCodigoArticulo() {
		return codigoArticulo;
	}

	public void setCodigoArticulo(String codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

	
	
	
	
}
