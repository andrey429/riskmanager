<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 11.01.13
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menustyle.css" type="text/css"
          media="screen, projection"/>
    <script type="text/javascript" language="javascript"
            src="${pageContext.request.contextPath}/js/jquery.dropdownPlain.js"></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
</head>
<body>
<div class="menucontent">
    <div id="page-wrap">
        <ul class="dropdown">
            <li><a href="#">Users</a>
                <ul class="sub_menu">
                    <li><a href="#">Add</a></li>
                    <li>
                        <a href="#">List</a>
                    </li>

                </ul>
            </li>
            <li>
                <a href="#">Organisations in domain</a>
                <ul class="sub_menu">
                    <li><a href="#">Add</a></li>
                    <li>
                        <a href="#">List</a>
                    </li>

                </ul>
            </li>
            <li>
                <a href="#">Asset inventory</a>
                <ul class="sub_menu">
                    <li><a href="#">Add</a></li>
                    <li>
                        <a href="#">List</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#">Risk assessment</a>
                <ul class="sub_menu">
                    <li><a href="#">Create</a>
                        <ul>
                            <li>
                                <a href="#">AssetType</a>
                            </li>
                            <li>
                                <a href="#">Media</a>
                            </li>
                            <li>
                                <a href="#">Risk</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">View</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#">Self-assess</a>
                <ul class="sub_menu">
                    <li>
                        <a href="#">Create assessment</a>
                    </li>
                    <li>
                        <a href="#">List assessments</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#">Log out</a>
            </li>

        </ul>

    </div>
    <div class="bodycontent">
        <iframe src="/riskmanager/self-assessment/  ">

        </iframe>
    </div>
</div>
</body>
</html>