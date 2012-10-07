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

<c:url var="addUrl" value="/riskmanager/persons/add" />


<c:choose>
    <c:when test="${not empty persons}">
        <table style="border: 1px solid; width: 500px; text-align:center">
            <thead style="background:#fcf">
            <tr>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Отчество</th>
                <th>Организация</th>
                <th>Отдел</th>
                <th>Должность</th>

                <th colspan="3">Опции редактирования</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${persons}" var="person">
                <c:url var="editUrl" value="/riskmanager/persons/edit?id=${person.id}" />
                <c:url var="deleteUrl" value="/riskmanager/persons/delete?id=${person.id}" /><!--main/persons-->
                <tr>
                    <td><c:out value="${person.lastName}" /></td>
                    <td><c:out value="${person.firstName}" /></td>
                    <td><c:out value="${person.secondName}" /></td>
                    <td><c:out value="${person.organization}"/></td>
                    <td><c:out value="${person.department}" /></td>
                    <td><c:out value="${person.jobPosition}"/></td>

                    <td><a href="${editUrl}">Edit</a></td>
                    <td><a href="${deleteUrl}">Delete</a></td>
                    <td><a href="${addUrl}">Add</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        На данный момент в области аудита отсутствуют сотрудники. Вы можете <a href="${addUrl}">добавить описание сотрудника</a>.
    </c:otherwise>
</c:choose>


</body>
</html>