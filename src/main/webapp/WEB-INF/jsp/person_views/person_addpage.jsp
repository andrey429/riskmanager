<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.addPageTitle"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-style.css" type="text/css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/sliding-elements.js"></script>


</head>
<body>

<tags:menu></tags:menu>
<div class="accordion">
    <form:form modelAttribute="personAttribute" method="POST" id="thisform">
        <h2><spring2:message code="label.person"/></h2>

        <h3><spring2:message code="label.personLastName"/></h3>

        <p>
                <%--<spring2:message code="help.personLastName"/>--%>
            <form:textarea path="lastName"/>
        </p>

        <h3>
            <spring2:message code="label.personFirstName"/>
        </h3>

        <p>
            <form:textarea path="firstName"/>
                <%--<spring2:message code="help.personFirstName"/>--%>
        </p>


        <h3>
            <spring2:message code="label.personSecondName"/>
        </h3>

        <p>
            <form:textarea path="secondName"/>
                <%--<spring2:message code="help.personSecondName"/>--%>
        </p>


        <h3>
            <spring2:message code="label.personOrganisation"/>
        </h3>

        <p>
                <%--<spring2:message code="help.personOrganisation"/>--%>
            <form:select path="organisation">
                <spring2:message code="label.optionMenuValueNotSelected" var="optionMenuValueNotSelected"/>
                <form:option value="${null}" label="${optionMenuValueNotSelected}"/>
                <c:forEach items="${existingOrganisations}" var="existingOrganisation">
                    <form:option value="${existingOrganisation.id}" label="${existingOrganisation.organisationName}"/>
                </c:forEach>
            </form:select>
        </p>

        <h3>
            <spring2:message code="label.personDepartment"/>
        </h3>

        <p>
                <%--<spring2:message code="help.personDepartment"/>--%>
            <form:textarea path="department"/>
        </p>

        <h3>
            <spring2:message code="label.personJobPosition"/>
        </h3>

        <p>
                <%--<spring2:message code="help.personJobPosition"/>--%>
            <form:textarea path="jobPosition"/>
        </p>

        <h3>
            <spring2:message code="label.loginUsername"/>

        </h3>

        <p>
                <%--<spring2:message code="help.personLogin"/>--%>
            <form:textarea path="login"/>
        </p>

        <h3>
            <spring2:message code="label.loginPassword"/>

        </h3>

        <p>
                <%--<spring2:message code="help.personPassword"/>--%>
            <form:password path="passwordHash"/>
        </p>

        <h3>
            <spring2:message code="label.accessRights"/>

        </h3>

        <p>
                <%--<spring2:message code="help.personPrivileges"/>--%>
            <form:select path="accessLevel">
                <form:option value="1">
                    <spring2:message code="label.accessRightsAdmin"/>
                </form:option>
                <form:option value="2">
                    <spring2:message code="label.accessRightsUser"/>
                </form:option>
            </form:select>
        </p>


        <h2 onclick="document.forms['thisform'].submit()" id="savebutton">
            <spring2:message code="risk.create.submit"/>
        </h2>

    </form:form>
</div>
</body>
</html>