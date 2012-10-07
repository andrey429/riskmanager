<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Сотрудники</title>
</head>
<body>
<h1>Сотрудники</h1>

<c:url var="addUrl" value="/riskmanager/main/persons/add" />
<table style="border: 1px solid; width: 500px; text-align:center">
	<thead style="background:#fcf">
		<tr>
			<th>Фамилия</th>
			<th>Имя</th>
			<th>Отчество</th>
			<th colspan="3"></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${persons}" var="person">
			<c:url var="editUrl" value="/riskmanager/main/persons/edit?id=${person.id}" />
			<c:url var="deleteUrl" value="/riskmanager/main/persons/delete?id=${person.id}" />
		<tr>
            <td><c:out value="${person.lastName}" /></td>
            <td><c:out value="${person.firstName}" /></td>
			<td><c:out value="${person.secondName}" /></td>
			<td><a href="${editUrl}">Edit</a></td>
			<td><a href="${deleteUrl}">Delete</a></td>
			<td><a href="${addUrl}">Add</a></td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<c:if test="${empty persons}">
	На данный момент в группу аудита не добавлены сотрудники. <a href="${addUrl}">Добавить описание сотрудника</a>.
</c:if>

</body>
</html>