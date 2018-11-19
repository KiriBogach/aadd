package beans;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import controller.Controlador;

@ManagedBean(name = "beanRegistrar2")
@SessionScoped
public class BeanRegistrar2 {
	@NotNull
	@Size(min = 4, max = 10)
	private String usuario;
	private String password;
	private String password2;
	private Date fechaNacimiento;
	private String profesion;
	private String email;
	@NotNull
	@Size(min = 6, max = 128)
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

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
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

	public String registro() {
		java.sql.Date fecha = new java.sql.Date(fechaNacimiento.getTime());
		if (password.equals(password2)) {
			if (Controlador.getInstance().registrarUsuario(usuario, password, fecha, profesion, email, nombre,
					apellidos) != null)
				return "faceletsWelcome";
		}
		setNombre(new String());
		setApellidos(new String());
		setProfesion(new String());
		setEmail(new String());
		setUsuario(new String());
		setApellidos(new String());
		setFechaNacimiento(new Date());
		return "faceletsFallo";
	}
}