package dao;

import model.Coche;
import model.Usuario;
public interface CocheDAO {

	Coche createCoche(Usuario usuario, String matricula, String modelo, int year, int confort);
	Coche findCoche(String matricula);
	//void addViaje();
	//void addParada();
	//void addReserva();
}
