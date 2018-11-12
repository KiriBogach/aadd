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
<f:view>
	<f:loadBundle basename="resources.messages" var="msg" />
	<center>
		<h3>
			<h:outputText value="#{msg.falloLoginTitulo}" />
		</h3>
	</center>
	<hr>
	<h:form id="LoginForm">
		<b><h:outputText value="#{msg.falloLogin}" /></b>
		<br>
		<br>
	</h:form>
</f:view>
	</div>
	<div id="footer">
		<%@ include file="divFooter.jsp"%>
	</div>
</body>
</html>