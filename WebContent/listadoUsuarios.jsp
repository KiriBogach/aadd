<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.um.es/aadd/tlds" prefix="ad"  %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="section">
		<table border="1">
			<tr>
				<td width="20%">
					<p align="center">
						<b>Usuario</b>
				</td>
				<td width="50%">
					<p align="center">
						<b>Nombre</b>
				</td>
				<td width="30%">
					<p align="center">
						<b>Correo</b>
				</td>
			</tr>
			<ad:listaUsuarios usuarios="${sessionScope.usuarios}">
				<tr>
					<td width="20%" align="center"><%=usuario%></td>
					<td width="50%" align="center"><%=nombre%></td>
					<td width="30%" align="center"><%=email%></td>
				</tr>
			</ad:listaUsuarios>
		</table>
	</div>
</body>
</html>