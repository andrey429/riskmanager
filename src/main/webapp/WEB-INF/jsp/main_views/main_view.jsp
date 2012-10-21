<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>


<!--security-->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.appName"/></title>
</head>
<body>
<h1><spring2:message code="label.appName"/></h1>

<c:url var="assetsUrl" value="/riskmanager/assets/"/>
<c:url var="personsUrl" value="/riskmanager/persons/"/>
<c:url var="organisationsUrl" value="/riskmanager/organisations/"/>
<c:url var="queryURL" value="/riskmanager/query"/>
<c:url var="downloadReportURL" value="/riskmanager/report/"/>

<h2><spring2:message code="label.appRiskEvaluationScope"/></h2>
<table style="border: 1px solid; width: 500px; text-align:center">
    <thead style="background:#fcf">
    <tr>

        <th><a href="${assetsUrl}"><spring2:message code="label.appAssetsTabLabel"/> </a></th>


        <!--only admins can add persons or organisations-->
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <th><a href="${personsUrl}"><spring2:message code="label.appPersonsTabLabel"/></a></th>
            <th><a href="${organisationsUrl}"><spring2:message code="label.appOrganisationsTabLabel"/></a></th>
            <th><a href="${queryURL}"><spring2:message code="label.queryPageTitle"/> </a></th>
            <th><a href ="${downloadReportURL}"><spring2:message code="label.appDownloadReport"/> </a></th>
        </sec:authorize>

    </tr>
    </thead>

</table>

<p>
    <spring2:message code="label.youHaveSignedInAsMessage"/> <b>${loggedInUsername}</b> ( ${loggedInFullName} )
</p>
<p>
    <c:url var="logoutURL" value="/riskmanager/auth/logout"/>
    <a href="${logoutURL}"><spring2:message code="label.loginLogoutSubmit"/></a>
</p>


</body>
</html>