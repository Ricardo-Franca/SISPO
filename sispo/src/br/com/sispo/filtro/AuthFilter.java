package br.com.sispo.filtro;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest rq = (HttpServletRequest) request;
		HttpServletResponse rp = (HttpServletResponse) response;
		boolean auth = rq.getSession().getAttribute("user") != null;
		
		if (!auth && !rq.getRequestURL().toString().contains("login.jsf")) {
			System.out.println("User:"+rq.getSession().getAttribute("user"));
			
			rp.sendRedirect(rq.getContextPath() + "/pages/usuario/login.jsf");
			
		} else {

			try {
				chain.doFilter(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
			}

	
		

	public void destroy() {

	}

	public void init(FilterConfig config) throws ServletException {

	}
}
