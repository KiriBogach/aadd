package dao;

import model.Usuario;

public interface UsuarioDAO {

	Usuario createUsuario(String usuario, String password, String email, String telefono);
	Usuario findUsuario(String usuario);
	void update(Usuario usuario);
	void addViaje();
	void addParada();
	void addReserva();
}
