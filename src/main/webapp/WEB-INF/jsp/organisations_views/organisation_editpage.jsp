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

<c:url var="saveUrl" value="/riskmanager/organisations/edit?id=${organisationAttribute.id}"/>
<form:form modelAttribute="organisationAttribute" method="POST" action="${saveUrl}">

    <div class="accordion">

        <h3><spring2:message code="label.organisation"/></h3>

        <p><spring2:message code="help.organisation"/></p>

        <h3><spring2:message code="label.organisationName"/>
            <br><br>
            <form:textarea path="organisationName"/>
        </h3>

        <p>
            <spring2:message code="help.organisationName"/>
        </p>


        <h3>
            <spring2:message code="label.organisationAddress"/>
            <br>
            <br>
            <form:textarea path="organisationAddress"/>
        </h3>

        <p><spring2:message code="help.organisationAddress"/></p>

        <h3><spring2:message code="label.saveDescription"/><br><br>
            <spring2:message code="label.saveButton" var="saveButton"/>
            <input type="submit" value="${saveButton}">
        </h3>

        <p><spring2:message code="help.saveDescription"/></p>
    </div>
</form:form>



</body>
</html>