package dao;

import model.Coche;

public interface CocheDAO {

	Coche createCoche(String matricula, String modelo, int year, int confort);

	Coche findCoche(String matricula);

	void update();

	void update(Coche coche);
}
