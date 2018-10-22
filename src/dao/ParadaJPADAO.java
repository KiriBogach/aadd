package dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
	public Parada createParada(String ciudad, String calle, int CP, Date fecha) {
		Parada parada = new Parada(ciudad, calle, CP, fecha);
		EntityTransaction tx = this.em.getTransaction();
		tx.begin();
		try {
			this.em.persist(parada);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			return null;
		}
		return parada;
	}

	@Override
	public Parada findParada(int id) {
		return this.em.find(Parada.class, id);
	}

}
