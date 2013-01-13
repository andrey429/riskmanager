<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.organisationListingPageTitle"/></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>

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
    <c:when test="${not empty organisations}">


        <h3 class="row">
            <h2 class="cell"><spring2:message code="label.organisationName"/></h2>

            <h2 class="cell"><spring2:message code="label.organisationAddress"/></h2>
        </h3>
        <c:forEach items="${organisations}" var="organisation">
            <c:url var="editUrl" value="/riskmanager/organisations/edit?id=${organisation.id}"/>
            <c:url var="deleteUrl" value="/riskmanager/organisations/delete?id=${organisation.id}"/><!--main/persons-->
            <h3 class="row">
                <h3 class="cell">
                    <i class="addIcon" onclick="iconHandler('${deleteUrl}')">(-)</i>
                    <a href="${editUrl}">${organisation}</a>
                </h3>
                <h3 class="cell">${organisation.organisationAddress}</h3>
            </h3>
        </c:forEach>

    </c:when>
    <c:otherwise>
        <div class="accordion">
            <p>
                <spring2:message code="label.organisationsNotPresent"/>
            </p>
        </div>

    </c:otherwise>
</c:choose>


</body>
</html>