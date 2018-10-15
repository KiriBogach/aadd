package dao;

public abstract class FactoriaDAO {

	private static FactoriaDAO unicaInstancia = null;

	protected FactoriaDAO() {
	}

	public static FactoriaDAO getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new FactoriaJPADAO();
		}
		return unicaInstancia;
	}

	public abstract UsuarioDAO getUsuarioDAO();
	// public abstract UsuarioDAO getCocheDAO();
}
