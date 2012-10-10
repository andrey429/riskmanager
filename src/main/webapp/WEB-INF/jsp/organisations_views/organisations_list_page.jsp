<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.organisationListingPageTitle"/></title>
</head>
<body>
<h1><spring2:message code="label.organisationListingPageTitle"/> </h1>

<c:url var="addUrl" value="/riskmanager/organisations/add" />


<c:choose>
    <c:when test="${not empty organisations}">
        <table style="border: 1px solid; width: 500px; text-align:center">
            <thead style="background:#fcf">
            <tr>
                <th><spring2:message code="label.organisationName"/> </th>
                <th><spring2:message code="label.organisationAddress"/></th>

                <th colspan="3"><spring2:message code="label.editOptionsTitles"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${organisations}" var="organisation">
                <c:url var="editUrl" value="/riskmanager/organisations/edit?id=${organisation.id}" />
                <c:url var="deleteUrl" value="/riskmanager/organisations/delete?id=${organisation.id}" /><!--main/persons-->
                <tr>
                    <td><c:out value="${organisation.organisationName}" /></td>
                    <td><c:out value="${organisation.organisationAddress}" /></td>


                    <td><a href="${editUrl}"><spring2:message code="label.editButton"/></a></td>
                    <td><a href="${deleteUrl}"><spring2:message code="label.deleteButton"/></a></td>
                    <td><a href="${addUrl}"><spring2:message code="label.addButton"/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <spring2:message code="label.organisationsNotPresent"/>
        <a href="${addUrl}"><spring2:message code="label.youMayAddLabel"/> </a>.
    </c:otherwise>
</c:choose>


</body>
</html>