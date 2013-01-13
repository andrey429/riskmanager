<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>



<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>


<c:set var="usersURL" value="/riskmanager/riskmanager/persons/"/>
<c:set var="addUsersURL" value="/riskmanager/riskmanager/persons/add"/>
<c:set var="listUsersURL" value="/riskmanager/riskmanager/persons/"/>

<c:set var="organisationsURL" value="/riskmanager/riskmanager/organisations/"/>
<c:set var="addOrganisationsURL" value="/riskmanager/riskmanager/organisations/add"/>
<c:set var="listOrganisationsURL" value="/riskmanager/riskmanager/organisations/"/>

<c:set var="assetInventoryURL" value="/riskmanager/riskmanager/assets/"/>
<c:set var="addAssetInventoryURL" value="/riskmanager/riskmanager/assets/add"/>
<c:set var="listAssetInventoryURL" value="/riskmanager/riskmanager/assets/"/>
<c:set var="queryAssetInventoryURL" value="/riskmanager/riskmanager/query/"/>


<c:set var="riskAssessmentURL" value="/riskmanager/riskmanager/risk-assessment/"/>
<c:set var="createRiskAssessmentURL" value="${riskAssessmentURL}create"/>
<c:set var="createAssetTypeRiskAssessmentURL" value="${createRiskAssessmentURL}/asset-type"/>
<c:set var="createMediaTypeRiskAssessmentURL" value="${createRiskAssessmentURL}/media-type"/>
<c:set var="createRiskRiskAssessmentURL" value="${createRiskAssessmentURL}/risk"/>
<c:set var="listRiskAssessmentURL" value="/riskmanager/riskmanager/risk-assessment/"/>

<c:set var="selfAssessmentURL" value="/riskmanager/riskmanager/self-assessment/"/>
<c:set var="listSelfAssessmentURL" value="/riskmanager/riskmanager/self-assessment/"/>
<c:set var="createAssessmentSelfAssessmentURL" value="${selfAssessmentURL}new"/>

<c:set var="logoutURL" value="/riskmanager/riskmanager/auth/logout"/>
<c:set var="rootURL" value="/riskmanager/riskmanager/"/>

<nav>
    <ul>
        <li><a href="${rootURL}">Главная</a></li>
        <li><a href="${usersURL}"><spring2:message code="menu.users"/></a>
            <ul>
                <li><a href="${addUsersURL}"><spring2:message code="menu.submenu.add"/></a></li>
                <li>
                    <a href="${listUsersURL}"><spring2:message code="menu.submenu.list"/></a>
                </li>

            </ul>
        </li>
        <li>
            <a href="${organisationsURL}"><spring2:message code="menu.organisations"/></a>
            <ul>
                <li><a href="${addOrganisationsURL}"><spring2:message code="menu.submenu.add"/></a></li>
                <li>
                    <a href="${listOrganisationsURL}"><spring2:message code="menu.submenu.list"/></a>
                </li>


            </ul>
        </li>
        <li>
            <a href="${assetInventoryURL}"><spring2:message code="menu.assetInventory"/></a>
            <ul>
                <li><a href="${addAssetInventoryURL}"><spring2:message code="menu.submenu.add"/></a></li>
                <li>
                    <a href="${listAssetInventoryURL}"><spring2:message code="menu.submenu.list"/></a>
                </li>
                <li>
                    <a href="${queryAssetInventoryURL}">Выборка активов</a>
                </li>
            </ul>
        </li>
        <li>
            <a href="${riskAssessmentURL}"><spring2:message code="menu.riskAssessment"/></a>
            <ul>
                <li><a <%--href="${createRiskAssessmentURL}"--%>href="#"><spring2:message code="menu.submenu.add"/></a>
                    <ul>
                        <li>
                            <a href="${createAssetTypeRiskAssessmentURL}"><spring2:message code="menu.submenu.createAssetType"/></a>
                        </li>
                        <li>
                            <a href="${createMediaTypeRiskAssessmentURL}"><spring2:message code="menu.submenu.createMenuType"/></a>
                        </li>
                        <li>
                            <a href="${createRiskRiskAssessmentURL}"><spring2:message code="menu.submenu.createRiskDetail"/></a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="${listRiskAssessmentURL}"><spring2:message code="menu.submenu.list"/></a>

                </li>
            </ul>
        </li>
        <li>
            <a href="${selfAssessmentURL}"><spring2:message code="menu.selfAssessment"/></a>
            <ul>
                <li>
                    <a href="${createAssessmentSelfAssessmentURL}"><spring2:message code="menu.submenu.createAssessment"/></a>
                </li>
                <li>
                    <a href="${listSelfAssessmentURL}"><spring2:message code="menu.subment.showAssessments"/></a>
                </li>
            </ul>
        </li>
        <li>
            <a href="${logoutURL}"><spring2:message code="menu.logout"/></a>
        </li>

    </ul>

</nav>