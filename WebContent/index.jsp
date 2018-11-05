<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Email del usuario</title>
</head>
<body>
	<b>Email del usuario:</b> ${sessionScope.usuario.email}<br/>
	<a href="AccionUsuario.ctrl"></a>
</body>
</html>