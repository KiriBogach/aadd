<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style type="text/css">
.switchlist {
	font-size: medium;
	font-family: Arial, sans-serif;
	height: 150px;
	width: 450px;
}

.switchlistButtons {
	width: 55px;
	display: inline-block;
	margin-top: 50px;
	vertical-align: top;
}

.switchlistButton {
	width: 50px;
	height: 25px;
}
</style>
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
		<f:view>
			<h:form id="reservas">
				<h:dataTable id="viajes" value="#{beanListarViaje.viajes}"
					var="viaje" border="1">
					<h:column>
						<f:facet name="header">
							<h:outputText value="Viaje" />
						</f:facet>
						<h:outputText value="#{viaje.id}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Precio" />
						</f:facet>
						<h:outputText value="#{viaje.precio}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Plazas" />
						</f:facet>
						<h:outputText value="#{viaje.plazas}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Seleccionar" />
						</f:facet>
						<h:commandLink id="Seleccionar" action="selectViaje"
							actionListener="#{beanListarViaje.selectViaje}">
							<h:outputText value="Seleccionar" />
							<f:param id="selectId" name="id" value="#{viaje.id}" />
						</h:commandLink>
					</h:column>
				</h:dataTable>
				<h:outputText
					value="Viaje seleccionado:
#{beanListarViaje.viajeSeleccionado}" />
				<br>
				<br>
				<h:panelGrid columns="3">
					<h:selectManyListbox id="ciudades"
						value="#{beanRegistrarParada.ciudadesSeleccionadas}"
						styleClass="switchlist">
						<f:selectItems value="#{beanRegistrarParada.ciudadesOfrecidas}" />
					</h:selectManyListbox>
					<h:panelGroup id="buttonGroup" styleClass="switchlistButtons">
						<h:commandButton binding="#{beanRegistrarParada.puedeSeleccionar}"
							value="->" actionListener="#{beanRegistrarParada.moveDtoP}"
							styleClass="switchlistButton" />
						<h:commandButton actionListener="#{beanRegistrarParada.movePtoD}"
							value="<-" styleClass="switchlistButton" />
					</h:panelGroup>
					<h:selectManyListbox id="paradaOrigen"
						value="#{beanRegistrarParada.paradasSeleccionadas}"
						styleClass="switchlist">
						<f:selectItems value="#{beanRegistrarParada.paradasOfrecidas}" />
					</h:selectManyListbox>
				</h:panelGrid>
				<h:outputText value="¿Puede seleccionar?:" />
				<h:selectBooleanCheckbox
					value="#{beanRegistrarParada.estadoAccionSeleccionar}"
					valueChangeListener="#{beanRegistrarParada.puedeAsignarParada}" />
				<h:commandButton value="recargar" type="submit" />
				<br>
				<br>
				<h:outputLink value="index.jsp">
					<h:graphicImage
						value="https://www.um.es/informatica/images/fium-logo-negativo-web.png"
						title="Bienvenido" height="30" />
				</h:outputLink>
				<h:messages />
			</h:form>
		</f:view>
	</div>
	<div id="footer">
		<%@ include file="divFooter.jsp"%>
	</div>
</body>
</html>