<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring2"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring2:message code="label.loginPageTitle" /></title>
</head>
<body>


    <h1>
        <spring2:message code="label.loginPageTitle"/>
    </h1>


    <div id="login-error">${error}</div>
    <form action="../../j_spring_security_check" method="post" >

        <p>
            <label for="j_username">
                <spring2:message code="label.loginUsername"/>
            </label>
            <input id="j_username" name="j_username" type="text" />
        </p>
        <p>
            <label for="j_password"><spring2:message code="label.loginPassword"/></label>
            <input id="j_password" name="j_password" type="password" />
          </p>
        <spring2:message code="label.loginSubmit" var="loginSubmit"/>
        <input  type="submit" value="${loginSubmit}"/>

    </form></body>
</html>

