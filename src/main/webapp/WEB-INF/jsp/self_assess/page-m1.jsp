<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addpage.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/page.css" media="all"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="self.mainPage"/></title>

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

<c:url var="saveUrl" value="/riskmanager/self-assessment/m1"/>
<%--<form:form modelAttribute="personAttribute" method="POST" action="${saveUrl}">--%>
<div class="accordion">

    <form:form modelAttribute="m1ChapterModel" method="POST" action="${saveUrl}">
    <h3>
        <b>M1.1</b>
        Определены ли в документах организации роли ее работников?
    </h3>
    <p>

        <form:select path="${}">
            <form:option value="0"/>
            <form:option value="0.25"/>
            <form:option value="0.5"/>
            <form:option value="0.75"/>
            <form:option value="1"/>
        </form:select>
        <input type="submit">
    </p>

    </form:form>
</div>
<%--</form:form>--%>


</body>
</html>