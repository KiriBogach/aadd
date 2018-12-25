package dao;

import javax.ejb.Local;

@Local
public interface FactoriaDAOLocal {

	public CocheDAO getCocheDAO();

	public UsuarioDAO getUsuarioDAO();

	public ViajeDAO getViajeDAO();

	public ParadaDAO getParadaDAO();

	public ReservaDAO getReservaDAO();

	public void setDAOFactoria(int tipo);
}
