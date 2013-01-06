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


<c:url var="ev1URL" value="/riskmanager/self-assessment/menu/ev1?assessmentID=${selfAssessmentModel.id}"/>
<c:url var="ev2URL" value="/riskmanager/self-assessment/menu/ev2?assessmentID=${selfAssessmentModel.id}"/>
<c:url var="ev3URL" value="/riskmanager/self-assessment/menu/ev3?assessmentID=${selfAssessmentModel.id}"/>


<div class="accordion">

    <h3><spring2:message code="self.menuPage.currentAssessment"/>: <i><c:out
            value="${selfAssessmentModel.selfAssessmentName}"/></i></h3>

    <p>
        <spring2:message code="self.menupage.actionDescription"/>
        <br>

    </p>


    <h3 onclick="location.href='${ev1URL}'"><spring2:message code="self.menupage.EV1description"/>
    </h3>

    <h3 onclick="location.href='${ev2URL}'"><spring2:message code="self.menupage.EV2description"/>
    </h3>

    <h3 onclick="location.href='${ev3URL}'"><spring2:message code="self.menupage.EV3description"/>
    </h3>


</div>


</body>
</html>