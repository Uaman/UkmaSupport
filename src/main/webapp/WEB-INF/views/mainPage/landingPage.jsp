<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><spring:message code="title.MainPage"/></title>
    <link href="../../../resources/img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="../../../resources/css/mainPage.css" type="text/css" media="screen"/>
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>
</head>
<body>
<div id="wrap">
    <nav id="header">

        <div class="container-fluid">
            <div class="navbar-header">
                <a href="/index"><img id="logo" alt="brand" src="../../../resources/img/logo.png"></a>
            </div>

            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a id="login" class="menu-element" href="/">
                        <spring:message code="login.login"/></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div id="mainImage"><img src="../../../resources/img/mainImg.jpg" alt=""/></div>

    <div id="mainInfo">
        <p id="pMainInfo"><span id="Ukma_support"><spring:message code="mainPage.UKMASupport"/></span> <spring:message
                code="mainPage.TEXT"/>
        </p>
        <button id="main_registr_button" type="submit" value="register"
                class="btn btn-primary btn-block button-style">
            <a href="/register"><spring:message code="mainPage.registration"/></a>
        </button>
    </div>

    <div id="footerMain">
        <div class="thick"></div>
        <div class="thin"></div>
        <div><p class="footertext"><spring:message code="login.footer"/></p></div>
        <div id="mainLocal">
            <a href="?lang=en" class="language"><spring:message code="language.en"/></a>
            <a href="?lang=ua" class="language"><spring:message code="language.ua"/></a>
        </div>
    </div>
</div>
</body>
</html>

