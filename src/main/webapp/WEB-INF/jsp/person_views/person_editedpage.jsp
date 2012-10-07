<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Редактирование описания сотрудника завершено</title>
</head>
<body>

<h1>Сотрудники</h1>

<p>Вы отредактировали описание сотрудника с id ${id} , дата редактирования  <%= new java.util.Date() %></p>

<c:url var="mainUrl" value="/riskmanager/persons/list" />
<p>Вернуться к <a href="${mainUrl}">списку сотрудников</a></p>

</body>
</html>