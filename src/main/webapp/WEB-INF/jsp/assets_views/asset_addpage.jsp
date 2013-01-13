<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


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
    <form:form modelAttribute="assetAttribute" method="POST" id="thisform"><%-- action="${saveUrl}"--%>


        <h2><spring2:message code="label.asset"/></h2>

        <p><spring2:message code="help.assetHelp"/></p>

        <h3><spring2:message code="label.assetName"/></h3>
        <%--<p><td></td></p>--%>
        <p><form:textarea path="name"/><%--<spring2:message code="help.assetName"/>--%></p>

        <h3><spring2:message code="label.assetDescription"/></h3>


        <p><form:textarea path="description"/><%--<spring2:message code="help.assetDescription"/>--%></p>

        <h3 class="checkboxh3">
            <form:checkbox path="requiresConfidentiality"/>
            <spring2:message code="label.confidentiality"/></h3>

        <p><spring2:message code="help.assetConfidentiality"/></p>

        <h3><form:checkbox path="requiresIntegrity"/>
            <spring2:message code="label.integrity"/></h3>

        <p><spring2:message code="help.assetIntegrity"/></p>


        <h3><form:checkbox path="requiresAvailability"/>
            <spring2:message code="label.availability"/></h3>

        <p><spring2:message code="help.assetAvailability"/></p>


        <h3><spring2:message code="label.assetBusinessProcessType"/>

            <form:select path="businessProcessType">
                <spring2:message code="label.optionMenuValueNotSelected" var="optionMenuValueNotSelected"/>
                <form:option value="${null}" label="${optionMenuValueNotSelected}"/>
                <form:option value="1"><spring2:message code="label.assetPaymentBusinessProcess"/></form:option>
                <form:option value="2"><spring2:message code="label.assetInformationBusinessProcess"/></form:option>
            </form:select></h3>

        <p><spring2:message code="help.assetProcess"/></p>

        <h3>
            <spring2:message code="label.assetOwner"/>
            <form:select path="personOwner">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <c:set var="isAdminUser" value="${true}"/>
                    <spring2:message code="label.optionMenuValueNotSelected" var="optionMenuValueNotSelected"/>
                    <form:option value="${loggedInAuthority.id}" label="${loggedInAuthority}"/>
                    <c:forEach items="${existingPersons}" var="existingPerson">
                        <form:option value="${existingPerson.id}" label="${existingPerson}"/>
                    </c:forEach>
                </sec:authorize>
                <sec:authorize access="!hasRole('ROLE_ADMIN')">
                    <form:option value="${loggedInAuthority.id}" label="${loggedInAuthority}"/>
                </sec:authorize>
            </form:select>
        </h3>

        <p><spring2:message code="help.assetOwner"/></p>

        <h3>
            <spring2:message code="label.assetLocation"/>
            <form:textarea path="assetLocation"/>
        </h3>

        <p><spring2:message code="help.assetLocation"/></p>




        <h2 onclick="document.forms['thisform'].submit()" id="savebutton">
            <spring2:message code="risk.create.submit"/>
        </h2>



    </form:form>
</div>

</body>
</html>