package dao;

import java.util.Date;

import model.Usuario;

public interface UsuarioDAO {

	Usuario createUsuario(String usuario, String password, Date fechaNacimiento, String profesion, String email,
			String nombre, String apellidos);

	Usuario findUsuario(String usuario);

	void update();

	
}
