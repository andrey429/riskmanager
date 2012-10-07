<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Организации области аудита</title>
</head>
<body>
<h1>Организации области аудита</h1>

<c:url var="addUrl" value="/riskmanager/organisations/add" />


<c:choose>
    <c:when test="${not empty organisations}">
        <table style="border: 1px solid; width: 500px; text-align:center">
            <thead style="background:#fcf">
            <tr>
                <th>Название</th>
                <th>Адрес</th>

                <th colspan="3">Опции редактирования</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${organisations}" var="organisation">
                <c:url var="editUrl" value="/riskmanager/organisations/edit?id=${organisation.id}" />
                <c:url var="deleteUrl" value="/riskmanager/organisations/delete?id=${organisation.id}" /><!--main/persons-->
                <tr>
                    <td><c:out value="${organisation.organisationName}" /></td>
                    <td><c:out value="${organisation.organisationAddress}" /></td>


                    <td><a href="${editUrl}">Edit</a></td>
                    <td><a href="${deleteUrl}">Delete</a></td>
                    <td><a href="${addUrl}">Add</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        На данный момент в области аудита нет ни одной организации. Вы можете <a href="${addUrl}">добавить описание организации</a>.
    </c:otherwise>
</c:choose>


</body>
</html>