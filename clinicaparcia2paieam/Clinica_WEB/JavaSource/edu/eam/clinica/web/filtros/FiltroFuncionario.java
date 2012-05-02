package edu.eam.clinica.web.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import edu.eam.clinica.jpa.entidades.Funcionario;
import edu.eam.clinica.jpa.entidades.Persona;
import edu.eam.clinica.web.autorizacion.SesionFactory;

public class FiltroFuncionario implements Filter{

	
	
	public FiltroFuncionario() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		
		//Se obtiene el request de tipo http.
		HttpServletRequest req=(HttpServletRequest) request;
		
		//se obtiene la persona de sesion.
		Persona user=(Persona) SesionFactory.getValor("persona");
		//esta en sesion, sigue la peticion.
		if(user!=null){
			
			if(user instanceof Funcionario)
			chain.doFilter(request, response);
			
		}else{
			//se envia a inicio.
			req.getRequestDispatcher("/pages/inicio.jsf").forward(request, response);
		}
		
		
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
