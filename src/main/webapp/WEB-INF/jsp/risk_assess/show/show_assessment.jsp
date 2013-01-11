<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.site.com/myTlds" prefix="func" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addpage.css" media="all"/>
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/page.css" media="all"/>--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/perfect_login.css" media="all">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title><spring2:message code="risk.pagetitle.risk"/></title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>

    <script type="text/javascript">
        function iconHandler(url) {
            if (confirm('<spring2:message code="risk.confirmDelete"/>')) {
                location.href = url;
            }
        }



    </script>
</head>


<body>

<c:choose>
    <c:when test="${(not empty storedScopeObjects) and (not empty storedRiskDetails)}">

        <c:set var="riskMatrix" value="${riskValueFactory.riskValueMatrix}"/>
        <div class="divtable">
            <h3 class="row"><%--threat and threat source--%>
                <h2 class="cell"><b><spring2:message code="risk.assetType"/></b></h2>

                <h2 class="cell"><spring2:message code="risk.mediaType"/></h2>

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
                    <h3 class="row">
                        <h3 class="cell">
                            <c:set var="deleteRiskURL"
                                   value="/riskmanager/riskmanager/risk-assessment/delete/risk?assetTypeID=${scopeObj.assetType.id}&mediaTypeID=${scopeObj.mediaType.id}&riskID=${riskDetail.id}"/>
                            <i class="addIcon"
                               onclick="iconHandler(location.href='${deleteRiskURL}')">(-)</i>${scopeObj.assetType}
                        </h3>

                        <h3 class="cell">${scopeObj.mediaType}</h3>

                        <h3 class="cell">
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

        </div>
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