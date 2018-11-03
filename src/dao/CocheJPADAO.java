package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Coche;

public class CocheJPADAO implements CocheDAO {

	private EntityManager em;

	public CocheJPADAO(EntityManager em) {
		this.em = em;
	}

	@Override
	public Coche createCoche(String matricula, String modelo, int year, int confort) {
		Coche coche = null;
		try {
			coche = new Coche(matricula, modelo, confort, year);
		} catch (IllegalArgumentException e) {
			return null;
		}
		
		EntityTransaction tx = this.em.getTransaction();
		tx.begin();
		try {
			this.em.persist(coche);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			return null;
		}
		return coche;
	}

	@Override
	public Coche findCoche(String matricula) {
		return this.em.find(Coche.class, matricula);
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

	@Override
	public void update(Coche coche) {

		coche = this.em.merge(coche);
		this.update();

	}

}
