<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.addPageTitle"/> </title>
</head>
<body>

<h1><spring2:message code="label.organisation"/> </h1>

<c:url var="saveUrl" value="/riskmanager/organisations/add" />
<form:form modelAttribute="organisationAttribute" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td>
                <spring2:message code="label.organisationName"/>
            </td>
            <td><form:input path="organisationName"/></td>
        </tr>

        <tr>
            <td>
                <spring2:message code="label.organisationAddress"/>
            </td>
            <td><form:input path="organisationAddress"/></td>
        </tr>



    </table>

    <spring2:message code="label.saveButton" var="saveButton"/>
    <input type="submit" value="${saveButton}">

</form:form>


<c:url var="mainUrl" value="/riskmanager/ "/>
<p>
    <a href="${mainUrl}"><spring2:message code="label.gotoMainURL"/> </a>
</p>

</body>
</html>