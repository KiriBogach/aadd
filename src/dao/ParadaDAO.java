package dao;

import java.util.Date;

import model.Parada;

public interface ParadaDAO {
	Parada createParada(String ciudad, String calle, int CP, Date fecha);
	Parada findParada(int id);
}
