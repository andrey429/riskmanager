<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.addPageTitle"/></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-style.css" type="text/css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/sliding-elements.js"></script>

</head>
<body>

<tags:menu></tags:menu>


<div class="accordion">
<form:form modelAttribute="organisationAttribute" method="POST" id="thisform">


        <h2><spring2:message code="label.organisation"/> </h2>
        <%--<p><spring2:message code="help.organisation"/> </p>--%>
        <h3><spring2:message code="label.organisationName"/>

        </h3>

        <p>
            <form:textarea path="organisationName"/>
            <%--<spring2:message code="help.organisationName"/>--%>
        </p>


        <h3>
            <spring2:message code="label.organisationAddress"/>


        </h3>

        <p><%--<spring2:message code="help.organisationAddress"/>--%>
            <form:textarea path="organisationAddress"/></p>

    <h2 onclick="document.forms['thisform'].submit()" id="savebutton">
        <spring2:message code="risk.create.submit"/>
    </h2>

</form:form>
</div>


</body>
</html>