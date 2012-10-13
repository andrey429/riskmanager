<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>



<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.addPageTitle"/></title>
</head>
<body>

<h1><spring2:message code="label.asset"/></h1>

<c:url var="saveUrl" value="/riskmanager/assets/add" />
<form:form modelAttribute="assetAttribute" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><spring2:message code="label.assetName"/></td>
            <td><form:input path="name"/></td>
        </tr>

        <tr>
            <td><spring2:message code="label.assetDescription"/></td>
            <td><form:input path="description"/></td>
        </tr>


        <tr>

            <td><form:checkbox path="requiresConfidentiality"/>
                <spring2:message code="label.confidentiality"/>
            </td>

        </tr>
        <tr>
            <td><form:checkbox path="requiresIntegrity"/>
                <spring2:message code="label.integrity"/>
            </td>
        </tr>

        <tr>
            <td><form:checkbox path="requiresAvailability"/>
                <spring2:message code="label.availability"/>
            </td>
        </tr>



        <tr>
            <td><spring2:message code="label.damageIfConfidentialityLost"/></td>
            <td><form:input path="damageIfConfidentialityLost"/></td>
        </tr>


        <tr>
            <td><spring2:message code="label.damageIfIntegrityLost"/></td>
            <td><form:input path="damageIfIntegrityLost"/></td>
        </tr>

        <tr>
            <td><spring2:message code="label.damageIfAvailabilityLost"/></td>
            <td><form:input path="damageIfAvailabilityLost"/></td>
        </tr>

        <tr>
            <td><spring2:message code="label.assetBusinessProcessType"/></td>
            <td>
                <form:select path="businessProcessType">
                    <form:option value="1"><spring2:message code="label.assetPaymentBusinessProcess"/></form:option>
                    <form:option value="2"><spring2:message code="label.assetInformationBusinessProcess"/></form:option>
                </form:select>
            </td>


        </tr>


        <tr>
            <td><spring2:message code="label.assetLocation"/></td>
            <td><form:input path="assetLocation"/></td>
        </tr>

    </table>

    <spring2:message code="label.saveButton" var="saveButton"/>
    <input type="submit" value="${saveButton}">

</form:form>

</body>
</html>