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
	
	public abstract CocheDAO getCocheDAO();

	public abstract UsuarioDAO getUsuarioDAO();
	public abstract ViajeDAO getViajeDAO();
	public abstract ParadaDAO getParadaDAO();
	// public abstract UsuarioDAO getCocheDAO();
}
