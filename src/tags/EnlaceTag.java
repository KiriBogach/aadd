package tags;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

// implements DynamicAtributes -> todo en la práctica
public class EnlaceTag extends SimpleTagSupport {
	private String url;
	private String texto;

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		PageContext pageContext = (PageContext) getJspContext();
		JspFragment body = getJspBody();
		
		// Para imprimir una etiqueta dentro de mi etiqueta EnlaceTage
		if (body != null) {
			out.println("<p>");
			body.invoke(null);
			out.println("</p>");
		}
		
		try {
			HttpServletResponse respuesta = (HttpServletResponse) pageContext.getResponse();
			pageContext.getOut().write("<a href=\"" + respuesta.encodeURL(url) + "\">" + texto + "</a>");
		} catch (java.io.IOException e) {
			throw new JspTagException(e.getMessage());
		}
	}
}
