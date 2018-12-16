package beans;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controller.Controlador;
import model.Parada;

@ManagedBean(name = "beanRegistrarParada")
@SessionScoped
public class BeanRegistrarParada implements Serializable {

	private static final long serialVersionUID = 1L;

	private BeanRegistrarParada origen;
	private BeanRegistrarParada destino;

	private String ciudad;
	private String calle;
	private String cp;
	private Date fecha;

	@PostConstruct
	public void init() {
		origen = new BeanRegistrarParada();
		destino = new BeanRegistrarParada();
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cP) {
		cp = cP;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BeanRegistrarParada getOrigen() {
		return origen;
	}

	public void setOrigen(BeanRegistrarParada _origen) {
		origen = _origen;
	}

	public BeanRegistrarParada getDestino() {
		return destino;
	}

	public void setDestino(BeanRegistrarParada _destino) {
		destino = _destino;
	}

	public Parada registrarParada(int idViaje, boolean paradaOrigen) {

		Controlador controlador = Controlador.getInstance();
		int CP;
		Parada parada;
		try {
			CP = Integer.parseInt(cp);
		} catch (NumberFormatException e) {
			return null;
		}
		if (paradaOrigen) {
			parada = controlador.registrarParadaOrigen(idViaje, ciudad, calle, CP, fecha);
		} else {
			parada = controlador.registrarParadaDestino(idViaje, ciudad, calle, CP, fecha);
		}

		return parada;

	}

	public void limpiarCampos() {
		ciudad = "";
		calle = "";
		cp = "";
		fecha = null;

	}

}
