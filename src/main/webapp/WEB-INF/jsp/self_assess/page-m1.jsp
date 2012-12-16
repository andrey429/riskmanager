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
        function roundToFourDigits(number) {
            return parseFloat(parseFloat((number.toPrecision(6)))).toFixed(4)
        }
        function evaluateAndDisplayM1() {


            //формула нормировки:
            //a' = a / (1 - sum(a_not_evaluated))

            var count = 20;
            var resultVal = 0.0;
            var idxOfWeight = 0, j = 0;
            var sumOfNotEvaluatedParamWeights = 0.0;

            for (j = 0; j < count/*weights.length*/; j++) {
                var element = (document).getElementById("M1." + (j + 1));
                var element_weight = parseFloat((document).getElementById("M1." + (j + 1) + ".weight").value);
                if (element.value == -1) {
                    sumOfNotEvaluatedParamWeights += element_weight;
                }
            }

            var denominator = (1 - sumOfNotEvaluatedParamWeights);
            /*var testsum = 0.0;//used to check the normalising condition*/
            if ((sumOfNotEvaluatedParamWeights != 0) && (denominator != 0)) {
                //recalculate every weight, but on page to be displayed
                for (j = 0; j < count; j++) {
                    var element = (document).getElementById("M1." + (j + 1));
                    var element_weight = parseFloat((document).getElementById("M1." + (j + 1) + ".weight").value);
                    if (element.value != -1) { //take those parameters, which are evauated
                        resultVal += element.value * (element_weight / denominator);
                    }
                }
            }


            document.getElementById("groupParam").value = (roundToFourDigits(resultVal));


        }
        ;


    </script>


</head>
<body>


<%--<c:url var="saveUrl" value="/riskmanager/self-assessment/m1"/>--%>

<div class="accordion">
    <form:form modelAttribute="m1Model" method="POST" action="${saveUrl}">


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


        <c:forEach var="idx" begin="${0}" end="${m1Model.parameterCount - 1}" step="${1}">
            <c:set var="requirement" value="${m1Model.parameterRequirements[idx]}"/>
            <h3>
                <b>
                    M1.${idx + 1}.
                </b>

            </h3>


            <p>

                <spring2:message code="M1.${idx + 1}.label"/>
                <br>
                <br>
                <form:select path="parameterValues[${idx}]" id="M1.${idx + 1}"
                             onchange="evaluateAndDisplayM1()"><%--todo!!!priority--%>
                    <c:choose>
                        <c:when test="${requirement == 1}">
                            <form:option value="0">
                                <spring2:message code="self.label.rating.zero"/>
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


                <form:hidden id="M1.${idx + 1}.weight" path="parameterWeights[${idx}]" disabled="true"/>


            </p>
        </c:forEach>
        <h3>
            <spring2:message code="self.label.actionsOnPage"/> M1:
        </h3>

        <p>
            <spring2:message code="self.label.evaluatedGroupParameter"/> M1 :


            <form:input path="groupParamValue" id="groupParam" disabled="true"></form:input>

            <br><%--<c:set var="submit"><spring2:message code="self.label.submitValues"/> </c:set>--%>
            <c:set var="discard"><spring2:message code="self.label.discardValues"/> </c:set>
            <c:set var="evaluate"><spring2:message code="self.label.evaluateGroupParameter"/> </c:set>
            <input type="submit" title="${submit}"/>

        </p>
    </form:form>

</div>

</body>
</html>