<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<f:view>
	<f:loadBundle basename="resources.messages" var="msg" />
	<title><h:outputText value="#{msg.login}" /></title>
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

<h3>
	<b>Registrar Parada TAB</b>
</h3>
	</div>
	<div id="footer">
		<%@ include file="divFooter.jsp"%>
	</div>
</body>
</html>