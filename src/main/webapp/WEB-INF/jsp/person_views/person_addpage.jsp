<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.addPageTitle"/></title>
</head>
<body>

<h1><spring2:message code="label.person"/></h1>

<c:url var="saveUrl" value="/riskmanager/persons/add" />
<form:form modelAttribute="personAttribute" method="POST" action="${saveUrl}">
    <table>
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
            <td><form:input path="organization"/></td>
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



    </table>

    <spring2:message code="label.saveButton" var="saveButton"/>
    <input type="submit" value="${saveButton}">
</form:form>

</body>
</html>