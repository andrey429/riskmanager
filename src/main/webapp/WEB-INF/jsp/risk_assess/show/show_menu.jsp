<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addpage.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/page.css" media="all"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="self.mainPage"/></title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".accordion h3:first").addClass("active");
            $(".accordion p:not(:first)").hide();
            $(".accordion h3").click(function () {
                $(this).next("p").slideToggle("slow")
                        .siblings("p:visible").slideUp("slow");
                $(this).toggleClass("active");
                $(this).siblings("h3").removeClass("active");
            });

        });
    </script>
</head>
<body>


<div id="dough"/>
<div id="intro">
    <p>
        <c:url var="mainUrl" value="/riskmanager/ "/>
        <a href="${mainUrl}"><spring2:message code="label.gotoMainURL"/> </a>
    </p>

    <p>
        <c:url var="logoutURL" value="/riskmanager/auth/logout"/>
        <a href="${logoutURL}"><spring2:message code="label.loginLogoutSubmit"/></a>
    </p>
</div>


<div class="accordion">

    <h3><spring2:message code="risk.show.menu.title"/></h3>
    <h3><spring2:message code="risk.assetType"/></h3>

    <p>

        <c:choose>
            <c:when test="${not empty storedAssetTypes}">
                <select size="4">

                    <c:forEach var="storedAssetType" items="${storedAssetTypes}">
                        <c:url var="editAssetTypeURL"
                               value="/riskmanager/risk-assessment/edit/asset-type?assetTypeID=${storedAssetType.id}"/>
                        <option onclick="location.href='${editAssetTypeURL}'">
                                ${storedAssetType.assetTypeName}
                        </option>
                    </c:forEach>
                </select>
            </c:when>
            <c:otherwise>
                <spring2:message code="risk.error.notExist"/>
            </c:otherwise>
        </c:choose>


    </p>

    <h3><spring2:message code="risk.mediaType"/></h3>

    <p>

        <c:choose>
            <c:when test="${not empty storedMediaTypes}">
                <select size="4">

                    <c:forEach var="storedMediaType" items="${storedMediaTypes}">
                        <c:url var="editMediaTypeURL"
                               value="/riskmanager/risk-assessment/edit/media-type?mediaTypeID=${storedMediaType.id}"/>
                        <option onclick="location.href='${editMediaTypeURL}'">
                                ${storedMediaType.mediaTypeName}
                        </option>
                    </c:forEach>
                </select>
            </c:when>
            <c:otherwise>
                <spring2:message code="risk.error.notExist"/>
            </c:otherwise>
        </c:choose>


    </p>


</div>


</body>
</html>