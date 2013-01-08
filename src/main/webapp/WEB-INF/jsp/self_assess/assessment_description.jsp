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


    <form:form modelAttribute="selfAssessmentModel"
               id="thisform">

        <h3><spring2:message code="self.newAssessment.name"/></h3>

        <p>
            <form:input path="selfAssessmentName"/>
        </p>

        <h3><spring2:message code="self.newAssessment.description"/>
        </h3>

        <p>
            <form:input path="description"/>
        </p>

        <h3><spring2:message code="self.newAssessment.auditor"/>
        </h3>

        <p>
            <form:input path="auditors"/>
        </p>

        <h3><spring2:message code="self.newAssessment.author"/>
        </h3>

        <p>
            <form:input path="creator"/>
        </p>

        <h3 onclick="document.forms['thisform'].submit()">
            <spring2:message code="self.newAssessment.submit"/>
        </h3>

    </form:form>
</div>


</body>
</html>