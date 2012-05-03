package edu.eam.clinica.web.filtros;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.eam.clinica.jpa.entidades.Medico;
import edu.eam.clinica.jpa.entidades.Persona;
import edu.eam.clinica.web.autorizacion.SesionFactory;

/**
 * Servlet Filter implementation class Filtro_Medico
 */
public class FiltroMedico implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroMedico() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//Se obtiene el request de tipo http.
		HttpServletRequest req=(HttpServletRequest) request;
		
		//se obtiene la persona de sesion.
		Medico user=(Medico) SesionFactory.getValor("medico");
		//esta en sesion, sigue la peticion.
		if(user!=null){
			
			/*se envia al HttpSession la variable de sesion de tipo medico*/
			SesionFactory.agregarASesion("medico", user);
			
			chain.doFilter(request, response);
		}else{
			//se envia a inicio.
			req.getRequestDispatcher("/pages/inicio.jsf").forward(request, response);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
