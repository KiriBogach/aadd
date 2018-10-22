package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Coche;
import model.Usuario;

public class CocheJPADAO implements CocheDAO {

	private EntityManager em;

	public CocheJPADAO(EntityManager em) {
		this.em = em;
	}

	@Override
	public Coche createCoche(Usuario usuario, String matricula, String modelo, int year, int confort) {
		this.em.merge(usuario);
		Coche coche = new Coche(matricula, modelo, confort, year);
		coche.setUsuario(usuario);
		
		EntityTransaction tx = this.em.getTransaction();
		tx.begin();
		try {
			this.em.persist(coche);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			return null;
		}
		return coche;
	}

	@Override
	public Coche findCoche(String matricula) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void finalize() throws Throwable {
		this.em.close();
		super.finalize();
	}

}
