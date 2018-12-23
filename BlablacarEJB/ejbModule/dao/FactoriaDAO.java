package dao;

import javax.ejb.Stateless;

@Stateless(name = "Factoria")
public class FactoriaDAO implements FactoriaDAOLocal {
	public static final int JPA = 0;
	protected FactoriaDAO factoria = null;

	public CocheDAO getCocheDAO() {
		return factoria.getCocheDAO();
	}

	public UsuarioDAO getUsuarioDAO() {
		return factoria.getUsuarioDAO();
	}

	public ViajeDAO getViajeDAO() {
		return factoria.getViajeDAO();
	}

	public ParadaDAO getParadaDAO() {
		return factoria.getParadaDAO();
	}

	public ReservaDAO getReservaDAO() {
		return factoria.getReservaDAO();
	}

	@Override
	public void setDAOFactoria(int tipo) {
		switch (tipo) {
			case JPA: {
				factoria = new FactoriaJPADAO();
			}
			break;
			
		}
	}
}
