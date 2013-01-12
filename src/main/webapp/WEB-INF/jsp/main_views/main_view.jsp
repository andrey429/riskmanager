<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>


<!--security-->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>



    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/page.css" media="all"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.appName"/></title>

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



<c:url var="assetsUrl" value="/riskmanager/assets/"/>
<c:url var="personsUrl" value="/riskmanager/persons/"/>
<c:url var="organisationsUrl" value="/riskmanager/organisations/"/>
<c:url var="queryURL" value="/riskmanager/query"/>
<c:url var="downloadReportURL" value="/riskmanager/report/"/>

<div id="dough">

</div>



<div id="intro">
    <h2>Добро пожаловать в систему RiskManager 0x1</h2>
</div>

    <table style="border: 1px solid; width: 500px; text-align:center">
        <caption>
            <spring2:message code="label.menu"></spring2:message>
        </caption>
        <thead style="background:#f1edff">
        <th>
            <spring2:message code="label.youHaveSignedInAsMessage"/> <b>${loggedInUsername}</b> ( ${loggedInFullName} )
        </th>
        </thead>
        <tbody>
        <tr>


            <th><a href="${assetsUrl}"><spring2:message code="label.appAssetsTabLabel"/> </a></th>


            <!--only admins can add persons or organisations-->


        </tr>
        <tr>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <th><a href="${personsUrl}"><spring2:message code="label.appPersonsTabLabel"/></a></th>



    </sec:authorize>
    </tr>
    <tr>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <th><a href="${organisationsUrl}"><spring2:message code="label.appOrganisationsTabLabel"/></a></th>
        </sec:authorize>
    </tr>
    <tr>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <th><a href="${queryURL}"><spring2:message code="label.queryPageTitle"/> </a></th>
        </sec:authorize>
    </tr>
    <tr>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <th><a href ="${downloadReportURL}"><spring2:message code="label.appDownloadReport"/> </a></th>
    </sec:authorize>
    </tr>
    <tr>
        <th>
    <c:url var="logoutURL" value="/riskmanager/auth/logout"/>
    <a href="${logoutURL}"><spring2:message code="label.loginLogoutSubmit"/></a>
    </th>
    </tr>

    </tbody>


    </table>


<%--</div>
    </div>--%>

</body>
</html>