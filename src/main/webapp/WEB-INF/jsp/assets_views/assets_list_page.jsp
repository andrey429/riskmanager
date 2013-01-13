<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.assetListingPageTitle"></spring2:message></title>

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

<c:url var="addUrl" value="/riskmanager/assets/add"/>


<c:choose>
    <c:when test="${not empty assets}">

        <h3 class="row">
            <h2 class="cell"><spring2:message code="label.assetName"/></h2>

            <h2 class="cell"><spring2:message code="label.assetDescription"/></h2>

            <h2 class="cell"><spring2:message code="label.assetOwner"/></h2>

            <h2 class="cell"><spring2:message code="label.assetRequirements"/></h2>

            <h2 class="cell"><spring2:message code="label.assetBusinessProcessType"/></h2>

            <h2 class="cell"><spring2:message code="label.assetLocation"/></h2>
        </h3>
        <c:forEach items="${assets}" var="asset">
            <c:url var="editUrl" value="/riskmanager/assets/edit?id=${asset.id}"/>
            <c:url var="deleteUrl" value="/riskmanager/assets/delete?id=${asset.id}"/>
            <h3 class="row">
                <h3 class="cell">
                    <i class="addIcon" onclick="iconHandler('${deleteUrl}')">(-)</i>
                    <a href="${editUrl}">${asset.name}</a></h3>

                <h3 class="cell">${asset.description}</h3>

                <h3 class="cell">${asset.personOwner}</h3>

                <h3 class="cell">
                    <c:if test="${asset.requiresConfidentiality}">C</c:if>
                    &nbsp;
                    <c:if test="${asset.requiresIntegrity}">I</c:if>
                    &nbsp;
                    <c:if test="${asset.requiresAvailability}">A</c:if>
                </h3>

                <h3 class="cell">
                    <c:choose>
                        <c:when test="${asset.businessProcessType == 1}">
                            <spring2:message code="label.assetPaymentBusinessProcess"/>
                        </c:when>
                        <c:when test="${asset.businessProcessType == 2}">
                            <spring2:message code="label.assetInformationBusinessProcess"/>
                        </c:when>
                    </c:choose>
                </h3>

                <h3 class="cell">${asset.assetLocation}</h3>
            </h3>


        </c:forEach>
    </c:when>
    <c:otherwise>
        <div class="accordion">
            <p>
                <spring2:message code="label.assetsNotPresent"/>
            </p>
        </div>

    </c:otherwise>
</c:choose>


</body>
</html>