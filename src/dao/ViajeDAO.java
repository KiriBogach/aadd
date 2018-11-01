package dao;

import java.util.Collection;

import model.Viaje;

public interface ViajeDAO {

	Viaje createViaje(int plazas, double precio);

	Viaje findViaje(int id);

	Collection<Viaje> getAllViajes();

	Collection<Viaje> getAllViajesBy(boolean pendientes, boolean realizados, boolean propios, boolean ordenFecha,
			boolean ordenCiudad);

	void update();
}
