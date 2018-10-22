package dao;

import javax.persistence.EntityManager;
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
	public Viaje createViaje(String usuario, String password, String email, String telefono) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Viaje findViaje(String usuario) {
		// TODO Auto-generated method stub
		return null;
	}

}
