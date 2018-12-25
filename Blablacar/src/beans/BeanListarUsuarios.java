package beans;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import model.Usuario;

@ManagedBean(name = "beanListarUsuarios")
@RequestScoped
public class BeanListarUsuarios {
	private Collection<Usuario> usuarios;

	@ManagedProperty(value = "#{beanController}")
	private BeanController beanController;

	public BeanController getBeanController() {
		return beanController;
	}

	public void setBeanController(BeanController beanController) {
		this.beanController = beanController;
	}

	public Collection<Usuario> getUsuarios() {
		usuarios = new LinkedList<Usuario>();
		Collection<Usuario> todosUsuarios = beanController.getControlador().getAllUsuarios();
		for (Usuario usuario : todosUsuarios) {
			usuarios.add(usuario);
		}
		return usuarios;
	}

	public BeanListarUsuarios() {
		usuarios = new LinkedList<Usuario>();
	}

	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}