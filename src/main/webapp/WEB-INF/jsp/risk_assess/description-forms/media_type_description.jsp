<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="risk.pagetitle.mediaType"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-style.css" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/sliding-elements.js"></script>

</head>
<body>


<tags:menu></tags:menu>
<c:url var="createAssetTypeURL" value="/riskmanager/risk-assessment/create/asset-type"/>
<c:url var="createMediaTypeURL" value="/riskmanager/risk-assessment/create/media-type"/>
<c:url var="createRiskURL" value="/riskmanager/risk-assessment/create/risk"/>
<c:url var="showRisksURL" value="/riskmanager/risk-assessment/show"/>

<nav>
    <ul>
        <li>
            <a><spring2:message code="risk.main.createMenu"/></a>
            <ul>
                <li>
                    <a href="${createAssetTypeURL}"><spring2:message code="menu.submenu.createAssetType"/></a>
                </li>
                <li>
                    <a href="${createMediaTypeURL}"><spring2:message code="menu.submenu.createMenuType"/></a>
                </li>
                <li>
                    <a href="${createRiskURL}"><spring2:message code="menu.submenu.createRiskDetail"/></a>
                </li>
            </ul>
        </li>
        <li><a href="${showRisksURL}"><spring2:message code="risk.main.showMenu"/></a></li>
    </ul>
</nav>


<div class="accordion">


    <form:form modelAttribute="mediaType"
               id="thisform">

        <h2><spring2:message code="risk.mediaType"/></h2>

        <h3><spring2:message code="risk.mediaType.name"/></h3>

        <p>
            <form:input path="mediaTypeName"/>
        </p>

        <h3><spring2:message code="risk.mediaType.description"/>
        </h3>

        <p>
            <form:textarea path="description"/>
        </p>


        <h2 onclick="document.forms['thisform'].submit()" id="savebutton">
            <spring2:message code="risk.create.submit"/>
        </h2>

    </form:form>
</div>


</body>
</html>