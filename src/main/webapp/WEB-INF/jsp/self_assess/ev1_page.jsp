<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.site.com/myTlds" prefix="func" %>
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
        function roundToFourDigits(number) {
            return parseFloat(parseFloat((number.toPrecision(6)))).toFixed(4)
        }

        //static variables
        var m_weights = ${func:toDoubleArrayString(ev1ValueFactory.parameterWeights)};
        var m_counts = ${func:toSingleArrayString(ev1ValueFactory.counts)};
        var m_required = ${func:toDoubleArrayString(ev1ValueFactory.requirement)};

        /*
         @param m_idx number of group parameter, i.e. '1' for M1, '5' for M5, '10' for M10
         * */
        function updateGroupValue(m_idx) {


            //формула нормировки:
            //a' = a / (1 - sum(a_not_evaluated))

            var count = m_counts[m_idx - 1];
            var resultVal = 0.0;
            var groupParamValue = 0;
            var sumOfNotEvaluatedParamWeights = 0.0;

            var parameter_weights = m_weights[m_idx - 1];


            for (var j = 0; j < count/*weights.length*/; j++) {
                var element = (document).getElementById("M" + m_idx + "." + (j + 1));
                if (element.value == -1) {
                    sumOfNotEvaluatedParamWeights += parameter_weights[j];
                }
            }

            var denominator = (1 - sumOfNotEvaluatedParamWeights);

            if (denominator != 0) {
                //recalculate every weight, but on page to be displayed
                for (j = 0; j < count; j++) {
                    var element = (document).getElementById("M" + m_idx + "." + (j + 1));

                    if (element.value != -1) { //use only evaluated parameters
                        resultVal += element.value * (parameter_weights[j] / denominator);
                    }
                }
            }

            document.getElementById("m" + m_idx + "GroupParam").value = (roundToFourDigits(resultVal));

        }


        function updateM9Value() {
            var count = m_counts[8];
            /*M9*/
            var resultVal = 1.0;
            /*searchin minimum in [0.0, 1.0]*/
            var groupParamValue = 0;
            var parameter_weights = m_weights[8];


            for (var j = 0; j < count; j++) {
                var element = (document).getElementById("M9." + (j + 1));

                if (element.value != -1) { //use only evaluated parameters
                    resultVal = Math.min(resultVal, element.value);
                }
            }
            document.getElementById("m9GroupParam").value = (roundToFourDigits(resultVal));
        }


    </script>


</head>
<body>


<%--<c:url var="saveurl" value="/riskmanager/self-assessment/ev1"/>--%>

<div class="accordion">

    <form:form modelAttribute="ev1FBO" method="POST">


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


        <c:set var="countArray" value="${ev1ValueFactory.counts}"/>

        <c:forEach var="groupIdx" begin="${0}" end="${9}" step="${1}">
            <c:set var="requirementArray" value="${ev1ValueFactory.requirement[groupIdx]}"/>
            <%--<c:set var="parameterValuesArray" value="${ev1FBO.parameterValues[groupIdx]}"/>--%>

            <c:forEach var="idx" begin="${0}" end="${countArray[groupIdx] - 1}" step="${1}">
                <c:set var="requirement" value="${requirementArray[idx]}"/>

                <h3>
                    <b>
                        M${groupIdx + 1}.${idx + 1}.
                    </b>

                </h3>


                <p>

                    <spring2:message code="M${groupIdx + 1}.${idx + 1}.label"/>
                    <br>
                    <br>
                    <form:select path="parameterValues[${groupIdx}][${idx}]" id="M${groupIdx + 1}.${idx + 1}"

                                 onchange="${groupIdx != 8 ? 'updateGroupValue(${groupIdx + 1})' : 'updateM9Value()'}"

                            >

                        <c:choose>
                            <c:when test="${requirement == 1}">
                                <form:option value="0">


                                </form:option>
                                <form:option value="0.25">
                                    <spring2:message code="self.label.rating.partlyLow"/>
                                </form:option>

                                <form:option value="0.5">
                                    <spring2:message code="self.label.rating.partlyMedium"/>
                                </form:option>

                                <form:option value="0.75">
                                    <spring2:message code="self.label.rating.partlyHigh"/>
                                </form:option>
                                <form:option value="1">
                                    <spring2:message code="self.label.rating.one"/>
                                </form:option>
                                <form:option value="-1">
                                    <spring2:message code="self.label.notAssignedValueLabel"/>
                                </form:option>
                            </c:when>

                            <c:otherwise>
                                <form:option value="-1">
                                    <spring2:message code="self.label.notAssignedValueLabel"/>
                                </form:option>
                                <form:option value="1">
                                    <spring2:message code="self.label.rating.one"/>
                                </form:option>

                            </c:otherwise>
                        </c:choose>
                    </form:select>

                    <c:choose>
                        <c:when test="${requirement == 1}">
                            <b><spring2:message code="self.label.isRequired"/></b>
                        </c:when>
                        <c:otherwise>
                            <i><spring2:message code="self.label.isNotRequired"/></i>
                        </c:otherwise>
                    </c:choose>
                    <br><br>


                </p>
            </c:forEach>
            <h3>
                <spring2:message code="self.label.actionsOnPage"/> M${groupIdx + 1}:
            </h3>

            <p>
                <spring2:message code="self.label.evaluatedGroupParameter"/> M${groupIdx + 1} :


                <form:input path="mGroupValues[${groupIdx}]" id="m${groupIdx + 1}GroupParam"
                            disabled="true"></form:input>

                <br><%--<c:set var="submit"><spring2:message code="self.label.submitValues"/> </c:set>--%>
                <c:set var="discard"><spring2:message code="self.label.discardValues"/> </c:set>
                <c:set var="evaluate"><spring2:message code="self.label.evaluateGroupParameter"/> </c:set>

            </p>
        </c:forEach>


        <h3>EV1</h3>

        <p>
            <form:input path="ev1Value" id="ev1Value"
                        disabled="true"></form:input>
        </p>

        <input type="submit"/>
    </form:form>


</div>

</body>
</html>