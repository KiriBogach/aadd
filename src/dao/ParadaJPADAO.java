package dao;

import javax.persistence.EntityManager;

import model.Parada;

public class ParadaJPADAO implements ParadaDAO {

	private EntityManager em;

	public ParadaJPADAO(EntityManager em) {
		this.em = em;
	}

	@Override
	protected void finalize() throws Throwable {
		this.em.close();
		super.finalize();
	}

	@Override
	public Parada createParada(String usuario, String password, String email, String telefono) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Parada findParada(String usuario) {
		// TODO Auto-generated method stub
		return null;
	}

}
