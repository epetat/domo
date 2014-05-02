<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="/WEB-INF/pages/entete.jsp"></jsp:include>

<h1>STATISTIQUE</h1>
<form:form action="statistique" modelAttribute="statistique">
	<br/><br/>
	<label for="dateDebutInput">Date de début : </label>
	<form:input path="dateDebut" id="dateDebutInput" type="datetime-local"/>
	<form:errors path="dateDebut" cssClass="error" />
	<br/><br/>
	<label for="dateFinInput">Date de fin : </label>
	<form:input path="dateFin" id="dateFinInput" type="datetime-local"/>
	<form:errors path="dateFin" cssClass="error" />
	<br/><br/>
	<input type="submit" value="Actualiser">
	<br/><br/>
	<IMG src='charts/temperature?dateDebut=${dateDebut}&dateFin=${dateFin}' name="ImgGraphTemperature">
	
</form:form>

<jsp:include page="/WEB-INF/pages/basDePage.jsp"></jsp:include>
