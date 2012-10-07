<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Добавление сотрудника завершено</title>
</head>
<body>

<h1>Новый сотрудник успешно добавлен</h1>

<p>Новый сотрудник добавлен. Дата добавления: <%= new java.util.Date() %></p>

<c:url var="mainUrl" value="/riskmanager/main/persons" />
<p>Вернуться к<a href="${mainUrl}">списку сотрудников</a></p>

</body>
</html>