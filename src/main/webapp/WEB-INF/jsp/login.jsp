<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring2" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">


    <title><spring2:message code="label.loginPageTitle"/></title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/perfect_login.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common-style.css"/>
     <style type="text/css">
         div.mydiv {
             width: 350px;
             height: 300px;

             background-image: linear-gradient(bottom, rgb(131, 124, 245) 0%, rgb(247,243,242) 34%, rgb(255,255,255) 82%);
             background-image: -o-linear-gradient(bottom, rgb(131, 124, 245) 0%, rgb(247,243,242) 34%, rgb(255,255,255) 82%);
             background-image: -moz-linear-gradient(bottom, rgb(131, 124, 245) 0%, rgb(247,243,242) 34%, rgb(255,255,255) 82%);
             background-image: -webkit-linear-gradient(bottom, rgb(131, 124, 245) 0%, rgb(247,243,242) 34%, rgb(255,255,255) 82%);
             background-image: -ms-linear-gradient(bottom, rgb(131, 124, 245) 0%, rgb(247,243,242) 34%, rgb(255,255,255) 82%);

             /*background-image: -webkit-gradient(
                 linear,
                 left bottom,
                 left top,
                 color-stop(0, rgb(245,196,196)),
                 color-stop(0.34, rgb(247,243,242)),
                 color-stop(0.82, rgb(255,255,255))
             );*/

         }
     </style>

</head>
<body>




<div class="mydiv">
    <h2>Добро пожаловать в систему ISManager 0.5a!</h2>
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
        <button type="submit" name="submit">></button>
    </p>
</form>


    <h2>Для входа введите свой логин и пароль</h2>
</div>


</body>
</html>

