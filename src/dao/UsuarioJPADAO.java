package dao;


import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


import model.Usuario;


public class UsuarioJPADAO implements UsuarioDAO {

	private EntityManager em;

	public UsuarioJPADAO(EntityManager em) {
		this.em = em;
	}

	public Usuario findUsuario(String usuario) {
		return this.em.find(Usuario.class, usuario);
	}

	public Usuario createUsuario(String usuario, String password, Date fechaNacimiento, String profesion, String email,
			String nombre, String apellidos) {
		Usuario u = new Usuario(usuario, password, fechaNacimiento, profesion, email, nombre, apellidos);

		EntityTransaction tx = this.em.getTransaction();
		tx.begin();
		try {
			this.em.persist(u);
			tx.commit();
		} catch (Exception e) {
			if(tx.isActive())
			tx.rollback();
			return null;
		}
		return u;
	}


	@Override
	public void update() {
		EntityTransaction tx = this.em.getTransaction();
		tx.begin();
		tx.commit();
	}

	@Override
	protected void finalize() throws Throwable {
		this.em.close();
		super.finalize();
	}

}
