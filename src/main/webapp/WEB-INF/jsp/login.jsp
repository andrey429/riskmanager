<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring2" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/page.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addpage.css" media="all"/>
    <title><spring2:message code="label.loginPageTitle"/></title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/perfect_login.css" media="all">



</head>
<body>





<form class="form-1" action="../../j_spring_security_check" method="post" id="loginform">
    <spring2:message code="label.loginUsername" var="username"/>
    <spring2:message code="label.loginPassword" var="password"/>

    <p class="field">

        <input type="text" name="j_username" placeholder="${username}" id="j_username">

    </p>
    <p class="field">
        <input type="password" name="j_password" placeholder="${password}" id="j_password">

    </p>

    <p class="submit">
        <button type="submit" name="submit">-></button>
    </p>
</form>


</form>
</body>
</html>

