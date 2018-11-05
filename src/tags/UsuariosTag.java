package tags;

import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import model.Usuario;

public class UsuariosTag extends BodyTagSupport {
	
	private static final long serialVersionUID = 1L;
	private Collection<Usuario> usuarios;
	private Iterator<Usuario> it;

	// Atributo de la etiqueta
	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	// Utilizamos este método para preparar la primera iteración.
	// Es preferible al doInitBody ya que podemos
	// evitar la primera iteración
	public int doStartTag() throws JspTagException {
		it = usuarios.iterator();
		Usuario c = (Usuario) it.next();
		if (c == null)
			return SKIP_BODY;
		else {
			pageContext.setAttribute("usuario", c.getUsuario());
			pageContext.setAttribute("nombre", c.getNombre());
			pageContext.setAttribute("email", c.getEmail());
			return EVAL_BODY_AGAIN;
		}
	}

	public int doAfterBody() throws JspTagException {
		BodyContent bodyContent = getBodyContent();
		if (bodyContent != null) {
			try {
				// Obtiene la evalución del cuerpo y
				// lo escribe por la salida
				bodyContent.getEnclosingWriter().print(bodyContent.getString());
				// Vacía el cuerpo para la siguiente iteración
				bodyContent.clearBody();
			} catch (Exception e) {
				throw new JspTagException(e.getMessage());
			}
		}
		// Comprobamos si seguimos iterando
		if (it.hasNext()) {
			Usuario c = (Usuario) it.next();
			pageContext.setAttribute("usuario", c.getUsuario());
			pageContext.setAttribute("nombre", c.getNombre());
			pageContext.setAttribute("email", c.getEmail());
			return EVAL_BODY_AGAIN;
		} else
			return SKIP_BODY;
	}
}
