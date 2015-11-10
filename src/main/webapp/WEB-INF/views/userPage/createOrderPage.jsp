<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="Shortcut Icon" href="" type="image/x-icon"/>
    <link rel="stylesheet" href="../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../resources/css/main.css" type="text/css" media="screen"/>
    <title></title>
</head>
<body>
<div id="header">

    <div>
        <a href="/"><img id="logo" alt="brand" src="../resources/img/logo.png"/></a>
    </div>

    <div class="collapse navbar-collapse">

    </div>
</div>

<form:form id="userForm" action="register" method="post" commandName="userForm">
    <div class="form-group">
        <div class="col-md-offset-4 col-md-4">
            <input type="text" class="form-control form-style" id="title" name="title" placeholder="title">
        </div>
    </div>
</form:form>>
<br>
<br>

<form class="form-inline">
    <div class="col-md-offset-4 col-md-4">
        <div class="form-group">
            <select class="form-control">

            </select>
        </div>

        <div class="form-group">
            <select class="form-control">

            </select>
        </div>
    </div>
</form>

<div class="form-group">
    <div class="col-md-offset-4 col-md-4">
    <textarea class="form-control" style="resize: none" rows="4" cols="50" name="description" placeholder="description">
    </textarea>
    </div>
</div>

<div class="form-group">
    <div class="col-md-offset-4 col-md-4">
        <button class="button-bar" type="submit">Create order</button>
    </div>
</div>

<div class="navbar-fixed-bottom">
    <div class="thick"></div>
    <div class="thin"></div>
    <div><p class="footertext"> © 2015 All Rights Reserved</p></div>
</div>
</body>
</html>