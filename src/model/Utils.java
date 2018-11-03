package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static final String FORMATO_FECHA = "dd/MM/yyyy";

	/* Este método permite obtener a partir de un String una fecha */
	public static java.sql.Date fromStringToSQLDate(String fecha) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATO_FECHA);
		Date utilDate = null;

		if (!fecha.equals("")) {

			try {
				utilDate = dateFormat.parse(fecha);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}

		}

		java.sql.Date sqlDate = null;
		if (utilDate != null)
			sqlDate = new java.sql.Date(utilDate.getTime());

		return sqlDate;
	}
}
