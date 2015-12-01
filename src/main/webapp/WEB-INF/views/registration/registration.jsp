<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><spring:message code="registration.title"/></title>
    <link href="../../../resources/img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            showTooltip($('#firstName'), '<spring:message code="help.firstName"/>');
            showTooltip($('#lastName'), '<spring:message code="help.lastName"/>');
            showTooltip($('#email'), '<spring:message code="valid.email"/>');
            showTooltip($('#password'), '<spring:message code="valid.password2"/>');
            showTooltip($('#confPassword'), '<spring:message code="valid.confPassword"/>');
        });
        function showTooltip (el, text) {
            if ($('.input-error').css('border-color') != 'rgb(255, 0, 0)') {
                el.tooltip({title: "" + text + "", placement: "right", trigger: "focus", animation: "true", delay: {show: 100}});
            }
        }
    </script>
</head>

<body>
<div id="wrap">
    <nav id="header">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="/"><img id="logo" alt="brand" src="../../../resources/img/logo.png"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a id="login" class="menu-element" href="/login"><spring:message code="registration.login"/></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div id="ContentRegister">
    <div id="mainRegister">
        <form:form id="userForm" class="form-horizontal" action="register" method="post" commandName="userForm">
            <div class="RegistrationWord centralWord row col-md-offset-1" colspan="2" align="center"><spring:message
                    code="registration.hello"/></div>
            <div id="reg-top-input" class="form-group row">
                <label class="col-md-4 control-label" for="firstName"><spring:message
                        code="registration.firstName"/></label>

                <div class="col-md-4">
                    <form:input id="firstName" path="firstName" name="firstName" type="text" value=""
                                class="form-control form-style" cssErrorClass="form-control form-style input-error"/>
                </div>
                <div class="col-md-offset-11">
                    <form:errors path="firstName" id="firstName.errors"
                                 cssClass="input-error-notif" element="div"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-4 control-label" for="lastName"><spring:message
                        code="registration.lastName"/></label>

                <div class="col-md-4">
                    <form:input id="lastName" path="lastName" name="lastName" type="text" value=""
                                class="form-control form-style" cssErrorClass="form-control form-style input-error"/>
                </div>
                <div class="col-md-offset-11">
                    <form:errors path="lastName" id="lastName.errors"
                                 cssClass="input-error-notif" element="div"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-4 control-label" for="email"><spring:message
                        code="registration.email"/></label>

                <div class="col-md-4">
                    <form:input id="email" path="email" name="email" type="text" value=""
                                class="form-control form-style" cssErrorClass="form-control form-style input-error"/>
                </div>
                <div class="col-md-offset-11">
                    <form:errors path="email" id="email.errors"
                                 cssClass="input-error-notif" element="div"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-4 control-label" for="password"><spring:message
                        code="registration.password"/></label>

                <div class="col-md-4">
                    <form:input id="password" path="password" name="password" type="password" value=""
                                class="form-control form-style" cssErrorClass="form-control form-style input-error"/>
                </div>
                <div class="col-md-offset-11">
                    <form:errors path="password" id="password.errors"
                                 cssClass="input-error-notif" element="div"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-4 control-label" for="confPassword"><spring:message
                        code="registration.confPassword"/></label>

                <div class="col-md-4">
                    <form:input id="confPassword" path="confPassword" name="confPassword" type="password" value=""
                                class="form-control form-style" cssErrorClass="form-control form-style input-error"/>
                </div>
                <div class="col-md-offset-11">
                    <form:errors path="confPassword" id="confPassword.errors"
                                 cssClass="input-error-notif" element="div"/>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-offset-4 col-md-4">
                    <button id="register-button" type="submit" value="register"
                            class="btn btn-primary btn-block button-style"><spring:message
                            code="registration.register"/>
                    </button>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-4 col-md-6">
                    <div class="error" id="form-error"><form:errors class="" path="dateOfEntry" id="dateOfEntry.errors"
                                                                    cssClass="input-error-notif" element="div"/></div>
                </div>
            </div>
        </form:form>
    </div>
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
