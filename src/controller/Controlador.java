package controller;

import java.util.Date;

import dao.CocheDAO;
import dao.FactoriaDAO;
import dao.ParadaDAO;
import dao.UsuarioDAO;
import dao.ViajeDAO;
import model.Coche;
import model.Parada;
import model.Usuario;
import model.Viaje;

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

	public Usuario findUsuario(String usuario) {
		UsuarioDAO dao = FactoriaDAO.getInstancia().getUsuarioDAO();
		return dao.findUsuario(usuario);
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

	public boolean registrarCoche(Usuario usuario, String matricula, String modelo, int year, int confort) {
		CocheDAO dao = FactoriaDAO.getInstancia().getCocheDAO();
		Coche coche = dao.createCoche(usuario, matricula, modelo, year, confort);
		if (coche == null) {
			return false;
		}
		usuario.setCoche(coche);
		return true;
	}

	public Viaje registrarViaje(int plazas, double precio) {
		ViajeDAO dao = FactoriaDAO.getInstancia().getViajeDAO();
		Viaje viaje = dao.createViaje("", "", "", "");
		return viaje;
	}

	public Parada registrarPradaOrigen(int idViaje, String ciudad, String calle, int CP, Date fecha) {
		ParadaDAO dao = FactoriaDAO.getInstancia().getParadaDAO();
		Parada parada = dao.createParada("", "", "", "");
		return parada;
	}

}
