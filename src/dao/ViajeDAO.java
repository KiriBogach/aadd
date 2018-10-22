package dao;

import model.Viaje;

public interface ViajeDAO {

	Viaje createViaje(String usuario, String password, String email, String telefono);
	Viaje findViaje(String usuario);
}
