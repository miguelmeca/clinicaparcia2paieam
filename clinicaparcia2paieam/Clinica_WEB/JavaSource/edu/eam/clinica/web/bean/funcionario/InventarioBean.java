package edu.eam.clinica.web.bean.funcionario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

import org.hibernate.validator.Pattern;

import edu.eam.clinica.jpa.entidades.Articulo;
import edu.eam.clinica.jpa.entidades.Consulta;
import edu.eam.clinica.jpa.entidades.Funcionario;
import edu.eam.clinica.jpa.entidades.Inventario;
import edu.eam.clinica.jpa.entidades.ListarMedicamentos;
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
	private Date fechaIngreso= new Date();
	private Articulo articulo;
	
	@Pattern(regex = "[a-zA-Z]*", message = "solo letras.")
	private String nombre;
	private List<Articulo>articulos;
	private ArrayList<ListarMedicamentos>listaMedicamentos;
	private List<Inventario>filtrados;
	
	
	
	
	/**
	 * Constructor InventarioBean
	 */
	public InventarioBean(){
		
		em=FactoryEntityManager.getEm();
		funcionario=(Funcionario)SesionFactory.getSesion();
		listaMedicamentos= new ArrayList<ListarMedicamentos>();
	}
	
	/**
	 * metodo que lista los medicamentos utulizando  2 consultas la primera saca todos los articulos a esta le saco un codigo y con dicho codigo hago
	 * otra consulta que me cuenta cuantas cantidades tengo de ese articulo o medicamento
	 * @return
	 */
	public List<ListarMedicamentos> getListaMedicamentos(){
		
		Query query= em.createNamedQuery(Articulo.FIND_ALL);
		articulos= query.getResultList();
		
		for(int i=0;i<articulos.size();i++){
			
			long codigo =articulos.get(i).getId();
			String nombre= articulos.get(i).getNombre();
			
			Query query2= em.createNamedQuery(Inventario.FIND_ARTICULO_BY_CANTIDAD);
			query2.setParameter(Inventario.PARAMETRO_CODIGO, codigo);
			
			int cantidad= (Integer) query2.getSingleResult();
			ListarMedicamentos medicamento=new ListarMedicamentos(nombre, codigo, cantidad);
			listaMedicamentos.add(medicamento);
		}
		return listaMedicamentos;
		
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
				Inventario inven= new Inventario(a, fechaIngreso, null, codigoBarras, null, funcionario,null);
				em.getTransaction().begin();
				em.persist(inven);
				em.getTransaction().commit();
			}	
		}	
	}

	/**
	 * metodo que filtra los medicamentos por el nombre entrdas y salidas
	 * @return
	 */
	public List<Inventario> buscarfiltrado(){
		
		Query query= em.createNamedQuery(Articulo.FIND_ARTICULO_BY_NOMBREARTI);
		query.setParameter(Articulo.PARAMENTRO_NOMBREART,nombre);
		
		List<Articulo>articulitos=query.getResultList();
		
		for(int i=0;i<articulitos.size();i++){
			long codigos=articulitos.get(i).getId();
			Query query2= em.createNamedQuery(Inventario.CONSULTA_FIND_BY_CODIGO);
			query2.setParameter(Inventario.PARAMETRO_CODIGO, codigos);
			filtrados= query2.getResultList();
		}
		
		return filtrados;
	
		
		
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

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	public List<Inventario> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<Inventario> filtrados) {
		this.filtrados = filtrados;
	}

	
	

	
	
	
	
}
