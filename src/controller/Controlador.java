package controller;

import dao.FactoriaDAO;
import dao.UsuarioDAO;
import model.Usuario;

public class Controlador {

	private static Controlador unicaInstancia = null;
	
	private Controlador() {
	}
	
	public static Controlador getInstance() {
		if (unicaInstancia == null) {
			unicaInstancia = new Controlador();
		}
		return unicaInstancia;
	}
	
	public boolean findUsuario(String usuario) {
		UsuarioDAO dao = FactoriaDAO.getInstancia().getUsuarioDAO();
		return dao.findUsuario(usuario) != null;
	}
	
	public Usuario createUsuario(String usuario, String password, String email, String telefono) {
		UsuarioDAO dao = FactoriaDAO.getInstancia().getUsuarioDAO();
		return dao.createUsuario(usuario, password, email, telefono);
	}
	
	public boolean loginUsuario(String usuario, String password) {
		UsuarioDAO dao = FactoriaDAO.getInstancia().getUsuarioDAO();
		Usuario u = dao.findUsuario(usuario);
		if (u != null) {
			return u.getPassword().equals(password);
		}
		return false;
	}
	
	public void test() {
		UsuarioDAO dao = FactoriaDAO.getInstancia().getUsuarioDAO();
		dao.addParada();
		dao.addReserva();
		dao.addViaje();
	}
	
}
