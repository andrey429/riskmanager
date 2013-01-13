<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="self.mainPage"/></title>


    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-style.css" type="text/css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/sliding-elements.js"></script>
</head>
<body>

<tags:menu></tags:menu>


<nav>
    <ul>
        <li><a href="new"><spring2:message code="self.mainPage.createNew"/></a>
        </li>
        <li><a><spring2:message code="self.mainPage.editExisting"/></a>
            <ul>
                <li>
                    <a><spring2:message code="self.mainPage.assessmentDescription"/></a>
                    <ul>
                        <c:choose>
                            <c:when test="${not empty storedAssessments}">
                                <c:forEach items="${storedAssessments}" var="assessment">

                                    <li>
                                        <a href="edit?assessmentID=${assessment.id}">${assessment}</a>
                                    </li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <li><a href="#">
                                    <spring2:message code="self.mainpage.noAssessmentStored"/></a></li>
                            </c:otherwise>
                        </c:choose>

                    </ul>
                </li>
                <li>
                    <a><spring2:message code="self.mainPage.evaluate"/></a>
                    <ul>
                        <c:choose>
                            <c:when test="${not empty storedAssessments}">
                                <c:forEach items="${storedAssessments}" var="assessment">

                                    <li>
                                        <a>${assessment}</a>
                                        <ul>
                                            <li>
                                                <a href="menu/ev1?assessmentID=${assessment.id}">
                                                    <spring2:message code="self.menupage.EV1description"/>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <spring2:message code="self.menupage.EV2description"/>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <spring2:message code="self.menupage.EV3description"/>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <li><a href="#">
                                    <spring2:message code="self.mainpage.noAssessmentStored"/></a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </li>
            </ul>

        </li>

        <li>
            <a><spring2:message code="self.mainPage.showExisting"/></a>
            <ul>
                <c:choose>
                    <c:when test="${not empty storedAssessments}">
                        <c:forEach items="${storedAssessments}" var="assessment">

                            <li>
                                <a href="show?assessmentID=${assessment.id}">${assessment}</a>
                            </li>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <li><a href="#">
                            <spring2:message code="self.mainpage.noAssessmentStored"/></a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </li>

    </ul>
</nav>
<div>
    <h3>
        <spring2:message code="self.mainPage.welcome"/>
    </h3>

    <p>
        <spring2:message code="self.mainPage.help.welcome"/>
    </p>
</div>

</body>
</html>