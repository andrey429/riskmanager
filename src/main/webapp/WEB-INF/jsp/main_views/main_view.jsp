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
    <title><spring2:message code="label.appName"/></title>
</head>
<body>
<h1><spring2:message code="label.appName"/></h1>

<c:url var="assetsUrl" value="/riskmanager/assets/" />
<c:url var="personsUrl" value="/riskmanager/persons/" />
<c:url var="organisationsUrl" value="/riskmanager/organisations/" />



        <h2><spring2:message code="label.appRiskEvaluationScope"/></h2>
        <table style="border: 1px solid; width: 500px; text-align:center">
            <thead style="background:#fcf">
            <tr>

                <th><a href="${assetsUrl}"><spring2:message code="label.appAssetsTabLabel"/> </a></th>
                <th><a href="${personsUrl}"><spring2:message code="label.appPersonsTabLabel"/></a></th>
                <th><a href="${organisationsUrl}"><spring2:message code="label.appOrganisationsTabLabel"/></a></th>

            </tr>
            </thead>

        </table>






</body>
</html>