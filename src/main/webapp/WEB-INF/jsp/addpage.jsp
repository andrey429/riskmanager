<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Описание нового сотрудника</title>
</head>
<body>

<h1>Описать сотрудника - участника аудита</h1>

<c:url var="saveUrl" value="/riskmanager/main/persons/add" />
<form:form modelAttribute="personAttribute" method="POST" action="${saveUrl}">
	<table>
        <tr>
            <td><form:label path="lastName">Фамилия</form:label></td>
            <td><form:input path="lastName"/></td>
        </tr>

        <tr>
			<td><form:label path="firstName">Имя:</form:label></td>
			<td><form:input path="firstName"/></td>
		</tr>

        <tr>
            <td><form:label path="secondName">Отчество</form:label></td>
            <td><form:input path="secondName"/></td>
        </tr>

		

	</table>
	
	<input type="submit" value="Save" />
</form:form>

</body>
</html>