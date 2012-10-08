<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Описание нового актива</title>
</head>
<body>

<h1>Описать актив области оценки рисков</h1>

<c:url var="saveUrl" value="/riskmanager/assets/add" />
<form:form modelAttribute="assetAttribute" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="name">Название</form:label></td>
            <td><form:input path="name"/></td>
        </tr>

        <tr>
            <td><form:label path="description">Описание</form:label></td>
            <td><form:input path="description"/></td>
        </tr>


        <tr>

            <td><form:checkbox path="isRequiresConfidentiality" label="Конфиденциальность"/></td>

        </tr>
        <tr>
            <td><form:checkbox path="isRequiresIntegrity" label= "Целостность"/></td>
        </tr>

        <tr>
            <td><form:checkbox path="isRequiresAvailability" label="Доступность"/></td>
        </tr>



        <tr>
            <td><form:label path="damageIfConfidentialityLost">Ущерб от потери конфиденциальности</form:label></td>
            <td><form:input path="damageIfConfidentialityLost"/></td>
        </tr>


        <tr>
            <td><form:label path="damageIfIntegrityLost">Ущерб от потери целостности</form:label></td>
            <td><form:input path="damageIfIntegrityLost"/></td>
        </tr>

        <tr>
            <td><form:label path="damageIfAvailabilityLost">Ущерб от потери доступности</form:label></td>
            <td><form:input path="damageIfAvailabilityLost"/></td>
        </tr>

    </table>

    <input type="submit" value="Save" />
</form:form>

</body>
</html>