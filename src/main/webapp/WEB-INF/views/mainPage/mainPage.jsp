<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><spring:message code="login.title"/></title>
    <link rel="Shortcut Icon" href="" type="image/x-icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>

    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>
</head>

<body>

<div id="wrap">
    <div id="header">
        <div>
            <a href="/"><img id="logo" alt="brand" style="margin-left: 15px;"
                             src="../../../resources/img/logo.png"/></a>
        </div>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a id="registration" class="leftWord" href="/register"><spring:message
                        code="login.registration"/></a></li>
            </ul>
        </div>
    </div>

    <spring:url var="authUrl" value="/static/j_spring_security_check"/>
    <div class="col-md-offset-4 col-md-4 vertalign bottom-block" id="auth-block">

        <form class="form-horizontal" style="margin-top: 130px;" method="post" action="${authUrl}">

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
                <div class="col-md-offset-1 col-md-10">
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

    <div class="footer">
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
