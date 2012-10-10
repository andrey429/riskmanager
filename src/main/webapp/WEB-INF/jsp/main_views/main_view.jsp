<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>



<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>RiskManager v. 0.1</title>
</head>
<body>
<h1>RiskManager v. 0.1</h1>

<c:url var="assetsUrl" value="/riskmanager/assets/" />
<c:url var="personsUrl" value="/riskmanager/persons/" />
<c:url var="organisationsUrl" value="/riskmanager/organisations/" />


        <h2>Область оценки рисков</h2>
        <table style="border: 1px solid; width: 500px; text-align:center">
            <thead style="background:#fcf">
            <tr>

                <th><a href="${assetsUrl}">Активы</a></th>
                <th><a href="${personsUrl}">Организации</a></th>
                <th><a href="${organisationsUrl}">Сотрудники</a></th>

            </tr>
            </thead>

        </table>






</body>
</html>