<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><spring:message code="assist.title.EditProfile"/></title>
    <link href="../../../resources/img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>
    <script>
        jQuery.ajax(url, {
            complete: function () {
                location = '/assist/created_orders';
            }
        });
    </script>
</head>

<body>
<div id="wrap">
    <div id="assistContent">
    <nav id="header">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="/assist/home"><img id="logo" alt="brand" src="../../../resources/img/logo.png"></a>
            </div>

            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element" data-toggle="dropdown"
                           href="#"><spring:message code="assist.menu.Orders"/><b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li class="drop-menu-element"><a class="menu-element-li"
                                                             href="/assist/home">
                                <spring:message code="assist.menu.Assigned"/></a></li>
                            <li class="drop-menu-element"><a class="menu-element-li"
                                                             href="/assist/created_orders">
                                <spring:message code="assist.menu.Created"/></a></li>
                        </ul>
                    </li>
                    <li><a id="editAssistProfile" class="menu-element" href="/assist/edit_profile">
                        <spring:message code="assist.menu.Profile"/></a></li>
                    <li><a class="menu-element" href="/logout"><spring:message code="assist.menu.LogOut"/></a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div id="local">
        <a href="?lang=en" class="language"><spring:message code="language.en"/></a>
        <a href="?lang=ua" class="language"><spring:message code="language.ua"/></a>
    </div>

        <div id="assistProfEdit" class="bottom-block top-block">
            <form:form id="assistProfileForm" class="form-horizontal" action="edit_profile" method="post"
                       commandName="passChangeForm">
                <div class="centralWordEdit row col-md-offset-1" colspan="2" align="center">
                    <spring:message
                            code="assist.EditProfile"/></div>

                <div class="form-group row">
                    <label class="col-md-4 control-label" for="firstName">
                        <spring:message code="registration.firstName"/></label>

                    <div class="col-md-4">
                        <form:input id="firstName" path="firstName" name="firstName" type="text" value=""
                                    class="form-control form-style" READONLY="true"/>
                    </div>
                    <div class="col-md-offset-9">
                        <form:errors path="firstName" class="regAssistErrors" id="firstName.errors"
                                     cssStyle="color: #ff0000;"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-md-4 control-label" for="lastName">
                        <spring:message code="registration.lastName"/></label>

                    <div class="col-md-4">
                        <form:input id="lastName" path="lastName" name="lastName" type="text" value=""
                                    class="form-control form-style" READONLY="true"/>
                    </div>
                    <div class="col-md-offset-9">
                        <form:errors path="lastName" class="regAssistErrors" id="lastName.errors"
                                     cssStyle="color: #ff0000;"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-md-4 control-label" for="email">
                        <spring:message code="registration.email"/></label>

                    <div class="col-md-4">
                        <form:input id="email" path="email" name="email" type="text" value=""
                                    class="form-control form-style" READONLY="true"/>
                    </div>
                    <div class="col-md-offset-9">
                        <form:errors path="email" class="regAssistErrors" id="email.errors"
                                     cssStyle="color: #ff0000;"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-md-4 control-label" for="oldPassword">
                        <spring:message code="assist.OldPassword"/></label>

                    <div class="col-md-4">
                        <form:input id="oldPassword" path="oldPassword" name="oldPassword" type="text" value=""
                                    class="form-control form-style"/>
                    </div>
                    <div class="col-md-offset-9">
                        <form:errors path="oldPassword" class="regAssistErrors" id="oldPassword.errors"
                                     cssStyle="color: #ff0000;"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-md-4 control-label" for="password">
                        <spring:message code="assist.NewPassword"/></label>

                    <div class="col-md-4">
                        <form:input id="password" path="password" name="password" type="text" value=""
                                    class="form-control form-style"/>
                    </div>
                    <div class="col-md-offset-9">
                        <form:errors path="password" class="regAssistErrors" id="password.errors"
                                     cssStyle="color: #ff0000;"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-md-4 control-label" for="confPassword">
                        <spring:message code="assist.ConfirmNewPassword"/></label>

                    <div class="col-md-4">
                        <form:input id="confPassword" path="confPassword" name="confPassword" type="text" value=""
                                    class="form-control form-style"/>
                    </div>
                    <div class="col-md-offset-9">
                        <form:errors path="confPassword" class="regAssistErrors" id="confPassword.errors"
                                     cssStyle="color: #ff0000;"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4  col-md-4">
                        <button id="change-pass-button" type="submit" value="editAssistProfile"
                                class="btn btn-primary btn-block button-style"><spring:message code="assist.Submit"/>
                        </button>
                    </div>
                </div>
            </form:form>
        </div>

    </div>

    <div class="footer">
        <div class="thick"></div>
        <div class="thin"></div>
        <div><p class="footertext"><spring:message code="login.footer"/></p></div>
    </div>
</div>
</body>
</html>