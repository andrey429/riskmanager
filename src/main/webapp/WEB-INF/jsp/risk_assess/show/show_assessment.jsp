<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.site.com/myTlds" prefix="func" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title><spring2:message code="risk.pagetitle.risk"/></title>
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

<%--todo MAKE FOR EACH NAV ELEMENT?--%>


<c:choose>
    <c:when test="${(not empty storedScopeObjects) and (not empty storedRiskDetails)}">

        <c:set var="riskMatrix" value="${riskValueFactory.riskValueMatrix}"/>

        <h3 class="row"><%--threat and threat source--%>
            <h2 class="cell"><b>Объект оценки<%--<spring2:message code="risk.assetType"/>--%></b></h2>


            <h2 class="cell"><spring2:message code="risk.riskDetail.targetProperty"/></h2>

            <h2 class="cell"><spring2:message code="risk.riskDetail.threat"/></h2>

            <h2 class="cell"><spring2:message code="risk.riskDetail.threat.implementation"/></h2>

            <h2 class="cell"><spring2:message code="risk.riskDetail.svr"/></h2>

            <h2 class="cell"><spring2:message code="risk.riskDetail.stp"/></h2>

            <h2 class="cell"><spring2:message code="values.risk"/></h2>

            <h2 class="cell"><spring2:message code="risk.riskDetail.plannedMeasures"/></h2>
        </h3>
        <c:forEach items="${storedScopeObjects}" var="scopeObj">
            <c:forEach items="${scopeObj.riskDetails}" var="riskDetail">
                <c:set var="deleteRiskURL"
                       value="/riskmanager/riskmanager/risk-assessment/delete/risk?assetTypeID=${scopeObj.assetType.id}&mediaTypeID=${scopeObj.mediaType.id}&riskID=${riskDetail.id}"/>
                <c:set var="editAssetTypeURL"
                       value="/riskmanager/riskmanager/risk-assessment/edit/asset-type?assetTypeID=${scopeObj.assetType.id}"/>
                <c:set var="editMediaTypeURL"
                       value="/riskmanager/riskmanager/risk-assessment/edit/media-type?mediaTypeID=${scopeObj.mediaType.id}"/>
                <c:set var="editRiskURL"
                       value="/riskmanager/riskmanager/risk-assessment/edit/risk?riskID=${riskDetail.id}"/>


                <h3 class="row">
                    <h3 class="cell">
                        <i class="addIcon"
                           onclick="iconHandler(/*location.href='*/'${deleteRiskURL}')">(-)</i>
                        Актив:<br>
                        <a
                                href="${editAssetTypeURL}">${scopeObj.assetType}</a>
                        <br>Объект среды:<br>
                        <a href="${editMediaTypeURL}">${scopeObj.mediaType}</a>
                    </h3>

                        <%--<h3 class="cell"></h3>--%>

                    <h3 class="cell">
                        <a href="${editRiskURL}"><i>Редактировать риск</i></a><br>
                        <c:choose>
                            <c:when test="${riskDetail.targetProperty == 0}">
                                <spring2:message code="label.confidentiality"/>
                            </c:when>
                            <c:when test="${riskDetail.targetProperty == 1}">
                                <spring2:message code="label.integrity"/>
                            </c:when>
                            <c:when test="${riskDetail.targetProperty == 2}">
                                <spring2:message code="label.availability"/>
                            </c:when>
                        </c:choose>

                    </h3>

                    <h3 class="cell">
                            <%--${risk.threatSource}--%>
                        <spring2:message code="threat${riskDetail.threatSource}"/>
                    </h3>

                    <h3 class="cell">
                        <c:choose>
                            <c:when test="${riskDetail.threatImplementation == ''}">
                                <spring2:message code="risk.error.null"/>
                            </c:when>
                            <c:otherwise>
                                ${riskDetail.threatImplementation}
                            </c:otherwise>
                        </c:choose>
                    </h3>

                    <h3 class="cell">
                        <spring2:message code="values.svr.${riskDetail.svrValue}"/>
                    </h3>

                    <h3 class="cell">
                        <spring2:message code="values.stp.${riskDetail.stpValue}"/>
                    </h3>

                    <h3 class="cell">

                        <c:choose>
                            <c:when test="${riskMatrix[riskDetail.svrValue][riskDetail.stpValue] == 1}">
                                <spring2:message code="values.acceptable"/>
                            </c:when>
                            <c:otherwise>
                                <spring2:message code="values.notAcceptable"/>
                            </c:otherwise>
                        </c:choose>
                    </h3>


                    <h3 class="cell">
                        <c:choose>
                            <c:when test="${riskDetail.plannedMeasures == ''}">
                                <spring2:message code="risk.error.null"/>
                            </c:when>
                            <c:otherwise>
                                ${riskDetail.plannedMeasures}
                            </c:otherwise>
                        </c:choose>
                    </h3>


                </h3>
            </c:forEach>
        </c:forEach>

        <%--</div>--%>
    </c:when>
    <c:otherwise>
        <div class="accordion">
            <h1 onclick="location.href='./'">
                <spring2:message code="risk.error.notEnoughScope"/>
            </h1>
        </div>
    </c:otherwise>

</c:choose>


</body>
</html>