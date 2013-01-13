<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.site.com/myTlds" prefix="func" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.appName"/></title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-style.css" type="text/css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/sliding-elements.js"></script>

</head>
<body>
<tags:menu></tags:menu>

<div>
    <h3>
        Добро пожаловать в систему RiskManager, ${loggedInUsername}
    </h3>

    <p>
        Система RiskManager создана для автоматизации менеджмента информационной безопасности в соответствии
        с требованиями стандарта Банка Росии СТО БР ИББС
    </p>
    <br>

    <p>
        Воспользуйтесь навигационным меню для доступа к соответствующим модулям
    </p>
</div>

<%--
<c:url var="assetsUrl" value="/riskmanager/assets/"/>
<c:url var="personsUrl" value="/riskmanager/persons/"/>
<c:url var="organisationsUrl" value="/riskmanager/organisations/"/>
<c:url var="queryURL" value="/riskmanager/query"/>
<c:url var="downloadReportURL" value="/riskmanager/report/"/>
--%>

<%--
    <table style="border: 1px solid; width: 500px; text-align:center">
        <caption>
            <spring2:message code="label.menu"></spring2:message>
        </caption>
        <thead style="background:#f1edff">
        <th>
            <spring2:message code="label.youHaveSignedInAsMessage"/> <b>${loggedInUsername}</b> ( ${loggedInFullName} )
        </th>
        </thead>
        <tbody>
        <tr>


            <th><a href="${assetsUrl}"><spring2:message code="label.appAssetsTabLabel"/> </a></th>


            <!--only admins can add persons or organisations-->


        </tr>
        <tr>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <th><a href="${personsUrl}"><spring2:message code="label.appPersonsTabLabel"/></a></th>



    </sec:authorize>
    </tr>
    <tr>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <th><a href="${organisationsUrl}"><spring2:message code="label.appOrganisationsTabLabel"/></a></th>
        </sec:authorize>
    </tr>
    <tr>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <th><a href="${queryURL}"><spring2:message code="label.queryPageTitle"/> </a></th>
        </sec:authorize>
    </tr>
    <tr>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <th><a href ="${downloadReportURL}"><spring2:message code="label.appDownloadReport"/> </a></th>
    </sec:authorize>
    </tr>
    <tr>
        <th>
    <c:url var="logoutURL" value="/riskmanager/auth/logout"/>
    <a href="${logoutURL}"><spring2:message code="label.loginLogoutSubmit"/></a>
    </th>
    </tr>

    </tbody>


    </table>


&lt;%&ndash;</div>
    </div>&ndash;%&gt;
--%>

</body>
</html>