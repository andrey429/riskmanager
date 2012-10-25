<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/listing_table.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/page.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addpage.css" media="all"/>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.assetListingPageTitle"></spring2:message></title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".accordion h3:first").addClass("active");
            $(".accordion p:not(:first)").hide();
            $(".accordion h3").click(function () {
                $(this).next("p").slideToggle("slow")
                        .siblings("p:visible").slideUp("slow");
                $(this).toggleClass("active");
                $(this).siblings("h3").removeClass("active");
            });

        });
    </script>

</head>
<body>


<spring2:message code="label.optionMenuValueNotSelected" var="optionMenuValueNotSelected"/>
<spring2:message code="label.assetPaymentBusinessProcess" var="paymentProcessLabel"/>
<spring2:message code="label.assetInformationBusinessProcess" var="informationalProcessLabel"/>
<spring2:message code="label.confidentiality" var="confidentialityLabel"/>
<spring2:message code="label.integrity" var="integrityLabel"/>
<spring2:message code="label.availability" var="availabilityLabel"/>
<spring2:message code="label.assetRequirements" var="assetRequirementsLabel"/>
<spring2:message code="label.queryFilter" var="queryFilter"/>



<c:url var="processRequest" value="/riskmanager/query"/>
<c:url var="resetURL" value="/riskmanager/query/reset"/>



<div id="intro">


    <p>
        <c:url var="mainUrl" value="/riskmanager/ "/>
        <a href="${mainUrl}"><spring2:message code="label.gotoMainURL"/> </a>
    </p>

    <p>
        <c:url var="logoutURL" value="/riskmanager/auth/logout"/>
        <a href="${logoutURL}"><spring2:message code="label.loginLogoutSubmit"/></a>
    </p>
</div>

<form:form method="post" action="${processRequest}" modelAttribute="queryAttribute">

    <div class="accordion">
        <h3>
            <spring2:message code="label.queryFilter"/>
        </h3>

        <p>
            <spring2:message code="help.queryBuildQuery"/>
        </p>

        <h3>
                <spring2:message code="label.queryOrganisationFilter"/></td>


            <form:select path="organisation">
                <form:option value="${assetQuery.organisation.id}">
                    <c:choose>
                        <c:when test="${assetQuery.organisation != null}">
                            ${assetQuery.organisation.organisationName}
                        </c:when>
                        <c:otherwise>
                            <spring2:message code="label.optionMenuValueNotSelected"/>
                        </c:otherwise>
                    </c:choose>
                </form:option>
                <c:forEach items="${existingOrganisations}" var="existingOrganisation">
                    <form:option value="${existingOrganisation.id}"
                                 label="${existingOrganisation.organisationName}"/>
                </c:forEach>
            </form:select>
        </h3>

        <p>
            <spring2:message code="help.queryByOrganisation"/>
        </p>

        <h3>
            <spring2:message code="label.queryPersonFilter"/>

            <form:select path="person">
                <form:option value="${assetQuery.person.id}">
                    <c:choose>
                        <c:when test="${assetQuery.person != null}">
                            ${assetQuery.person}
                        </c:when>
                        <c:otherwise>
                            <spring2:message code="label.optionMenuValueNotSelected"/>
                        </c:otherwise>
                    </c:choose>
                </form:option>
                <c:forEach items="${existingPersons}" var="existingPerson">
                    <form:option value="${existingPerson.id}"
                                 label="${existingPerson}"/>
                </c:forEach>
            </form:select>
        </h3>

        <p>
            <spring2:message code="help.queryByPerson"/>
        </p>

        <h3>
            <spring2:message code="label.queryBusinessProcessFilter"/>

            <form:select path="businessProcessType">
                <form:option value="${null}" label="${optionMenuValueNotSelected}"/>
                <form:option value="${1}" label="${paymentProcessLabel}"/>
                <form:option value="${2}" label="${informationalProcessLabel}"/>
            </form:select>
        </h3>

        <p>
            <spring2:message code="help.queryByProcess"/>
        </p>

        <h3><form:checkbox path="requiresConfidentiality" label="${confidentialityLabel}"/>

        </h3>

        <p><spring2:message code="help.queryConfidential"/></p>

        <h3>
            <form:checkbox path="requiresIntegrity" label="${integrityLabel}"/>
        </h3>

        <p><spring2:message code="help.queryIntegrity"/></p>

        <h3>
            <form:checkbox path="requiresAvailability" label="${availabilityLabel}"/>
        </h3>

        <p><spring2:message code="help.queryAvailability"/></p>


        <h3>
            <spring2:message code="label.queryFilter"/><br><br>
            <input type="submit" value="${queryFilter}"/><br><br>
            <a href="${resetURL}"><spring2:message code="label.queryReset"/> </a>
        </h3>

        <p>
            <spring2:message code="help.querySubmitReset"/>
        </p>
    </div>

</form:form>

<%--query results--%>

<br>



<table style="border: 1px solid; width: 500px; text-align:center">
    <caption>
        <br>
        <spring2:message code="label.queryResults"/>
    </caption>

    <thead style="background:#fcf">
    <tr>
        <th><spring2:message code="label.assetName"/></th>
        <th><spring2:message code="label.assetDescription"/></th>
        <th><spring2:message code="label.assetOwner"/></th>
        <th colspan="3"><spring2:message code="label.assetRequirements"/></th>

        <th><spring2:message code="label.assetBusinessProcessType"/></th>
        <th><spring2:message code="label.assetLocation"/></th>


    </tr>
    </thead>
    <tbody>
    <c:forEach items="${queriedAssetsList}" var="asset">

        <tr>
            <td><c:out value="${asset.name}"/></td>
            <td><c:out value="${asset.description}"/></td>

            <td><c:out value="${asset.personOwner}"/></td>

            <td><c:if test="${asset.requiresConfidentiality}">C</c:if></td>
            <td><c:if test="${asset.requiresIntegrity}">I</c:if></td>
            <td><c:if test="${asset.requiresAvailability}">A</c:if></td>


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
        </tr>


    </c:forEach>
    </tbody>
</table>





</body>
</html>