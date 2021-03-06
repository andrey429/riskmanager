<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.site.com/myTlds" prefix="func" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="risk.pagetitle.assetType"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-style.css" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/sliding-elements.js"></script>

    <script type="text/javascript">
        <c:set var="riskAcceptable"><spring2:message code="values.acceptable"/></c:set>
        <c:set var="riskNotAcceptable"><spring2:message code="values.notAcceptable"/></c:set>

        var riskMatrix =${func:toDoubleArrayString(riskValueFactory.riskValueMatrix)};


        <c:choose>
        <c:when test="${storedAssetTypes ==null || storedMediaTypes ==null}">
            document.ready = function () {
                updateRiskValue();
                <c:choose>
                <c:when test="${riskIDAttribute != null}">
                    document.getElementById('idField').value = ${riskIDAttribute};
                </c:when>
                <c:otherwise>
                document.getElementById('idField').value = -1;
                </c:otherwise>
                </c:choose>
        };
        </c:when>
        </c:choose>
        function updateRiskValue() {
            var svr = document.getElementById('svrValueID').value;
            var stp = document.getElementById('stpValueID').value;
            var risk = document.getElementById('riskValue');

            if (riskMatrix[svr][stp] == 1) {
                risk.value = '${riskAcceptable}';
            } else {
                risk.value = '${riskNotAcceptable}';
            }


        }
        ;

        function submitForm() {

            var thisform = document.forms['thisform'];
            var assetTypeID = document.getElementById('selectedAssetTypeID').value;
            var mediaTypeID = document.getElementById('selectedMediaTypeID').value;

            <c:choose>
            <c:when test="${riskIDAttribute != null}">

            thisform.action =
                    "risk?assetTypeID=" + assetTypeID
                            + '&mediaTypeID=' + mediaTypeID + "&riskID=" + ${riskIDAttribute};
            </c:when>
            <c:otherwise>

            thisform.action =
                    "risk?assetTypeID=" + assetTypeID
                            + '&mediaTypeID=' + mediaTypeID;
            </c:otherwise>
            </c:choose>


            thisform.submit();
        }

    </script>
</head>
<body>

<tags:menu></tags:menu>
<c:url var="createAssetTypeURL" value="/riskmanager/risk-assessment/create/asset-type"/>
<c:url var="createMediaTypeURL" value="/riskmanager/risk-assessment/create/media-type"/>
<c:url var="createRiskURL" value="/riskmanager/risk-assessment/create/risk"/>
<c:url var="showRisksURL" value="/riskmanager/risk-assessment/show"/>

<nav>
    <ul>
        <li>
            <a><spring2:message code="risk.main.createMenu"/></a>
            <ul>
                <li>
                    <a href="${createAssetTypeURL}"><spring2:message code="menu.submenu.createAssetType"/></a>
                </li>
                <li>
                    <a href="${createMediaTypeURL}"><spring2:message code="menu.submenu.createMenuType"/></a>
                </li>
                <li>
                    <a href="${createRiskURL}"><spring2:message code="menu.submenu.createRiskDetail"/></a>
                </li>
            </ul>
        </li>
        <li><a href="${showRisksURL}"><spring2:message code="risk.main.showMenu"/></a></li>
    </ul>
</nav>


<div class="accordion">

    <c:choose>
        <c:when test="${storedMediaTypes != null && storedAssetTypes != null}">
            <form:form modelAttribute="newRisk"
                       id="thisform">

                <div hidden="true">


                    <form:input path="id" id="idField"/>


                </div>

                <h2><spring2:message code="risk.pagetitle.risk"/></h2>

                <h3>
                    <spring2:message code="risk.assetType"/>
                </h3>

                <p>
                        <%--<spring2:message code="risk.assetType"/>: ${selectedAssetTypeModel}--%>
                    <select id="selectedAssetTypeID">

                        <c:forEach items="${storedAssetTypes}" var="storedAsset">
                            <option value="${storedAsset.id}">
                                    ${storedAsset}
                            </option>
                        </c:forEach>


                    </select>
                </p>
                <h3>
                    <spring2:message code="risk.mediaType"/>
                </h3>

                <p>
                    <select id="selectedMediaTypeID">
                        <c:forEach items="${storedMediaTypes}" var="storedMediaType">
                            <option value="${storedMediaType.id}">
                                    ${storedMediaType}
                            </option>
                        </c:forEach>
                    </select>
                </p>
                </h3>
                <%--<p>
                    <spring2:message code="risk.mediaType"/>: ${selectedMediaTypeModel}
                </p>--%>
                <h3>
                    <spring2:message code="risk.riskDetail.targetProperty"/>
                </h3>

                <p>
                    <form:select path="targetProperty">
                        <form:option value="0">
                            <spring2:message code="values.property.confidentiality"/>
                        </form:option>
                        <form:option value="1">
                            <spring2:message code="values.property.integrity"/>
                        </form:option>
                        <form:option value="2">
                            <spring2:message code="values.property.availability"/>
                        </form:option>
                    </form:select>
                </p>

                <h3>
                    <spring2:message code="risk.riskDetail.threat"/>
                </h3>

                <p>
                    <form:select path="threatSource">

                        <c:forEach begin="1" end="${threatSourceFactory.numberOfThreats}" var="threatID">
                            <form:option value="${threatID}">
                                <spring2:message code="threat${threatID}"/>
                            </form:option>
                        </c:forEach>

                    </form:select>
                </p>

                <h3>
                    <spring2:message code="risk.riskDetail.threat.implementation"/>
                </h3>

                <p>
                    <form:textarea path="threatImplementation"/>
                </p>

                <h3>
                    <spring2:message code="risk.riskDetail.svr"/>
                </h3>

                <p>
                    <form:select class="levelValue" path="svrValue" id="svrValueID" onchange="updateRiskValue()">
                        <c:forEach begin="0" end="4" var="levelSVR">
                            <form:option value="${levelSVR}">
                                <spring2:message code="values.svr.${levelSVR}"/>
                            </form:option>
                        </c:forEach>
                    </form:select>
                </p>

                <h3>
                    <spring2:message code="risk.riskDetail.stp"/>
                </h3>

                <p>
                    <form:select class="levelValue" path="stpValue" id="stpValueID" onchange="updateRiskValue()">
                        <c:forEach begin="0" end="3" var="levelSTP">
                            <form:option value="${levelSTP}">
                                <spring2:message code="values.stp.${levelSTP}"/>
                            </form:option>
                        </c:forEach>
                    </form:select>
                </p>

                <h3>
                    <spring2:message code="values.risk"/>
                </h3>

                <p>
                    <input id="riskValue" disabled="true"/>
                </p>

                <h3>
                    <spring2:message code="risk.riskDetail.plannedMeasures"/>
                </h3>

                <p>
                    <form:textarea path="plannedMeasures"/>
                </p>

                <h2 onclick="submitForm()" id="savebutton">
                    <spring2:message code="risk.create.submit"/>
                </h2>

            </form:form>
        </c:when>
        <c:otherwise>

            <h1 onclick="location.href='../'">
                <spring2:message code="risk.error.notEnoughScope"/>
            </h1>
        </c:otherwise>

    </c:choose>
</div>


</body>
</html>