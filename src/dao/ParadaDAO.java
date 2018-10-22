package dao;

import model.Parada;

public interface ParadaDAO {
	Parada createParada(String usuario, String password, String email, String telefono);
	Parada findParada(String usuario);
}
