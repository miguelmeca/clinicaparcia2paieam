package edu.eam.clinica.web.bean.medico;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.mapping.Array;
import org.hibernate.validator.Length;
import org.hibernate.validator.Pattern;

import edu.eam.clinica.jpa.entidades.Articulo;
import edu.eam.clinica.jpa.entidades.Consulta;
import edu.eam.clinica.jpa.entidades.DetalleFormulaMedica;
import edu.eam.clinica.jpa.entidades.DetalleHistoriaClinica;
import edu.eam.clinica.jpa.entidades.DetalleOrdenMedica;
import edu.eam.clinica.jpa.entidades.Especialidad;
import edu.eam.clinica.jpa.entidades.FormulaMedica;
import edu.eam.clinica.jpa.entidades.Medicamento;
import edu.eam.clinica.jpa.entidades.OrdenMedica;
import edu.eam.clinica.jpa.entidades.Paciente;
import edu.eam.clinica.jpa.entidades.Persona;
import edu.eam.clinica.jpa.entidades.Procedimiento;
import edu.eam.clinica.jpa.entidades.Remision;
import edu.eam.clinica.jpa.entidades.utilidades.FactoryEntityManager;
import edu.eam.clinica.jpa.enumeraciones.EstadoConsultaEnum;
import edu.eam.clinica.web.autorizacion.SesionFactory;

public class AtenderConsultaBean {

	/*
	 * el paciente de la consulta
	 */
	private Paciente paciente;

	/*
	 * em es el entityManager para manipular los registros e la base de datos
	 */
	public EntityManager em;

	/*
	 * nombre del medicamento el cual se quiere  buscar para 
	 * luego mostrar una lista de medicamentos
	 * que concuerden con este nombre
	 */
	@Pattern(regex = "[a-zA-Z]*", message = "solo letras.")
	@Length(min = 1, max = 20, message = "debe tener almenos 1 caracter y maximo 30")
	private String nombreMedicamento;

	/*
	 * codigo del medicaento que se quiere agregar a la lista
	 * con este se busca el medicamento
	 */
	private Long codigoMedicamento;

	/*
	 * medicamentos que se buscan por nomnbre
	 */
	private List<Medicamento> medicamentos;

	/*
	 * medicamentos finales que se le van a recetar al paciente
	 */
	private List<Medicamento> medicamentosPaciente;

	/*
	 * medicamento que se va a ir elegiendo para agregar a los medicamentos 
	 * que el paciente llevara
	 */
	private Medicamento medicamento;

	/*
	 * prescripcion es la dosis y la frecuencia con la que se tomara el medicamento
	 */
	private String prescrpcion;

	/*
	 * es la variable que llega en session y representa la consulta que atiende el medico
	 */
	private Consulta conuslta;
	/*
	 * formula medica del paciente actual
	 */
	private FormulaMedica formula;
	
	/*
	 * recomendaciones que el medico hace al paciente
	 */
	private String recomendacion;

	// ---------------------------------------variables prescripciones

	/*
	 * procedimientos que tiene el paciente de la consulta
	 */
	private List<Procedimiento> procedimientosPaciente;

	/*
	 * codigo del procedimiento o examen que se quiere buscar
	 */
	private long codigoProcedimiento;

	/*
	 * nombre del procedimiento(s) o examen(s) que se le agregara al paciente
	 */
	private String nombreProcedimiento;

	/*
	 * lista de procedimientos candidato a elegir 
	 */
	private List<Procedimiento> procedimientos;

	/*
	 * procedimeinto elegido para agregar a la lista de procedimientos del paciente
	 */
	private Procedimiento procedimiento;
	/**
	 * orden medica que tendra el paciente
	 */
	private OrdenMedica orden;

	// ---------------------------------------variables remisiones
/*
 * remisiones  que tendra finalmente el paciente
 */
	private List<Remision> remisionesPaciente;

	/*
	 * codigo para buscar las remisiones y agregarlas a la lista de remisones
	 */
	private String codigoRemision;

	/*
	 * observacion del medico
	 */
	private String observacion;
	/*
	 * codigo de la remision que se selecciona en el combo box
	 */
	private String codComboRemision;
	
	 /*
	  * id de la especialidad la cual se va a remitir al paciente
	  */
	private long idEspecialidad;
	
	/*
	 * comentario de la remision
	 */
	private String comentarioRemision;
	//----------------------------------------variables evolucion
	/*
	 * evolucion que el medico le ha visto a l paciente
	 */
	private String evolucion;
	/*
	 * medico que atiende la consulta que viene por session
	 */
	private Persona medico;
	
	private boolean indicadorGuardar;
	
	public AtenderConsultaBean() {

		em = FactoryEntityManager.getEm();

		// aqui se saca la variable en sesion
		/*
		 * FacesContext context = FacesContext.getCurrentInstance();
		 * HttpServletRequest request = (HttpServletRequest) context
		 * .getExternalContext().getRequest(); HttpSession httpSession =
		 * request.getSession(false); Consulta consulta = (Consulta)
		 * httpSession.getAttribute("consulta");
		 */
		
		 SesionFactory session=new SesionFactory();
		 Consulta consulta =
		 (Consulta)session.getValor("consulta");
		 
		 //medico=(Persona)session.getValor("persona");
		 //conuslta = em.find(Consulta.class, 1l);
		 em.refresh(conuslta);
	
		 this.paciente = conuslta.getPaciente();
		 indicadorGuardar=false;
		 
	}

	/*
	 * este metodo retorna la regla de navegacion para ir a la histpria clinica
	 * pero antes guarda en session el paciente de la consulta
	 */
	public String irHistoriaClinica() {

		if(indicadorGuardar==true){
		
		SesionFactory session = new SesionFactory();
		session.agregarASesion("pacienteHistoria", this.paciente);
		
		//aqui va evaluacion agregarla
		/*
		HistoriaClinica hc=new HistoriaClinica();
		DetalleComentarioRevision detalle=new DetalleComentarioRevision();
		detalle.setComentario(evolucion);
		detalle.setHistoria(hc);
		ArrayList<DetalleComentarioRevision>detalles=new ArrayList<DetalleComentarioRevision>();
		detalles.add(detalle);
		Antecedente an= new Antecedente();
		hc.setPaciente(paciente);*/
		

		return "historiaClinica";
		}else{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Antes de dirigirce a la historia clinica debe guardar todos los cambios",null));
			return "";
		
		}
	}
	
	/*
	 * guar dar todo es para gusdar lo que se ha hecho en esta pagina a nivel de
	 * bese de datos. guarda la formula y la consulta
	 */
	public void guardarTodo(){
		if(formula == null || orden == null || evolucion==""){
			
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe haber culminado en su totalidad el proceso de atender conuslta",null));
		}else{
		
		this.conuslta.setFormulanMedica(formula);
		this.conuslta.setOrdenMedica(orden);
		DetalleHistoriaClinica dhc=new DetalleHistoriaClinica();
		dhc.setConsulta(this.conuslta);
		dhc.setComentario(evolucion);
		dhc.setHistoria(this.conuslta.getPaciente().getHistoriaClinica());
		this.conuslta.setDetalleHistoriaClinica(dhc);
	    
	this.conuslta.setEstado(EstadoConsultaEnum.TOMADA);
	
	em.getTransaction().begin();
	em.merge(this.conuslta);
	em.getTransaction().commit();
	
	indicadorGuardar=true;
	
	FacesContext.getCurrentInstance().addMessage(null,
			new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado cirrectamente",null));
	}
	}

	/*
	 * busca medicamentos similares por nombres
	 */
	public void buscarMedicamentos() {

		Query q = em.createNamedQuery(Medicamento.FIND_MEDICAMENTO_BY_NOMBRE);
		q.setParameter(Medicamento.PARAMENTRO_NOMBRE, "%"+nombreMedicamento+"%");
		
		this.medicamentos = (List<Medicamento>) q.getResultList();
		System.out.println(medicamentos);
		
		if(this.medicamentos.size()==0){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha encontrado ningun medicamento similar a dicho nombre",null));
		}

	}

	/*
	 * busca los procedimientos similares por nombre
	 */
	public void buscarProcedimientos() {

		Query q = em
				.createNamedQuery(Procedimiento.FIND_PROCEDIMIENTO_BY_NOMBRE);
		q.setParameter(Procedimiento.PARAMETRO_NOMBRE_PROC, "%"+nombreProcedimiento+"%");
		this.procedimientos = (List<Procedimiento>) q.getResultList();
		ArrayList<DetalleOrdenMedica> lista=new ArrayList<DetalleOrdenMedica>();
	for (int i = 0; i < procedimientos.size(); i++) {
		DetalleOrdenMedica detalle= new DetalleOrdenMedica();
		detalle.setProcedimiento(procedimientos.get(i));
		detalle.setCantidad(1);
		detalle.setOrdenMedica(orden);
		lista.add(detalle);
	
	}
	orden.setDetalleOrdenMedica(lista);
		if(this.procedimientos.size()==0){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha encontrado ningun procedimeinto similar a dicho nombre",null));
		}

	}
/*
 * busca el medicamento con el codigo y lo iguala a medicamento
 */
	public void agregarMediacmento() {
		
		
		medicamento = em.find(Medicamento.class, codigoMedicamento);

	}
/*
 * agrega el procedimiento encontrado a la variable procedimientos de paciente
 */
	public void agregarProcedimiento() {

		procedimiento = em.find(Procedimiento.class, codigoProcedimiento);
		if(procedimientos==null){
			procedimientos=new ArrayList<Procedimiento>();
		}
		procedimientos.add(procedimiento);

		DetalleOrdenMedica detalle= new DetalleOrdenMedica();
		detalle.setProcedimiento(procedimiento);
		detalle.setCantidad(1);
		
		if(orden==null){
			orden= new OrdenMedica();
			orden.setDetalleOrdenMedica(new ArrayList<DetalleOrdenMedica>());
		}

		orden.setConsulta(this.conuslta);
		orden.setDiagnostico("");

		detalle.setOrdenMedica(orden);
		
		orden.getDetalleOrdenMedica().add(detalle);
//
		
//		if(medicamentosPaciente==null){
//			medicamentosPaciente=new ArrayList<Medicamento>();
//		}
//		medicamentosPaciente.add(medicamento);
	}

	/*
	 * eliminar uno de los medicamentos que esta llevando el paciente
	 */
	public void eliminarMedicamento() {

//		for (int i = 0; i < medicamentosPaciente.size(); i++) {
//			if (medicamentosPaciente.get(i).getId().equals(codigoMedicamento)
//					) {
//
//				medicamentosPaciente.remove(i);
//			}
//		}
//if(formula.getDetallesFormulaMedica()==null){
//	formula.setDetallesFormulaMedica(new ArrayList<DetalleFormulaMedica>());
//}
		for (int i = 0; i <formula.getDetallesFormulaMedica().size(); i++) {
			if (formula.getDetallesFormulaMedica().get(i).getArticulo().getId().equals(codigoMedicamento)
					) {

				formula.getDetallesFormulaMedica().remove(i);
			}
		}

	}

	/*
	 * elimina uno de los procedimientos que adquiere el paciente en esta 
	 * atencion de consulta
	 */
	public void eliminarProcedimiento() {

//		for (int i = 0; i < procedimientos.size(); i++) {
//			if (procedimientos.get(i).getId()==codigoProcedimiento) {
//
//				procedimientos.remove(i);
//			}
//		}
if(orden.getDetalleOrdenMedica()==null){
	orden.setDetalleOrdenMedica(new ArrayList<DetalleOrdenMedica>());
}
		for (int i = 0; i <orden.getDetalleOrdenMedica().size(); i++) {
			if (orden.getDetalleOrdenMedica().get(i).getProcedimiento().getId()==codigoMedicamento
					) {

				orden.getDetalleOrdenMedica().remove(i);
			}
		}
	}

	/*
	 * se elimina la remision de las remisiones generales
	 */
	public void eliminarRemision() {

		for (int i = 0; i < remisionesPaciente.size(); i++) {
			if (remisionesPaciente.get(i).getEspecialidad().getCodigo()
					.equals(codigoRemision) == true) {

				remisionesPaciente.remove(i);
			}
		}
	}

	/*
	 * listar las especialidades que hay en la base de datos
	 */
	public List<Especialidad> getEspecialidadesAll() {
		Query q = em.createNamedQuery(Especialidad.FIND_ALL);
		return (List<Especialidad>) q.getResultList();

	}
	
	/*
	 * agregar remisiones a la consulta
	 */
	public void agregarRemisiones(){
		
		Remision remi= new Remision();
		remi.setConsulta(this.conuslta);
		remi.setObservacion(comentarioRemision);
		
		
		Especialidad esp=em.find(Especialidad.class, idEspecialidad);
		
		remi.setEspecialidad(esp);
		
		if(remisionesPaciente==null){
			remisionesPaciente=new ArrayList<Remision>();
		}
		
		remisionesPaciente.add(remi);
		this.conuslta.setRemisiones(remisionesPaciente);
		
	}

	/*
	 * agrega medicamentos a la lista general de 
	 * los medicamentos que el paciente finalmente llevara
	 */
	public void agregarMediacmentoToLista() {

		DetalleFormulaMedica detalle = new DetalleFormulaMedica();
		detalle.setArticulo(medicamento);
		detalle.setCantidad(1);

		if (formula == null) {
			formula = new FormulaMedica();
			formula.setDetallesFormulaMedica(new ArrayList<DetalleFormulaMedica>());
		}
		formula.setConsulta(conuslta);

		formula.setRecomendacion(recomendacion);

		detalle.setFormulaMedica(formula);
		detalle.setPrescripcion(prescrpcion);

		
		formula.getDetallesFormulaMedica().add(detalle);
		//temporal
		if(medicamentosPaciente==null){
			medicamentosPaciente=new ArrayList<Medicamento>();
		}
		medicamentosPaciente.add(medicamento);
		
	}

	/*
	 * lista las especialidades que hay en la basxe de datos
	 */
	public List<SelectItem> getEspecialidades(){
		
		List<SelectItem> lista= new ArrayList<SelectItem>();
		Query q = em.createNamedQuery(Especialidad.FIND_ALL);
		List<Especialidad> especials =(List<Especialidad>) q.getResultList();
		for (int i = 0; i < especials.size(); i++) {
			
			Especialidad e= especials.get(i);
			SelectItem item=new SelectItem(e.getId(),e.getNombre());
			lista.add(item);
		}
		
		return lista;
		
	}
	
	public Consulta getConuslta() {
		return conuslta;
	}

	public void setConuslta(Consulta conuslta) {
		this.conuslta = conuslta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getEvolucion() {
		return evolucion;
	}

	public void setEvolucion(String evolucion) {
		this.evolucion = evolucion;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public String getNombreMedicamento() {
		return nombreMedicamento;
	}

	public FormulaMedica getFormula() {
		return formula;
	}

	public void setFormula(FormulaMedica formula) {
		this.formula = formula;
	}

	public String getComentarioRemision() {
		return comentarioRemision;
	}

	public void setComentarioRemision(String comentarioRemision) {
		this.comentarioRemision = comentarioRemision;
	}

	public void setNombreMedicamento(String nombreMedicamento) {
		System.out.println("setter nombre medicamento:"+nombreMedicamento);
		this.nombreMedicamento = nombreMedicamento;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public long getCodigoProcedimiento() {
		return codigoProcedimiento;
	}

	public void setCodigoProcedimiento(long codigoProcedimiento) {
		this.codigoProcedimiento = codigoProcedimiento;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(ArrayList<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public String getPrimerNombre() {
		return paciente.getPrimerNombre();
	}

	public String getCodComboRemision() {
		return codComboRemision;
	}

	public void setCodComboRemision(String codComboRemision) {
		this.codComboRemision = codComboRemision;
	}

	public String getSegundoNombre() {
		return paciente.getSegundoNombre();
	}

	public String getPrimerApellido() {
		return paciente.getPrimerApellido();
	}

	public String getSegundoApellido() {
		return paciente.getSegundoApellido();
	}

	public String getEps() {
		return paciente.getEps().getNombre();
	}

	public String getOcupacion() {
		return paciente.getOcupacion();
	}

	public Long getCodigoMedicamento() {
		return codigoMedicamento;
	}

	public List<DetalleOrdenMedica> getProcedimientos() {
		if(orden==null){
			orden=new OrdenMedica();
		}
		return orden.getDetalleOrdenMedica();
	}

	public void setProcedimientos(ArrayList<Procedimiento> procedimientos) {
		this.procedimientos = procedimientos;
	}

	public void setCodigoMedicamento(Long codigoMedicamento) {
		this.codigoMedicamento = codigoMedicamento;
	}

	public List<Procedimiento> getProcedimientosPaciente() {
		return procedimientosPaciente;
	}

	public void setProcedimientosPaciente(
			ArrayList<Procedimiento> procedimientosPaciente) {
		this.procedimientosPaciente = procedimientosPaciente;
	}

	public List<DetalleFormulaMedica> getMedicamentosPaciente() {
		if(formula==null){
			formula=new FormulaMedica();
		}
		return formula.getDetallesFormulaMedica();
	}

	public void setMedicamentosPaciente(
			ArrayList<Medicamento> medicamentosPaciente) {
		this.medicamentosPaciente = medicamentosPaciente;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public String getCodigoRemision() {
		return codigoRemision;
	}

	public void setCodigoRemision(String codigoRemision) {
		this.codigoRemision = codigoRemision;
	}

	public String getPrescrpcion() {
		return prescrpcion;
	}

	public void setPrescrpcion(String prescrpcion) {
		this.prescrpcion = prescrpcion;
	}

	public String getNombreProcedimiento() {
		return nombreProcedimiento;
	}

	public void setNombreProcedimiento(String nombreProcedimiento) {
		this.nombreProcedimiento = nombreProcedimiento;
	}

	public Procedimiento getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(Procedimiento procedimiento) {
		this.procedimiento = procedimiento;
	}

	public List<Remision> getRemisionesPaciente() {
		return remisionesPaciente;
	}

	public void setRemisionesPaciente(ArrayList<Remision> remisionesPaciente) {
		this.remisionesPaciente = remisionesPaciente;
	}

	public long getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(long idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	
public String getRecomendacion() {
		return recomendacion;
	}

	public void setRecomendacion(String recomendacion) {
		this.recomendacion = recomendacion;
	}

	/*
 * retornar la fecha de nacimiento del paciente en forma de cadena
 */
	public String getFechaNacimiento() {
		Calendar c= Calendar.getInstance();
		c.setTime(paciente.getFechaNacimiento());
		int dia = c.get(Calendar.DAY_OF_MONTH);
		int mes = c.get(Calendar.MONTH);
		int anho = c.get(Calendar.YEAR);
		return dia + "/" + mes + "/" + anho;
	}
	

}
