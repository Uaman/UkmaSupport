<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><spring:message code="editAdmin.title"/></title>
    <link href="../../../resources/img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function() {
            if($("*[name='oldPassword_errors']").css('color') == 'rgb(255, 0, 0)') {
                $('#oldPassword').css('border-color', 'rgb(255, 0, 0)');
            }
            if($("*[name='password_errors']").css('color') == 'rgb(255, 0, 0)') {
                $('#password').css('border-color', 'rgb(255, 0, 0)');
            }
            if($("*[name='confPassword_errors']").css('color') == 'rgb(255, 0, 0)') {
                $('#confPassword').css('border-color', 'rgb(255, 0, 0)');
            }
            showTooltip($('#oldPassword'), '<spring:message code="help.oldPassword"/>');
            showTooltip($('#password'), '<spring:message code="valid.password2"/>');
            showTooltip($('#confPassword'), '<spring:message code="valid.confPassword"/>');
        });
        function showTooltip (el, text) {
            if ($('.input-error-notif').css('border-color') != 'rgb(255, 0, 0)') {
                el.tooltip({title: "" + text + "", placement: "right", trigger: "focus", animation: "true", delay: {show: 100}});
            }
        }
        jQuery.ajax(url, {
            complete: function () {
                location = '/userhome';
            }
        });
    </script>
</head>

<body>

<div id="wrap">
    <nav id="header">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="/user/userhome"><img id="logo" alt="brand" src="../../../resources/img/logo.png"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a class="menu-element" href="/user/userhome"><spring:message
                            code="admin.orders"/></a></li>
                    <li><a id="editProfile" class="menu-element" href="/user/editProfile"><spring:message
                            code="admin.edit"/></a></li>
                    <li><a class="menu-element" href="/logout"><spring:message code="admin.logout"/></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div id="profEdit" class="bottom-block">
        <form:form id="passChangeForm" class="form-horizontal" action="editProfile" method="post"
                   commandName="passChangeForm">
            <div class="centralWord row col-md-offset-1" colspan="2" align="center"><spring:message
                    code="admin.hello"/></div>
            <div class="form-group row">
                <label class="col-md-4 control-label" for="firstName"><spring:message
                        code="registration.firstName"/></label>

                <div class="col-md-4">
                    <form:input id="firstName" path="firstName" name="firstName" type="text" value=""
                                class="form-control form-style" READONLY="true"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-4 control-label" for="lastName"><spring:message
                        code="registration.lastName"/></label>
                <div class="col-md-4">
                    <form:input id="lastName" path="lastName" name="lastName" type="text" value=""
                                class="form-control form-style" READONLY="true"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-4 control-label" for="email"><spring:message
                        code="registration.email"/></label>

                <div class="col-md-4">
                    <form:input id="email" path="email" name="email" type="text" value=""
                                class="form-control form-style" READONLY="true"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-4 control-label" for="oldPassword"><spring:message
                        code="admin.oldPassword"/></label>

                <div class="col-md-4">
                    <form:input id="oldPassword" path="oldPassword" name="oldPassword" type="password" value=""
                                class="form-control form-style"/>
                </div>
                <div class="col-md-offset-9">
                    <form:errors path="oldPassword" id="oldPassword.errors" name="oldPassword_errors"
                                 cssClass="input-error-notif" element="div"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-4 control-label" for="password"><spring:message
                        code="admin.newPassword"/></label>

                <div class="col-md-4">
                    <form:input id="password" path="password" name="password" type="password" value=""
                                class="form-control form-style"/>
                </div>
                <div class="col-md-offset-9">
                    <form:errors path="password" id="password.errors" name="password_errors"
                                 cssClass="input-error-notif" element="div"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-4 control-label" for="confPassword"><spring:message
                        code="registration.confPassword"/></label>

                <div class="col-md-4">
                    <form:input id="confPassword" path="confPassword" name="confPassword" type="password" value=""
                                class="form-control form-style"/>
                </div>
                <div class="col-md-offset-9">
                    <form:errors path="confPassword" id="confPassword.errors" name="confPassword_errors"
                                 cssClass="input-error-notif" element="div"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-4  col-md-4">
                    <button id="change-pass-button" type="submit" value="editProfile"
                            class="btn btn-primary btn-block button-style"><spring:message code="admin.confirm"/>
                    </button>
                </div>
            </div>
        </form:form>
    </div>

    <div id="footer">
        <div class="thick"></div>
        <div class="thin"></div>
        <div><p class="footertext"><spring:message code="login.footer"/></p></div>
        <div id="localization">
            <a href="?lang=en" class="language"><spring:message code="language.en"/></a>
            <a href="?lang=ua" class="language"><spring:message code="language.ua"/></a>
        </div>
    </div>
</div>
</body>
</html>