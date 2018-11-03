package dao;

import model.Reserva;

public interface ReservaDAO {
	Reserva createReserva(String comentario);

	Reserva findReserva(int id);

	void update();

	void update(Reserva reserva);
}
