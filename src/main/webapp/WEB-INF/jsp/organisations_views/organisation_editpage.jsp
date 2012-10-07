<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Редактирование описания организации</title>
</head>
<body>

<h1>Редактирование описания организации</h1>

<c:url var="saveUrl" value="/riskmanager/organisations/edit?id=${organisationAttribute.id}" />
<form:form modelAttribute="organisationAttribute" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="id">Id:</form:label></td>
            <td><form:input path="id" disabled="true"/></td>
        </tr>

        <tr>
            <td><form:label path="organisationName">Название</form:label></td>
            <td><form:input path="organisationName"/></td>
        </tr>

        <tr>
            <td><form:label path="organisationAddress">Адрес</form:label></td>
            <td><form:input path="organisationAddress"/></td>
        </tr>





    </table>

    <input type="submit" value="Save" />
</form:form>

</body>
</html>