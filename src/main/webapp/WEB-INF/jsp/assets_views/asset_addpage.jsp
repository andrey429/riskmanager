<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addpage.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/page.css" media="all"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.addPageTitle"/></title>
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


<c:url var="saveUrl" value="/riskmanager/assets/add"/>
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

<div id="dough"></div>
<form:form modelAttribute="assetAttribute" method="POST" action="${saveUrl}">

    <div class="accordion">
        <h3><spring2:message code="label.asset"/> </h3>
        <p><spring2:message code="help.assetHelp"/> </p>
        <h3><spring2:message code="label.assetName"/><br><br><form:input path="name"/></h3>
            <%--<p><td></td></p>--%>
        <p><spring2:message code="help.assetName"/></p>

        <h3><spring2:message code="label.assetDescription"/><br><br><form:textarea path="description"/></h3>


        <p><spring2:message code="help.assetDescription"/></p>

        <h3><form:checkbox path="requiresConfidentiality"/>
            <spring2:message code="label.confidentiality"/></h3>

        <p><spring2:message code="help.assetConfidentiality"/></p>

        <h3><form:checkbox path="requiresIntegrity"/>
            <spring2:message code="label.integrity"/></h3>

        <p><spring2:message code="help.assetIntegrity"/></p>


        <h3><form:checkbox path="requiresAvailability"/>
            <spring2:message code="label.availability"/></h3>

        <p><spring2:message code="help.assetAvailability"/></p>


        <h3><spring2:message code="label.assetBusinessProcessType"/>

            <form:select path="businessProcessType">
                <spring2:message code="label.optionMenuValueNotSelected" var="optionMenuValueNotSelected"/>
                <form:option value="${null}" label="${optionMenuValueNotSelected}"/>
                <form:option value="1"><spring2:message code="label.assetPaymentBusinessProcess"/></form:option>
                <form:option value="2"><spring2:message code="label.assetInformationBusinessProcess"/></form:option>
            </form:select></h3>

        <p><spring2:message code="help.assetProcess"/></p>

        <h3>
            <spring2:message code="label.assetOwner"/>
            <form:select path="personOwner">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <c:set var="isAdminUser" value="${true}"/>
                    <spring2:message code="label.optionMenuValueNotSelected" var="optionMenuValueNotSelected"/>
                    <form:option value="${loggedInAuthority.id}" label="${loggedInAuthority}"/>
                    <c:forEach items="${existingPersons}" var="existingPerson">
                        <form:option value="${existingPerson.id}" label="${existingPerson}"/>
                    </c:forEach>
                </sec:authorize>
                <sec:authorize access="!hasRole('ROLE_ADMIN')">
                    <form:option value="${loggedInAuthority.id}" label="${loggedInAuthority}"/>
                </sec:authorize>
            </form:select>
        </h3>

        <p><spring2:message code="help.assetOwner"/></p>

        <h3>
            <spring2:message code="label.assetLocation"/>
            <form:input path="assetLocation"/>
        </h3>

        <p><spring2:message code="help.assetLocation"/></p>


        <h3><spring2:message code="label.saveDescription"/> <br><br>
            <spring2:message code="label.saveButton" var="saveButton"/>
            <input type="submit" value="${saveButton}"></h3>

        <p><spring2:message code="help.saveDescription"/> </p>

    </div>
    <%--<table style="background: #800000; color: #ffffff;">
    <caption>
        <spring2:message code="label.asset"/>
    </caption>--%>
    <%--    <thead>

    </thead>--%>
    <%--<tr>
        <td><spring2:message code="label.assetName"/></td>
        <td><form:input path="name"/></td>
    </tr>--%>

    <%--<tr>
        <td><spring2:message code="label.assetDescription"/></td>
        <td><form:input path="description"/></td>
    </tr>--%>


    <%--<tr>

                <td><form:checkbox path="requiresConfidentiality"/>
                    <spring2:message code="label.confidentiality"/>
                </td>

            </tr>
    --%> <%--    <tr>
            <td><form:checkbox path="requiresIntegrity"/>
                <spring2:message code="label.integrity"/>
            </td>
        </tr>--%>

    <%--<tr>
        <td><form:checkbox path="requiresAvailability" />
            <spring2:message code="label.availability"/>
        </td>
    </tr>--%>


    <%--<tr>
        <td><spring2:message code="label.assetBusinessProcessType"/></td>
        <td>
            <form:select path="businessProcessType">
                <spring2:message code="label.optionMenuValueNotSelected" var="optionMenuValueNotSelected"/>
                <form:option value="${null}" label="${optionMenuValueNotSelected}"/>
                <form:option value="1"><spring2:message code="label.assetPaymentBusinessProcess"/></form:option>
                <form:option value="2"><spring2:message code="label.assetInformationBusinessProcess"/></form:option>
            </form:select>
        </td>


    </tr>--%>

    <%--<tr>
                <td>
                    <spring2:message code="label.assetOwner"/>

                </td>

                <td>
                    <form:select path="personOwner">

                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <c:set var="isAdminUser" value="${true}"/>

                            <spring2:message code="label.optionMenuValueNotSelected" var="optionMenuValueNotSelected"/>
                            <form:option value="${loggedInAuthority.id}" label="${loggedInAuthority}"/>
                            <c:forEach items="${existingPersons}" var="existingPerson">
                                <form:option value="${existingPerson.id}" label="${existingPerson}"/>

                            </c:forEach>

                        </sec:authorize>

                        <sec:authorize access="!hasRole('ROLE_ADMIN')">

                            <form:option value="${loggedInAuthority.id}" label="${loggedInAuthority}"/>

                        </sec:authorize>

                    </form:select>
                </td>

            </tr>

    --%>
    <%--<tr>
        <td><spring2:message code="label.assetLocation"/></td>
        <td><form:input path="assetLocation"/></td>
    </tr>--%>

    <%--</table>--%>

    <%--<spring2:message code="label.saveButton" var="saveButton"/>
  <input type="submit" value="${saveButton}">--%>


</form:form>


</body>
</html>