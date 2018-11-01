package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Reserva;

public class ReservaJPADAO implements ReservaDAO {

	private EntityManager em;

	public ReservaJPADAO(EntityManager em) {
		this.em = em;
	}

	@Override
	protected void finalize() throws Throwable {
		this.em.close();
		super.finalize();
	}

	@Override
	public Reserva createReserva(String comentario) {
		Reserva reserva = new Reserva(comentario);

		EntityTransaction tx = this.em.getTransaction();
		tx.begin();
		try {
			this.em.persist(reserva);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			return null;
		}
		return reserva;
	}
	
	@Override
	public void update(Reserva reserva) {
		reserva = this.em.merge(reserva);
		this.update();
	}

	@Override
	public void update() {
		EntityTransaction tx = this.em.getTransaction();
		tx.begin();
		tx.commit();
	}

	@Override
	public Reserva findReserva(int id) {
		return this.em.find(Reserva.class, id);
	}

}
