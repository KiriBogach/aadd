package servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LogRegistro
 */
@WebFilter("/ServletRegistro")
public class LogRegistro implements Filter {

	public LogRegistro() {
	}

	public void init(FilterConfig config) throws ServletException {
		System.out.println("Nuevo Fichero de Log");
	}

	public void destroy() {
		System.out.println("Cerrando fichero de LOG.");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Intento de Registro del usuario: " + request.getParameter("usuario"));
		// if (!request.getParameter("usuario").equals("admin")) { // no dejará logearse si no eres el admin
		chain.doFilter(request, response);
		// }
		System.out.println("Resultado del registro. Codigo: " + ((HttpServletResponse) response).getStatus());
	}

}
