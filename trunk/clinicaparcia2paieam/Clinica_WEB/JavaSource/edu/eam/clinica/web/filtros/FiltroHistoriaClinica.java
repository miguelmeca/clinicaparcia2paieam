package edu.eam.clinica.web.filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import edu.eam.clinica.jpa.entidades.Persona;
import edu.eam.clinica.web.autorizacion.SesionFactory;

/**
 * Servlet Filter implementation class FiltroHistoriaClinica
 */
public class FiltroHistoriaClinica implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroHistoriaClinica() {
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

		HttpServletRequest req = (HttpServletRequest) request;
		
		Persona p = (Persona) SesionFactory.getValor("persona");
		/*si la persona se ha autenticado podra seguir a la pagina*/
		if(p !=null){
			chain.doFilter(request, response);
		}else{
			/*sino se redirigira a la pagina de inicio*/
			req.getRequestDispatcher("/pages/inicio.jsf").forward(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
