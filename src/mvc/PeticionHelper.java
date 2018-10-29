package mvc;

import javax.servlet.http.HttpServletRequest;

public class PeticionHelper {

	private HttpServletRequest peticion;

	public PeticionHelper(HttpServletRequest peticion) {
		this.peticion = peticion;
	}

	public boolean errorNavegacion() {
		return false;
	}

	public boolean necesitaValidacion() {
		return false;
	}

	public Accion getAccion() {
		// Analiza la URI para determinar la acción a realizar
		String uri = peticion.getRequestURI();
		// Obtiene la cadena entre la última "/" y ".ctrl"
		int posIni = uri.lastIndexOf("/");
		int posFin = uri.lastIndexOf(".");
		String accionKey = uri.substring(posIni + 1, posFin);
		Class<?> claseAccion;
		Accion acc = null;
		try {
			claseAccion = Class.forName("mvc." + accionKey);
			acc = (Accion) claseAccion.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return acc;
	}

}
