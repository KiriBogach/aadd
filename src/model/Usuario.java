package model;

import java.io.Serializable;


public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String password;
	private String email;
	private String telefono;
	private boolean administrador;
	
	public Usuario() {
		administrador = false;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String clave) {
		this.password = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
}
