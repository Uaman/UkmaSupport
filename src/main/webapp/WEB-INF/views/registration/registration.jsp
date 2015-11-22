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
                    <li><a id="login" class="menu-element" href="/"><spring:message code="registration.login"/></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div id="ContentRegister">
    <div id="mainRegister">
        <form:form id="userForm" class="form-horizontal" action="register" method="post" commandName="userForm">
            <div class="RegistrationWord centralWord row col-md-offset-1" colspan="2" align="center"><spring:message
                    code="registration.hello"/></div>
            <div class="form-group">
                <div class="col-md-offset-4 col-md-6">
                    <div class="error" id="form-error"><form:errors class="" path="dateOfEntry"
                                                                    id="dateOfEntry.errors"/></div>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-4 control-label" for="firstName"><spring:message
                        code="registration.firstName"/></label>

                <div class="col-md-4">
                    <form:input id="firstName" path="firstName" name="firstName" type="text" value=""
                                class="form-control form-style"/>
                </div>
                <div class="col-md-offset-9">
                    <form:errors path="firstName" class="regErrors" id="firstName.errors"
                                 cssStyle="color: #ff0000;"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-4 control-label" for="lastName"><spring:message
                        code="registration.lastName"/></label>

                <div class="col-md-4">
                    <form:input id="lastName" path="lastName" name="lastName" type="text" value=""
                                class="form-control form-style"/>
                </div>
                <div class="col-md-offset-9">
                    <form:errors path="lastName" class="regErrors" id="lastName.errors"
                                 cssStyle="color: #ff0000;"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-4 control-label" for="email"><spring:message
                        code="registration.email"/></label>

                <div class="col-md-4">
                    <form:input id="email" path="email" name="email" type="text" value=""
                                class="form-control form-style"/>
                </div>
                <div class="col-md-offset-9">
                    <form:errors path="email" class="regErrors" id="email.errors"
                                 cssStyle="color: #ff0000;"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-4 control-label" for="password"><spring:message
                        code="registration.password"/></label>

                <div class="col-md-4">
                    <form:input id="password" path="password" name="password" type="password" value=""
                                class="form-control form-style"/>
                </div>
                <div class="col-md-offset-9">
                    <form:errors path="password" class="regErrors" id="password.errors"
                                 cssStyle="color: #ff0000;"/>
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
                    <form:errors path="confPassword" class="regErrors" id="confPassword.errors"
                                 cssStyle="color: #ff0000;"/>
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
