<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.personListingPageTitle"/></title>
</head>
<body>
<h1><spring2:message code="label.personListingPageTitle"/></h1>

<c:url var="addUrl" value="/riskmanager/persons/add" />


<c:choose>
    <c:when test="${not empty persons}">
        <p>
            <a href="${addUrl}"><spring2:message code="label.addButton"/></a>
        </p>
        <table style="border: 1px solid; width: 500px; text-align:center">
            <thead style="background:#fcf">
            <tr>
                <th><spring2:message code="label.personLastName"/></th>
                <th><spring2:message code="label.personFirstName"/></th>
                <th><spring2:message code="label.personSecondName"/></th>
                <th><spring2:message code="label.personOrganisation"/></th>
                <th><spring2:message code="label.personDepartment"/></th>
                <th><spring2:message code="label.personJobPosition"/></th>

                <th colspan="3"><spring2:message code="label.editOptionsTitles"/></th>
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
                    <td><c:out value="${person.organisation}"/></td>
                    <td><c:out value="${person.department}" /></td>
                    <td><c:out value="${person.jobPosition}"/></td>

                    <td><a href="${editUrl}"><spring2:message code="label.editButton"/></a></td>
                    <td><a href="${deleteUrl}"><spring2:message code="label.deleteButton"/></a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <spring2:message code="label.personsNotPresent"/>
        <a href="${addUrl}"><spring2:message code="label.youMayAddLabel"/> </a>.

    </c:otherwise>
</c:choose>


<c:url var="mainUrl" value="/riskmanager/"/>
<p>
    <a href="${mainUrl}"><spring2:message code="label.gotoMainURL"/> </a>
</p>
<p>
    <c:url var="logoutURL" value="/riskmanager/auth/logout"/>
    <a href="${logoutURL}"><spring2:message code="label.loginLogoutSubmit"/></a>
</p>

</body>
</html>