package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import controller.Controlador;
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
	public void update() {
		EntityTransaction tx = this.em.getTransaction();
		tx.begin();
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Viaje> getAllViajes() {
		Query query = this.em.createQuery("SELECT v FROM Viaje v");
		return query.getResultList();
	}

	@Override
	public Collection<Viaje> getAllViajesBy(boolean pendientes, boolean realizados, boolean propios, boolean ordenFecha,
			boolean ordenCiudad) {
		String queryString = "SELECT v FROM Viaje v WHERE ";
		LinkedList<String> filtros = new LinkedList<>();
		if (pendientes) {
			filtros.add("v.origen.fecha < :fecha ");
		}
		if (realizados) {
			filtros.add("v.destino.fecha < :fecha ");
		}
		if (propios) {
			filtros.add("v.coche.usuario.usuario = :usuario ");
		}
		// ....
		
		for (int i = 0; i < filtros.size() - 1; i++) {
			queryString += filtros.get(i) + " OR ";
		}
		
		if (filtros.size() != 0) {
			queryString += filtros.getLast();
		}
		
		Query query = this.em.createQuery(queryString);

		
		Date fecha = Controlador.fromStringToDate(Controlador.fechaSistema);
		
		if (pendientes || realizados) {
			query.setParameter("fecha", fecha, TemporalType.DATE);
		}
		if (propios) {
			query.setParameter("usuario", Controlador.getInstance().getUsuarioLogeado().getUsuario());
		}
		return query.getResultList();
	}

}
