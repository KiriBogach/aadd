package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Viaje;

public class ViajeJPADAO implements ViajeDAO {

	private EntityManager em;

	public ViajeJPADAO(EntityManager em) {
		this.em = em;
	}

	@Override
	protected void finalize() throws Throwable {
		this.em.close();
		super.finalize();
	}

	@Override
	public Viaje createViaje(int plazas, double precio) {
		Viaje v = new Viaje(plazas, precio);
		EntityTransaction tx = this.em.getTransaction();
		tx.begin();
		try {
			this.em.persist(v);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			return null;
		}
		return v;
	}

	@Override
	public Viaje findViaje(int id) {
		return this.em.find(Viaje.class, id);
	}

	@Override
	public void update(Viaje v) {
		EntityTransaction tx = this.em.getTransaction();
		tx.begin();
		tx.commit();
	}

}
