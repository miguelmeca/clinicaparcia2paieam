package edu.eam.clinica.web.bean.funcionario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

public class InventarioBean {

	/**
	 * parametros del InvetarioBean
	 */
	private EntityManager em;
	private Funcionario funcionario;

	@Pattern(regex = "[0-9]*", message = "solo numeros porfavor.")
	private String codigoArticulo;

	@Pattern(regex = "[0-9]*", message = "solo numeros porfavor.")
	private String codigoBarras;

	private Date fechaIngreso = new Date();
	private Articulo articulo;

	@Pattern(regex = "[a-zA-Z ]*", message = "Solo Letras.")
	private String nombre;

	private List<Articulo> articulos;
	private ArrayList<ListarMedicamentos> listaMedicamentos;
	private List<Inventario> filtrados;
	private List<Inventario> inventarioEntrada;
	private List<Inventario> inventarioSalidad;

	/**
	 * Constructor InventarioBean
	 */
	public InventarioBean() {

		em = FactoryEntityManager.getEm();
		funcionario = (Funcionario) SesionFactory.getValor("persona");
		
		
		
		
		listaMedicamentos = new ArrayList<ListarMedicamentos>();
	}

	/**
	 * metodo que lista los medicamentos 
	 * 
	 * @return
	 */
	public List<Articulo> getArticulos() {

		Query query = em.createNamedQuery(Articulo.FIND_ALL);
		return articulos = query.getResultList();
		
		

	}

	

	/**
	 * metodo para crear el medicamento en el inventario utilizo el
	 * codigoarticulo que me digita el usuario y busco un articulo con el y si
	 * encuentra algo procedo a buscar el codigo de barra de inventario y si no
	 * encutro nada lo creo
	 */
	public void crearInventario() {

		Articulo a = em.find(Articulo.class, Long.parseLong(codigoArticulo));

		if (a != null) {

			List<Inventario> i = em
					.createNamedQuery(Inventario.CONSULTA_FIND_BY_CODIGOBARRAS)
					.setParameter(Inventario.PARAMETRO_CODIGO, codigoBarras)
					.getResultList();

			if (i.size() == 0) {
				em.getTransaction().begin();
				Inventario inven = new Inventario(a, new Date(), null,
						codigoBarras, null, funcionario, null);

				em.persist(inven);
				em.getTransaction().commit();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"MEDICAMENTO REGISTRADO", null));

			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"CODIGO YA REGISTRADO", null));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"EL ARTICULO NO EXISTE", null));
		}

	}

	/**
	 * metodo que filtra los medicamentos por el nombre entrdas y salidas
	 * 
	 * @return
	 */
	public String buscarfiltrado() {

		Query query = em.createNamedQuery(Inventario.CONSULTA_FIND_BY_ENTRADA_ARTICULO);
		query.setParameter(Inventario.PARAMENTRO_NOMBRE, nombre);
		inventarioEntrada = query.getResultList();

		Query query2 = em.createNamedQuery(Inventario.CONSULTA_FIND_BY_SALIDA_ARTICULO);
		query2.setParameter(Inventario.PARAMENTRO_NOMBRE, nombre);

		inventarioSalidad = query2.getResultList();

		return null;

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

	

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	public List<Inventario> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<Inventario> filtrados) {
		this.filtrados = filtrados;
	}

	public List<Inventario> getInventarioEntrada() {
		return inventarioEntrada;
	}

	public void setInventarioEntrada(List<Inventario> inventarioEntrada) {
		this.inventarioEntrada = inventarioEntrada;
	}

	public List<Inventario> getInventarioSalidad() {
		return inventarioSalidad;
	}

	public void setInventarioSalidad(List<Inventario> inventarioSalidad) {
		this.inventarioSalidad = inventarioSalidad;
	}
	public ArrayList<ListarMedicamentos> getListaMedicamentos() {
		return listaMedicamentos;
	}

	public void setListaMedicamentos(ArrayList<ListarMedicamentos> listaMedicamentos) {
		this.listaMedicamentos = listaMedicamentos;
	}

}
