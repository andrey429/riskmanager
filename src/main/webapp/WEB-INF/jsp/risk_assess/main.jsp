<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 08.01.13
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
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



<c:url var="createMenuURL" value="/riskmanager/risk-assessment/create"/>
<c:url var="showMenuURL" value="/riskmanager//risk-assessment/show"/>

<div class="accordion">

    <h3><spring2:message code="risk.main.welcome"/></h3>

    <p>
        <spring2:message code="risk.main.welcome.help"/>
    </p>

    <h3 onclick="location.href='${createMenuURL}'"><spring2:message code="risk.main.createMenu"/>
    </h3>


    <h3 onclick="location.href='${showMenuURL}'">
        <spring2:message code="risk.main.showMenu"/>
    </h3>



</div>


</body>
</html>