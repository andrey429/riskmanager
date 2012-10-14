<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>



<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.assetListingPageTitle"></spring2:message></title>
</head>
<body>
<h1><spring2:message  code="label.assetListingPageTitle"/></h1>

<c:url var="addUrl" value="/riskmanager/assets/add" />


<c:choose>
    <c:when test="${not empty assets}">
        <p>
            <a href="${addUrl}"><spring2:message code="label.addButton"/></a>
        </p>
        <table style="border: 1px solid; width: 500px; text-align:center">
            <thead style="background:#fcf">
            <tr>
                <th><spring2:message code="label.assetName"/></th>
                <th><spring2:message code="label.assetDescription"/></th>
                <th><spring2:message code="label.assetOwner"/></th>
                <th colspan="3"><spring2:message code="label.assetRequirements"/></th>

                <th><spring2:message code="label.assetBusinessProcessType"/></th>
                <th><spring2:message code="label.assetLocation"/></th>


                <th colspan="3"><spring2:message code="label.editOptionsTitles"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${assets}" var="asset">
                <c:url var="editUrl" value="/riskmanager/assets/edit?id=${asset.id}" />
                <c:url var="deleteUrl" value="/riskmanager/assets/delete?id=${asset.id}" />
                <tr>
                    <td><c:out value="${asset.name}" /></td>
                    <td><c:out value="${asset.description}" /></td>

                    <td><c:out value="${asset.personOwner}"/></td>

                    <td><c:if test="${asset.requiresConfidentiality}">C</c:if> </td>
                    <td><c:if test="${asset.requiresIntegrity}">I</c:if> </td>
                    <td><c:if test="${asset.requiresAvailability}">A</c:if> </td>



                    <td>
                        <c:choose>
                            <c:when test="${asset.businessProcessType == 1}">
                                <spring2:message code="label.assetPaymentBusinessProcess"/>
                            </c:when>
                            <c:when test="${asset.businessProcessType == 2}">
                                <spring2:message code="label.assetInformationBusinessProcess"/>
                            </c:when>
                        </c:choose>
                    </td>

                    <td>
                        <c:out value="${asset.assetLocation}"/>
                    </td>

                    <td><a href="${editUrl}"><spring2:message code="label.editButton"/></a></td>
                    <td><a href="${deleteUrl}"><spring2:message code="label.deleteButton"/></a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <spring2:message code="label.assetsNotPresent"/>. <a href="${addUrl}">
        <spring2:message code="label.youMayAddLabel"/></a>.
    </c:otherwise>
</c:choose>

<c:url var="mainUrl" value="/riskmanager/ "/>
<p>
    <a href="${mainUrl}"><spring2:message code="label.gotoMainURL"/> </a>
</p>
<p>
    <c:url var="logoutURL" value="/riskmanager/auth/logout"/>
    <a href="${logoutURL}"><spring2:message code="label.loginLogoutSubmit"/></a>
</p>

</body>
</html>