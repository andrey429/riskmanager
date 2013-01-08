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
    <title><spring2:message code="risk.pagetitle.risk"/></title>

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

    <script type="text/javascript">
        function sendForms() {
            document.forms['scopeForm'].submit();
            document.forms['riskForm'].submit();
        }
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


<c:url var="getBackURL" value="/riskmanager/risk-assessment/create"/>

<div class="accordion">

    <c:choose>
        <c:when test="${empty storedAssetTypes or empty storedMediaTypes}">
            <h3 onclick="location.href='${getBackURL}'">
                <spring2:message code="risk.error.notEnoughScope"/>
            </h3>
        </c:when>
        <c:otherwise>

            <form:form modelAttribute="scopeObject"
                       id="scopeForm">


                <h3>
                        <%--<spring2:message code=""/>--%>
                    AssetType<br><br>
                    <form:select path="assetType">
                    <c:forEach items="${storedAssetTypes}" var="storedAT">
                        <form:option value="${storedAT.id}" label="${storedAT}"/>
                    </c:forEach>
                    </form:select>
                </h3>

                <h3>
                    MediaType<br><br>
                    <form:select path="mediaType" >
                        <c:forEach items="${storedMediaTypes}" var="storedMT">
                            <form:option value="${storedMT.id}" label="${storedMT}"/>
                        </c:forEach>
                    </form:select>
                </h3>

            </form:form>
            <form:form modelAttribute="riskDetail" id="riskForm">

            </form:form>
            <h3 onclick="sendForms()"><spring2:message code="risk.create.submit"/></h3>
        </c:otherwise>
    </c:choose>
</div>


</body>
</html>