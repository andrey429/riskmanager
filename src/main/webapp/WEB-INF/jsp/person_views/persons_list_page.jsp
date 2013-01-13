<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.personListingPageTitle"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-style.css" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/sliding-elements.js"></script>

    <script type="text/javascript">
        function iconHandler(url) {
            if (confirm('<spring2:message code="risk.confirmDelete"/>')) {
                location.href = url;
            }
        }
    </script>
</head>
<body>

<tags:menu></tags:menu>



<c:choose>
    <c:when test="${not empty persons}">
        <h3 class="row">
            <h2 class="cell"><spring2:message code="label.personLastName"/></h2>
            <h2 class="cell"><spring2:message code="label.personFirstName"/></h2>
            <h2 class="cell"><spring2:message code="label.personSecondName"/></h2>
            <h2 class="cell"><spring2:message code="label.personOrganisation"/></h2>
            <h2 class="cell"><spring2:message code="label.personDepartment"/></h2>
            <h2 class="cell"><spring2:message code="label.personJobPosition"/></h2>
        </h3>
        <c:forEach items="${persons}" var="person">
        <c:url var="editUrl" value="/riskmanager/persons/edit?id=${person.id}"/>
        <c:url var="deleteUrl" value="/riskmanager/persons/delete?id=${person.id}"/><!--main/persons-->
           <h3 class="row">
            <h3 class="cell">
                <i class="addIcon" onclick="iconHandler('${deleteUrl}')">(-)</i>
            <a href="${editUrl}">${person.lastName}</a>
            </h3>
            <h3 class="cell">${person.firstName}</h3>
            <h3 class="cell">${person.secondName}</h3>
            <h3 class="cell">${person.organisation}</h3>
            <h3 class="cell">${person.department}</h3>
            <h3 class="cell">${person.jobPosition}</h3>
           </h3>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <div class="accordion">
            <p>
                <spring2:message code="label.personsNotPresent"/>
            </p>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>