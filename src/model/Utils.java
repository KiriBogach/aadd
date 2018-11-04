package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static final String FORMATO_FECHA = "dd/MM/yyyy";
	public static final String FORMATO_FECHA_HORA_MINUTOS = "dd/MM/yyyy hh:mm";
	public static final String FORMATO_FECHA_HORA_MINUTOS_SEGUNDOS = "dd/MM/yyyy hh:mm:ss";

	/* Este método permite obtener a partir de un String una fecha */
	public static Date fromStringToDate(String fecha) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATO_FECHA);
		return fromStringToDate(fecha, dateFormat);
	}

	public static Date fromStringToDateTime(String fecha) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATO_FECHA_HORA_MINUTOS);
		return fromStringToDate(fecha, dateFormat);
	}
	
	public static Date fromStringToDateTime2(String fecha) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATO_FECHA_HORA_MINUTOS_SEGUNDOS);
		return fromStringToDate(fecha, dateFormat);
	}

	private static Date fromStringToDate(String fecha, SimpleDateFormat dateFormat) {
		Date utilDate = null;

		if (!fecha.equals("")) {
			try {
				utilDate = dateFormat.parse(fecha);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
		}

		return utilDate;
	}
}
