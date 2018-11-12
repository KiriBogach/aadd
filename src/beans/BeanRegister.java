package beans;

import java.util.Date;

import controller.Controlador;

public class BeanRegister {

	private String usuario;
	private String password;
	private Date fechaNacimiento;
	private String profesion;
	private String email;
	private String nombre;
	private String apellidos;

	public String getUsuario() {
		return usuario;
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

	public String registrarUsuario() {
		if (Controlador.getInstance().registrarUsuario(usuario, password, fechaNacimiento, profesion, email, nombre,
				apellidos) != null) {
			setUsuario("");
			setPassword("");
			setFechaNacimiento(null);
			setProfesion("");
			setEmail("");
			setNombre("");
			setApellidos("");
			return "success";
		}
		return "failure";
	}
}
