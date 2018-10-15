package dao;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Direccion;
import model.EstadoReserva;
import model.Parada;
import model.Reserva;
import model.Usuario;
import model.Viaje;

public class UsuarioJPADAO implements UsuarioDAO {

	private EntityManager em;
	
	public UsuarioJPADAO(EntityManager em) {
		this.em = em;
	}
	
	public Usuario findUsuario(String usuario) {
		return this.em.find(Usuario.class, usuario);
	}

	public Usuario createUsuario(String usuario, String password, String email, String telefono) {
		Usuario u = new Usuario();
		u.setUsuario(usuario);
		u.setPassword(password);
		u.setEmail(email);
		u.setTelefono(telefono);
		
		EntityTransaction tx = this.em.getTransaction();
		tx.begin();
		try {
			this.em.persist(u);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			return null;
		} /*finally {
			this.em.close();
		}*/
		return u;
	}
	
	public void addViaje() {
		Viaje v = new Viaje();
		v.setPlazas(2);
		v.setPrecio(200);
		ArrayList<String> notas = new ArrayList<>();
		notas.add("Buen olor");
		notas.add("Gran espacio");
		v.setNotas(notas);
		
		EntityTransaction tx = this.em.getTransaction();
		tx.begin();
		try {
			this.em.persist(v);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} 
	}
	
	public void addParada() {
		Direccion d = new Direccion();
		d.setCalle("Calle Santa Otilia");
		d.setCP(03300);
		
		Parada p = new Parada();
		p.setCiudad("Orihuela");
		p.setFecha(new Date());
		p.setDireccion(d);
		
		EntityTransaction tx = this.em.getTransaction();
		tx.begin();
		try {
			this.em.persist(p);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} 
	}
	
	public void addReserva() {
		Reserva r = new Reserva();
		r.setEstado(EstadoReserva.ACEPTADA);
		r.setComentario("Quiero llevar a mi mascota");
		
		EntityTransaction tx = this.em.getTransaction();
		tx.begin();
		try {
			this.em.persist(r);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} 
	}
	
	@Override
	protected void finalize() throws Throwable {
		this.em.close();
		super.finalize();
	}

}
