package edu.eam.clinica.web.autorizacion;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Clase que implementa una factoria para la session http de la aplicacion.
 * 
 * @author caferrerb@gmail.com
 * @version 1.0
 * 
 */
public class SesionFactory {

	/**
	 * Singleton de la clase.
	 */
	public static SesionFactory sesion;

	/**
	 * Metodo para obtener la instancia Singleton del la fabrica.
	 * 	 * 
	 * @return la sesion
	 */
	public static SesionFactory getInstance() {
		if (sesion == null) {
			sesion = new SesionFactory();

		}
		return sesion;
	}
	/**
	 * Metodo que retorna la sesion http.
	 * @return la sesion.
	 */
	public static HttpSession getSesion() {

		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);

	}
	/**
	 * Metodo para agregar a sesion una variable
	 * @param nombre nombde de la variable
	 * @param valor valor del objeto.
	 */
	public static synchronized void agregarASesion(String nombre,Object valor){
		getSesion().setAttribute(nombre, valor);
	}
	/**
	 * Metodo para recuperar una variable de
	 * @param nombre
	 * @return objeto que se quiere recuperar de sesion.
	 */
	public static synchronized Object getValor(String nombre){
		
		return getSesion().getAttribute(nombre);
	}

}
