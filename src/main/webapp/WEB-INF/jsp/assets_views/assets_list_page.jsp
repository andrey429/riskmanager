<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>



<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.assetListingPageTitle"></spring2:message></title>
</head>
<body>
<h1><spring2:message  code="label.assetListingPageTitle"/></h1>

<c:url var="addUrl" value="/riskmanager/assets/add" />


<c:choose>
    <c:when test="${not empty assets}">
        <table style="border: 1px solid; width: 500px; text-align:center">
            <thead style="background:#fcf">
            <tr>
                <th>Название</th>
                <th>Описание</th>
                <th colspan="3">Требования безопасности</th>
                <th colspan="3">Ущерб от потерь</th>


                <th colspan="3">Опции редактирования</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${assets}" var="asset">
                <c:url var="editUrl" value="/riskmanager/assets/edit?id=${asset.id}" />
                <c:url var="deleteUrl" value="/riskmanager/assets/delete?id=${asset.id}" />
                <tr>
                    <td><c:out value="${asset.name}" /></td>
                    <td><c:out value="${asset.description}" /></td>
                   <!---> <-->
                    <td><c:if test="${asset.requiresConfidentiality}">C</c:if> </td>
                    <td><c:if test="${asset.requiresIntegrity}">I</c:if> </td>
                    <td><c:if test="${asset.requiresAvailability}">A</c:if> </td>
                    <!---> <-->
                    <td><c:out value="${asset.damageIfConfidentialityLost}"></c:out> </td>
                    <td><c:out value="${asset.damageIfIntegrityLost}"></c:out> </td>
                    <td><c:out value="${asset.damageIfAvailabilityLost}"></c:out> </td>



                    <td><a href="${editUrl}">Edit</a></td>
                    <td><a href="${deleteUrl}">Delete</a></td>
                    <td><a href="${addUrl}">Add</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        Активы отсутствуют в области оценки. Вы можете <a href="${addUrl}">добавить описание актива</a>.
    </c:otherwise>
</c:choose>


</body>
</html>