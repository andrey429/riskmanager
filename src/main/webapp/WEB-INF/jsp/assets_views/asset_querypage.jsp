<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring2:message code="label.assetListingPageTitle"></spring2:message></title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/sliding-elements.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-style.css" type="text/css"/>


</head>
<body>


<spring2:message code="label.optionMenuValueNotSelected" var="optionMenuValueNotSelected"/>
<spring2:message code="label.assetPaymentBusinessProcess" var="paymentProcessLabel"/>
<spring2:message code="label.assetInformationBusinessProcess" var="informationalProcessLabel"/>
<spring2:message code="label.confidentiality" var="confidentialityLabel"/>
<spring2:message code="label.integrity" var="integrityLabel"/>
<spring2:message code="label.availability" var="availabilityLabel"/>
<spring2:message code="label.assetRequirements" var="assetRequirementsLabel"/>
<spring2:message code="label.queryFilter" var="queryFilter"/>


<c:url var="processRequest" value="/riskmanager/query"/>
<c:url var="resetURL" value="/riskmanager/query/reset"/>
<tags:menu></tags:menu>

<form:form method="post" action="${processRequest}" modelAttribute="queryAttribute" id="thisform">
    <nav>
        <ul>
            <li><a>По организации</a>
                <ul>
                    <c:choose>
                        <c:when test="${not empty existingOrganisations}">
                            <c:forEach items="${existingOrganisations}" var="existingOrganisation">
                                <li>
                                    <a>
                                        <form:radiobutton path="organisation" value="${existingOrganisation.id}"/>
                                            ${existingOrganisation}
                                    </a></li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <li><a>Организации не добавлены</a></li>
                        </c:otherwise>
                    </c:choose>

                </ul>
            </li>
            <li><a>По владельцу</a>
                <ul>
                    <c:choose>
                        <c:when test="${not empty existingPersons}">
                            <c:forEach items="${existingPersons}" var="existingPerson">
                                <li>
                                    <a>
                                        <form:radiobutton path="person" value="${existingPerson.id}"/>
                                            ${existingPerson}
                                    </a></li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <li><a>Сотрудники отсутствуют</a></li>
                        </c:otherwise>
                    </c:choose>

                </ul>
            </li>
            <li><a>По типу бизнес процесса</a>
                <ul>
                    <li><a>
                        <form:radiobutton path="businessProcessType" value="${null}"/>${optionMenuValueNotSelected}
                    </a></li>
                    <li><a>
                        <form:radiobutton path="businessProcessType" value="${1}"/>${paymentProcessLabel}
                    </a></li>
                    <li><a>
                        <form:radiobutton path="businessProcessType" value="${2}"/>${informationalProcessLabel}
                    </a></li>

                </ul>
            </li>
            <li><a>По свойствам ИБ</a>
                <ul>
                    <li>
                        <a><form:checkbox path="requiresConfidentiality"/>Конфиденциальность</a>
                    </li>
                    <li>
                        <a><form:checkbox path="requiresIntegrity"/>Целостность</a>
                    </li>
                    <li>
                        <a><form:checkbox path="requiresAvailability"/>Доступность</a>
                    </li>
                </ul>
            </li>
                <%--<li><a>Сброс критериев</a></li>--%>
            <li><a onclick="document.forms['thisform'].submit()">Применить фильтр</a></li>
        </ul>
    </nav>
</form:form>

<%--results--%>
<c:choose>
    <c:when test="${not empty queriedAssetsList}">

        <h3 class="row">
            <h2 class="cell"><spring2:message code="label.assetName"/></h2>

            <h2 class="cell"><spring2:message code="label.assetDescription"/></h2>

            <h2 class="cell"><spring2:message code="label.assetOwner"/></h2>

            <h2 class="cell"><spring2:message code="label.assetRequirements"/></h2>

            <h2 class="cell"><spring2:message code="label.assetBusinessProcessType"/></h2>

            <h2 class="cell"><spring2:message code="label.assetLocation"/></h2>
        </h3>
        <c:forEach items="${queriedAssetsList}" var="asset">
            <c:url var="editUrl" value="/riskmanager/assets/edit?id=${asset.id}"/>
            <c:url var="deleteUrl" value="/riskmanager/assets/delete?id=${asset.id}"/>
            <h3 class="row">
                <h3 class="cell">
                    <i class="addIcon" onclick="iconHandler('${deleteUrl}')">(-)</i>
                    <a href="${editUrl}">${asset.name}</a></h3>

                <h3 class="cell">${asset.description}</h3>

                <h3 class="cell">${asset.personOwner}</h3>

                <h3 class="cell">
                    <c:if test="${asset.requiresConfidentiality}">C</c:if>
                    &nbsp;
                    <c:if test="${asset.requiresIntegrity}">I</c:if>
                    &nbsp;
                    <c:if test="${asset.requiresAvailability}">A</c:if>
                </h3>

                <h3 class="cell">
                    <c:choose>
                        <c:when test="${asset.businessProcessType == 1}">
                            <spring2:message code="label.assetPaymentBusinessProcess"/>
                        </c:when>
                        <c:when test="${asset.businessProcessType == 2}">
                            <spring2:message code="label.assetInformationBusinessProcess"/>
                        </c:when>
                    </c:choose>
                </h3>

                <h3 class="cell">${asset.assetLocation}</h3>
            </h3>


        </c:forEach>
    </c:when>
    <c:otherwise>
        <div class="accordion">
            <h1>
                Нет активов, удовлетворяющих заданным критериям
            </h1>
        </div>

    </c:otherwise>
</c:choose>
<%--
<div class="accordion">



        <h3>
            <spring2:message code="label.queryFilter"/>
        </h3>

        <p>
            <spring2:message code="help.queryBuildQuery"/>
        </p>

        <h3>
                <spring2:message code="label.queryOrganisationFilter"/></td>


            <form:select path="organisation">
                <form:option value="${assetQuery.organisation.id}">
                    <c:choose>
                        <c:when test="${assetQuery.organisation != null}">
                            ${assetQuery.organisation.organisationName}
                        </c:when>
                        <c:otherwise>
                            <spring2:message code="label.optionMenuValueNotSelected"/>
                        </c:otherwise>
                    </c:choose>
                </form:option>
                <c:forEach items="${existingOrganisations}" var="existingOrganisation">
                    <form:option value="${existingOrganisation.id}"
                                 label="${existingOrganisation.organisationName}"/>
                </c:forEach>
            </form:select>
        </h3>

        <p>
            <spring2:message code="help.queryByOrganisation"/>
        </p>

        <h3>
            <spring2:message code="label.queryPersonFilter"/>

            <form:select path="person">
                <form:option value="${assetQuery.person.id}">
                    <c:choose>
                        <c:when test="${assetQuery.person != null}">
                            ${assetQuery.person}
                        </c:when>
                        <c:otherwise>
                            <spring2:message code="label.optionMenuValueNotSelected"/>
                        </c:otherwise>
                    </c:choose>
                </form:option>
                <c:forEach items="${existingPersons}" var="existingPerson">
                    <form:option value="${existingPerson.id}"
                                 label="${existingPerson}"/>
                </c:forEach>
            </form:select>
        </h3>

        <p>
            <spring2:message code="help.queryByPerson"/>
        </p>

        <h3>
            <spring2:message code="label.queryBusinessProcessFilter"/>

            <form:select path="businessProcessType">
                <form:option value="${null}" label="${optionMenuValueNotSelected}"/>
                <form:option value="${1}" label="${paymentProcessLabel}"/>
                <form:option value="${2}" label="${informationalProcessLabel}"/>
            </form:select>
        </h3>

        <p>
            <spring2:message code="help.queryByProcess"/>
        </p>

        <h3><form:checkbox path="requiresConfidentiality" label="${confidentialityLabel}"/>

        </h3>

        <p><spring2:message code="help.queryConfidential"/></p>

        <h3>
            <form:checkbox path="requiresIntegrity" label="${integrityLabel}"/>
        </h3>

        <p><spring2:message code="help.queryIntegrity"/></p>

        <h3>
            <form:checkbox path="requiresAvailability" label="${availabilityLabel}"/>
        </h3>

        <p><spring2:message code="help.queryAvailability"/></p>


        <h3>
            <spring2:message code="label.queryFilter"/><br><br>
            <input type="submit" value="${queryFilter}"/><br><br>
            <a href="${resetURL}"><spring2:message code="label.queryReset"/> </a>
        </h3>

        <p>
            <spring2:message code="help.querySubmitReset"/>
        </p>
    </div>

</form:form>

&lt;%&ndash;todo query results&ndash;%&gt;

<h3 class="row">
    <h2 class="cell"><spring2:message code="label.assetName"/></h2>

    <h2 class="cell"><spring2:message code="label.assetDescription"/></h2>

    <h2 class="cell"><spring2:message code="label.assetOwner"/></h2>

    <h2 class="cell"><spring2:message code="label.assetRequirements"/></h2>

    <h2 class="cell"><spring2:message code="label.assetBusinessProcessType"/></h2>

    <h2 class="cell"><spring2:message code="label.assetLocation"/></h2>
</h3>
<c:forEach items="${queriedAssetsList}" var="asset">
<c:url var="editUrl" value="/riskmanager/assets/edit?id=${asset.id}"/>
<c:url var="deleteUrl" value="/riskmanager/assets/delete?id=${asset.id}"/>
<h3 class="row">
    <h3 class="cell">
        <i class="addIcon" onclick="iconHandler('${deleteUrl}')">(-)</i>
        <a href="${editUrl}">${asset.name}</a></h3>

    <h3 class="cell">${asset.description}</h3>

    <h3 class="cell">${asset.personOwner}</h3>

    <h3 class="cell">
        <c:if test="${asset.requiresConfidentiality}">C</c:if>
        &nbsp;
        <c:if test="${asset.requiresIntegrity}">I</c:if>
        &nbsp;
        <c:if test="${asset.requiresAvailability}">A</c:if>
    </h3>

    <h3 class="cell">
        <c:choose>
            <c:when test="${asset.businessProcessType == 1}">
                <spring2:message code="label.assetPaymentBusinessProcess"/>
            </c:when>
            <c:when test="${asset.businessProcessType == 2}">
                <spring2:message code="label.assetInformationBusinessProcess"/>
            </c:when>
        </c:choose>
    </h3>

    <h3 class="cell">${asset.assetLocation}</h3>
</h3>



--%>


</body>
</html>