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
</head>
<body>

<h1><spring2:message code="label.assetListingPageTitle"/></h1>


<spring2:message code="label.optionMenuValueNotSelected" var="optionMenuValueNotSelected"/>
<spring2:message code="label.assetPaymentBusinessProcess" var="paymentProcessLabel"/>
<spring2:message code="label.assetInformationBusinessProcess" var="informationalProcessLabel"/>
<spring2:message code="label.confidentiality" var="confidentialityLabel"/>
<spring2:message code="label.integrity" var="integrityLabel"/>
<spring2:message code="label.availability" var="availabilityLabel"/>
<spring2:message code="label.assetRequirements" var="assetRequirementsLabel"/>

<%--<c:url var="makeQueryURL">
    /riskmanager/assets/query?organisation=${queryAttribute.organisation}&
    requiresConfidentiality=${queryAttribute.requiresConfidentiality}&
    requiresIntegrity=${queryAttribute.requiresIntegrity}&
    requiresAvailability=${queryAttribute.requiresAvailability}&
    businessProcessType=${queryAttribute.businessProcessType}&
    person=${queryAttribute.person}
</c:url>--%>

<c:url var="processRequest" value="/riskmanager/query"/>
<form:form method="post" action="${processRequest}" modelAttribute="queryAttribute">
    <table>
        <tr>
            <td>
                <form:select path="organisation">
                    <form:option value="${null}" label="${optionMenuValueNotSelected}"/>
                    <form:options items="${existingOrganisations}" var="existingOrganisation"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>
                <form:select path="person">
                    <form:option value="${null}" label="${optionMenuValueNotSelected}"/>
                    <c:forEach items="${existingPersons}" var="existingPerson">
                        <form:option value="${existingPerson.id}" label="${existingPerson}"/>
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>

                <form:select path="businessProcessType">
                    <form:option value="${null}" label="${optionMenuValueNotSelected}"/>
                    <form:option value="${1}" label="${paymentProcessLabel}"/>
                    <form:option value="${2}" label="${informationalProcessLabel}"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>
                <form:checkbox path="requiresConfidentiality" label="${confidentialityLabel}"/>
            </td>
            <td>
        </tr>
        <tr>
            <td>
                <form:checkbox path="requiresIntegrity" label="${integrityLabel}"/>
            </td>

        </tr>
        <tr>
            <td>
                <form:checkbox path="requiresAvailability" label="${availabilityLabel}"/>
            </td>
        </tr>
    </table>
    <input type="submit"/>

</form:form>

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