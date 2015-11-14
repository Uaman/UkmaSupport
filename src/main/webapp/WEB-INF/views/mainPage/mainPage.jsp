<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><spring:message code="login.title"/></title>
    <link rel="Shortcut Icon" href="" type="image/x-icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>

    <script type="text/javascript">
        function startTime() {
            var tm = new Date();
            var h = tm.getHours();
            var m = tm.getMinutes();
            var s = tm.getSeconds();
            m = checkTime(m);
            s = checkTime(s);
            document.getElementById('txt').innerHTML = h + ":" + m + ":" + s;
            t = setTimeout('startTime()', 500);
        }
        function checkTime(i) {
            if (i < 10) {
                i = "0" + i;
            }
            return i;
        }
    </script>
</head>

<body onload="startTime()">

<div id="wrap">
    <div id="header">

        <div>
            <a href="/"><img id="logo" alt="brand" src="../../../resources/img/logo.png"
                             style="width: 305px; height:65px; margin-top:11px;"/></a>
        </div>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a id="registration" class="leftWord" href="/register"><spring:message
                        code="login.registration"/></a></li>
            </ul>
        </div>
    </div>

    <div id="time">
					<span id="date">
					<script type="text/javascript">
                        var ld = new Date();
                        document.write(ld.toDateString());
                    </script></span>
        <span id="txt"></span>
    </div>
    <spring:url var="authUrl" value="/static/j_spring_security_check"/>
    <form method="post" action="${authUrl}">
        <div class="col-md-offset-4 col-md-4 vertalign">

            <form class="form-horizontal">

                <div class="form-group">
                    <div class="col-md-offset-1 col-md-10">
                        <input type="email" class="form-control form-style" id="email" name="j_username"
                               placeholder="<spring:message
                        code="login.email"/>">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-offset-1 col-md-10">
                        <input type="password" class="form-control form-style" id="password" name="j_password"
                               placeholder="<spring:message
                        code="login.password"/>">
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-md-offset-7 col-md-4">
                        <button id="login-button" type="submit" class="btn btn-primary btn-block button-style">
                            <spring:message code="login.login"/>
                        </button>
                    </div>
                </div>

                <div class="col-md-offset-1">
                    <a id="forgotpassword" href="/forgotPassword"><spring:message code="login.forgotPassword"/></a>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            </form>
        </div>
    </form>

    <div class="navbar-fixed-bottom">
        <div class="thick"></div>
        <div class="thin"></div>
        <div><p class="footertext" style="padding-bottom: 10px;"><spring:message code="login.footer"/></p></div>

        <div class="text-center">
            <a href="?lang=en" class="language"><spring:message code="language.en"/></a>
            <a href="?lang=ua" class="language"><spring:message code="language.ua"/></a>
        </div>
    </div>

</div>
</body>
</html>
