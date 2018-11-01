package dao;

import model.Viaje;

public interface ViajeDAO {

	Viaje createViaje(int plazas, double precio);

	Viaje findViaje(int id);

	void update();
}
