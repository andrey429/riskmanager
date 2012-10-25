<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring2" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu_table.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/page.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addpage.css" media="all"/>


    <title><spring2:message code="label.loginPageTitle"/></title>

</head>
<body>


<%--
<div id="login_welcome">

    <h2>
        <spring2:message code="label.loginPageTitle"/>
    </h2>
</div>
--%>

<div id="dough"></div>


<form action="../../j_spring_security_check" method="post">


    <div id="intro">
        Для осуществления действий в системе необходимо пройти авторизацию.
        <br>Введите логин и пароль в форме ниже
    </div>


    <div class="accordion">
        <h3>
        <label for="j_username"><spring2:message code="label.loginUsername"/></label>
        <input id="j_username" name="j_username" type="text"/>
        </h3>
        <p>
            Имя пользователя для идентификации в системе
        </p>

        <h3>
            <label for="j_password"><spring2:message code="label.loginPassword"/></label>
            <input id="j_password" name="j_password" type="password"/>
        </h3>

        <p>
            Пароль для входа
        </p>

        <h3>
            <strong><spring2:message code="label.loginSubmit"/></strong>
            <br>
            <spring2:message code="label.loginSubmit" var="loginSubmit"/>
            <br>
            <input type="submit" value="${loginSubmit}" style="height: 30px; width: 140px"/>
        </h3>



    </div>

    <%--<table style="border: 1px solid; width: 500px; text-align:center">
        <caption>
            <spring2:message code="label.loginPageTitle"/>
        </caption>
        <tbody>
        <tr>
            <td>

                <label for="j_username"><spring2:message code="label.loginUsername"/></label>

                <input id="j_username" name="j_username" type="text"/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="j_password"><spring2:message code="label.loginPassword"/></label>
                <input id="j_password" name="j_password" type="password"/>
            </td>
        </tr>
        <tr>
            <td>
                <div id="login-error">
                    <c:choose>
                        <c:when test="${not empty error}">
                            <div style="color: red">
                            <spring2:message code="label.loginError"/>
                            </div>
                        </c:when>
                        &lt;%&ndash;${error}&ndash;%&gt;
                    </c:choose>
                </div>
            </td>
        </tr>
        <tr>
            <td>
            <spring2:message code="label.loginSubmit" var="loginSubmit"/>
            <input type="submit" value="${loginSubmit}"/>
            </td>
        </tr>
        </tbody>
        </table>
--%>
</form>
</body>
</html>

