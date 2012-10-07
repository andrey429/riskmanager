<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Редактирование описания сотрудника</title>
</head>
<body>

<h1>Редактирование описания сотрудника</h1>

<c:url var="saveUrl" value="/riskmanager/persons/edit?id=${personAttribute.id}" />
<form:form modelAttribute="personAttribute" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="id">Id:</form:label></td>
            <td><form:input path="id" disabled="true"/></td>
        </tr>

        <tr>
            <td><form:label path="lastName">Фамилия</form:label></td>
            <td><form:input path="lastName"/></td>
        </tr>

        <tr>
            <td><form:label path="firstName">Имя:</form:label></td>
            <td><form:input path="firstName"/></td>
        </tr>



        <tr>
            <td><form:label path="secondName">Отчество</form:label></td>
            <td><form:input path="secondName"/></td>
        </tr>


        <tr>
            <td><form:label path="organization">Организация</form:label></td>
            <td><form:input path="organization"/></td>
        </tr>

        <tr>
            <td><form:label path="department">Структурное подразделение</form:label></td>
            <td><form:input path="department"/></td>
        </tr>

        <tr>
            <td><form:label path="jobPosition">Должность</form:label></td>
            <td><form:input path="jobPosition"/></td>
        </tr>

    </table>

    <input type="submit" value="Save" />
</form:form>

</body>
</html>