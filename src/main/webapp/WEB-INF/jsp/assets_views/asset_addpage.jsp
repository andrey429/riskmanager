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

            <td><form:checkbox path="requiresConfidentiality" />
                <spring2:message code="label.confidentiality"/>
            </td>

        </tr>
        <tr>
            <td><form:checkbox path="requiresIntegrity"/>
                <spring2:message code="label.integrity"/>
            </td>
        </tr>

        <tr>
            <td><form:checkbox path="requiresAvailability" onchange=""/>
                <spring2:message code="label.availability"/>
            </td>
        </tr>




        <tr>
            <td><spring2:message code="label.assetBusinessProcessType"/></td>
            <td>
                <form:select path="businessProcessType">
                    <spring2:message code="label.optionMenuValueNotSelected" var="optionMenuValueNotSelected"/>
                    <form:option value="${null}" label="${optionMenuValueNotSelected}"/>
                    <form:option value="1"><spring2:message code="label.assetPaymentBusinessProcess"/></form:option>
                    <form:option value="2"><spring2:message code="label.assetInformationBusinessProcess"/></form:option>
                </form:select>
            </td>


        </tr>

        <tr>
            <td>
                <spring2:message code="label.assetOwner"/>

            </td>

            <td><form:select path="personOwner">
                <spring2:message code="label.optionMenuValueNotSelected" var="optionMenuValueNotSelected"/>
                <form:option value="${null}" label="${optionMenuValueNotSelected}"/>
                <c:forEach items="${existingPersons}" var="existingPerson">
                    <form:option value="${existingPerson.id}" label="${existingPerson}"/>
                </c:forEach>
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