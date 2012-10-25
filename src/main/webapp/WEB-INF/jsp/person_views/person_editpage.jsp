<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addpage.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/page.css" media="all"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.editPageTitle"/></title>
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




<div id="dough"/>

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

<c:url var="saveUrl" value="/riskmanager/persons/add"/>

<form:form modelAttribute="personAttribute" method="POST" action="${saveUrl}">
    <div class="accordion">

        <h3><spring2:message code="label.person"/></h3>

        <p>
            <spring2:message code="help.person"/>
        </p>

        <h3><spring2:message code="label.personLastName"/>
            <form:input path="lastName"/>
        </h3>

        <p>
            <spring2:message code="help.personLastName"/>
        </p>


        <h3>
            <spring2:message code="label.personFirstName"/>
            <td>
            <form:input path="firstName"/></h3>
        <p>
            <spring2:message code="help.personFirstName"/>
        </p>


        <h3>
            <spring2:message code="label.personSecondName"/>

            <form:input path="secondName"/></h3>

        <p>
            <spring2:message code="help.personSecondName"/>
        </p>


        <h3>
            <spring2:message code="label.personOrganisation"/>

            <form:select path="organisation">
                <spring2:message code="label.optionMenuValueNotSelected" var="optionMenuValueNotSelected"/>
                <form:option value="${null}" label="${optionMenuValueNotSelected}"/>
                <c:forEach items="${existingOrganisations}" var="existingOrganisation">
                    <form:option value="${existingOrganisation.id}" label="${existingOrganisation.organisationName}"/>
                </c:forEach>
            </form:select>
        </h3>

        <p>
            <spring2:message code="help.personOrganisation"/>
        </p>


        <h3>
            <spring2:message code="label.personDepartment"/>
            <form:input path="department"/></h3>

        <p>
            <spring2:message code="help.personDepartment"/>
        </p>

        <h3>
            <spring2:message code="label.personJobPosition"/>
            <form:input path="jobPosition"/>
        </h3>

        <h3>
            <spring2:message code="label.loginUsername"/>
            <form:input path="login"/>
        </h3>

        <p>
            <spring2:message code="help.personLogin"/>
        </p>

        <h3>
            <spring2:message code="label.loginPassword"/>
            <form:password path="passwordHash"/>
        </h3>

        <p>
            <spring2:message code="help.personPassword"/>
        </p>

        <h3>
            <spring2:message code="label.accessRights"/>
            <form:select path="accessLevel">
                <form:option value="1">
                    <spring2:message code="label.accessRightsAdmin"/>
                </form:option>
                <form:option value="2">
                    <spring2:message code="label.accessRightsUser"/>
                </form:option>
            </form:select>
        </h3>

        <p>
            <spring2:message code="help.personPrivileges"/>
        </p>


        <h3>
            <spring2:message code="label.saveButton" var="saveButton"/>
            <input type="submit" value="${saveButton}">
        </h3>

        <p><spring2:message code="help.saveDescription"/></p>
    </div>
</form:form>


</body>
</html>