package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoriaJPADAO extends FactoriaDAO {
	
	private EntityManagerFactory emf;
	
	protected FactoriaJPADAO() {
		this.emf = Persistence.createEntityManagerFactory("aadd");
	}
	
	@Override
	public UsuarioDAO getUsuarioDAO() {
		synchronized (this.emf) {
			return new UsuarioJPADAO(this.emf.createEntityManager());
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		this.emf.close();
		super.finalize();
	}

}
