<%--
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.site.com/myTlds" prefix="func" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addpage.css" media="all"/>
    &lt;%&ndash;<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/page.css" media="all"/>&ndash;%&gt;
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="risk.pagetitle.risk"/></title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>


</head>
<body>


<form:form modelAttribute="scopeObject" id="scopeForm">


    <div class="accordion">
        <h2><spring2:message code="risk.scopeObject"/></h2>

        <h3><spring2:message code="risk.mediaType"/></h3>


        <p>
            <form:select path="assetType">
                <c:forEach items="${storedAssetTypes}" var="storedAsset">
                    <form:option value="${storedAsset.id}">
                        ${storedAsset}
                    </form:option>
                </c:forEach>
            </form:select>
        </p>

        <h3><spring2:message code="risk.mediaType"/></h3>


        <p>
            <form:select path="mediaType">
                <c:forEach items="${storedMediaTypes}" var="storedMediaType">
                    <form:option value="${storedMediaType.id}">
                        ${storedMediaType}
                    </form:option>
                </c:forEach>
            </form:select>
        </p>

        <h2 onclick="document.forms['scopeForm'].submit()" id="savebutton"><spring2:message code="risk.create.submit"/>
        </h2>
    </div>
</form:form>


</body>
</html>--%>
