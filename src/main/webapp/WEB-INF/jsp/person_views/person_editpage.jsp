<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.editPageTitle"/></title>
</head>
<body>

<h1><spring2:message code="label.person"/></h1>

<c:url var="saveUrl" value="/riskmanager/persons/edit?id=${personAttribute.id}"/>
<form:form modelAttribute="personAttribute" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td>ID: <c:out value="${personAttribute.id}"/></td>
        </tr>

        <tr>
            <td>
                <spring2:message code="label.personLastName"/>

            </td>
            <td><form:input path="lastName"/></td>
        </tr>

        <tr>
            <td><spring2:message code="label.personFirstName"/></td>
            <td><form:input path="firstName"/></td>
        </tr>

        <tr>
            <td>
                <spring2:message code="label.personSecondName"/>
            </td>
            <td><form:input path="secondName"/></td>
        </tr>

        <tr>
            <td>
                <spring2:message code="label.personOrganisation"/>
            </td>
            <td><form:select path="organisation">
                <spring2:message code="label.optionMenuValueNotSelected" var="optionMenuValueNotSelected"/>
                <form:option value="${null}" label="${optionMenuValueNotSelected}"/>
                <c:forEach items="${existingOrganisations}" var="existingOrganisation">
                    <form:option value="${existingOrganisation.id}" label="${existingOrganisation.organisationName}"/>
                </c:forEach>
            </form:select>
            </td>
        </tr>

        <tr>
            <td>
                <spring2:message code="label.personDepartment"/>
            </td>
            <td><form:input path="department"/></td>
        </tr>

        <tr>
            <td>
                <spring2:message code="label.personJobPosition"/>
            </td>
            <td><form:input path="jobPosition"/></td>
        </tr>

        <tr>
            <td>
                <spring2:message code="label.loginUsername"/>
                <form:input path="login"/>
            </td>
            <td>
                <spring2:message code="label.loginPassword"/>
                <form:password path="passwordHash" />
            </td>

        </tr>
        <tr>
            <td>
                <spring2:message code="label.accessRights"/>
                <form:select path="accessLevel">
                    <form:option value="1">
                        <spring2:message code="label.accessRightsAdmin"/>
                    </form:option>
                    <form:option value="2">
                        <spring2:message code="label.accessRightsUser"/>
                    </form:option>

                </form:select>


            </td>
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