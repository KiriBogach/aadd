<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.um.es/aadd/tlds" prefix="ad"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>

<!-- <fmt:setLocale value="${sessionScope.idioma}" scope="application"/> -->
<fmt:setLocale value="en" scope="application" />
<fmt:bundle basename="resources.application">

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html locale="true">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<c:forEach items="${usuarios}" var="usu">
		<c:if test="${usu.usuario == 'usuario2'}">
			<b> SOY EL USUARIO 2</b>
		</c:if>
		<fmt:message key="usuario"></fmt:message>
		<b> ${usu.usuario} - ${fn:length(usu.usuario)} </b>
		<br>
	</c:forEach>

	<br> ${ad:concat("Kiri", "Aramayo")}

	<br>

	<ad:enlace texto="Google" url="http://www.google.es" />
	<br>
	<tag:enlace texto="Twitter" url="http://www.twitter.com" />

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
</fmt:bundle>