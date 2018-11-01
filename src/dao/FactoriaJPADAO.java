package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoriaJPADAO extends FactoriaDAO {
	public static final String UNIT_PERSISTENCE="aadd"; 
	private EntityManagerFactory emf;
	
	protected FactoriaJPADAO() {
		this.emf = Persistence.createEntityManagerFactory(UNIT_PERSISTENCE);
	}
	
	@Override
	public UsuarioDAO getUsuarioDAO() {
		synchronized (this.emf) {
			return new UsuarioJPADAO(this.emf.createEntityManager());
		}
	}
	
	@Override
	public CocheDAO getCocheDAO() {
		synchronized (this.emf) {
			return new CocheJPADAO(this.emf.createEntityManager());
		}
	}
	
	@Override
	public ViajeDAO getViajeDAO() {
		synchronized (this.emf) {
			return new ViajeJPADAO(this.emf.createEntityManager());
		}
	}

	@Override
	public ParadaDAO getParadaDAO() {
		synchronized (this.emf) {
			return new ParadaJPADAO(this.emf.createEntityManager());
		}
	}

	
	@Override
	protected void finalize() throws Throwable {
		this.emf.close();
		super.finalize();
	}

}
