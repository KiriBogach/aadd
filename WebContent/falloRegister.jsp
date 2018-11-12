<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<f:view>
	<f:loadBundle basename="resources.messages" var="msg" />
	<title><h:outputText value="#{msg.aplicacion}" /></title>
</f:view>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="header">
		<%@ include file="divHeader.jsp"%>
	</div>
	<div id="nav">
		<%@ include file="divNav.jsp"%>
	</div>
	<div id="section">
		<html locale="true">
<center>
	<h3>Fallo Registrar</h3>
</center>
<hr>
<f:view>
	<h:form id="LoginForm">
		<b> <h:outputText value="No se le permite
registrar los datos: " /></b>
		<h:outputText value="#{beanRegister.usuario}" />
		<br>
		<br>
		<h:commandLink action="register">
			<h:outputText value="Volver" />
		</h:commandLink>
		<br>
		<br>
		<h:commandLink action="login">
			<h:outputText value="Login" />
		</h:commandLink>
	</h:form>
</f:view>
	</div>
	<div id="footer">
		<%@ include file="divFooter.jsp"%>
	</div>
</body>
</html>