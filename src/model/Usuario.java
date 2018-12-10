package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name = "getAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String usuario;
	private String password;
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	private String profesion;
	private String email;
	private String nombre;
	private String apellidos;

	@OneToOne(mappedBy = "usuario")
	private Coche coche;

	@OneToMany(mappedBy = "usuario")
	private Collection<Reserva> reservas;

	
	private Collection<Valoracion> valoraciones;

	public Usuario() {

	}

	public Usuario(String usuario, String password, Date fechaNacimiento, String profesion, String email, String nombre,
			String apellidos) {
		this.usuario = usuario;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.profesion = profesion;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.reservas = new LinkedList<>();
		this.valoraciones = new LinkedList<>();
	}

	public String getUsuario() {
		return usuario;
	}
	
	public int getEdad() {
		Calendar now = Calendar.getInstance();
		Calendar nacimiento = Calendar.getInstance();
		nacimiento.setTime(this.fechaNacimiento);
		return now.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	public Collection<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(Collection<Reserva> reservas) {
		this.reservas = reservas;
	}

	public void addReserva(Reserva reserva) {
		this.reservas.add(reserva);
	}

	public boolean isUsuario(String usuario) {
		return this.getUsuario().equals(usuario);
	}

	public void addValoracion(Valoracion valoracion) {

		this.valoraciones.add(valoracion);
	}

	public boolean tieneCoche() {
		return coche != null;
	}

	public void registrarViaje(Viaje viaje) {
		coche.addViaje(viaje);
	}

	public Valoracion createValoracion(String comentario, int puntuacion, Usuario receptor, Reserva reserva) {
		Valoracion valoracion = null;
		try {
			valoracion = new Valoracion(comentario, puntuacion);
		} catch (IllegalArgumentException e) {
			return null;
		}

		valoracion.setEmisor(this);
		valoracion.setReceptor(receptor);
		valoracion.setReserva(reserva);

		receptor.addValoracion(valoracion);
		this.addValoracion(valoracion);
		reserva.addValoracion(valoracion);

		return valoracion;

	}

}
