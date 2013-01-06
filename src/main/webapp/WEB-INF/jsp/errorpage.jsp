<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<body>
<h3>
    <spring2:message code="self.errorpage.title"/>
</h3>

<p>
    <c:choose>
        <c:when test="${code == 1}">
            <spring2:message code="self.errorpage.noEv1"/>
        </c:when>
        <c:otherwise>
            <spring2:message code="self.errorpage.other"/>
        </c:otherwise>

    </c:choose>
</p>
</body>
</html>