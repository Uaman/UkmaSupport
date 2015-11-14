<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><spring:message code="editAdmin.title"/></title>
    <link rel="Shortcut Icon" href="" type="image/x-icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>
    <script>
        jQuery(function ($) {
            $('tbody tr[data-href]').addClass('clickable').click(function () {
                window.location = $(this).attr('data-href');
            });
        });
    </script>
</head>

<body>

<div id="wrap">
    <nav id="header">
        <div class="container-fluid">
            <div class="navbar-header" style="width: 290px;">
                <a href=""><img id="logo" alt="brand" src="../../../resources/img/logo.png"
                                style="width: 305px; height:65px; margin-top:11px;"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element" data-toggle="dropdown" href="#"><spring:message
                                code="admin.auditoriums"/><b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a class="menu-element-li" href="/createAuditorium"><spring:message
                                    code="admin.addAuditorium"/></a></li>
                            <li><a class="menu-element-li" href="/auditoriums"><spring:message
                                    code="admin.auditoriums"/></a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element" data-toggle="dropdown" href="#"><spring:message
                                code="admin.users"/><b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a class="menu-element-li" href="/all"><spring:message code="admin.allUsers"/></a></li>
                            <li><a class="menu-element-li" href="/users"><spring:message code="admin.users"/></a></li>
                            <li><a class="menu-element-li" href="/assistants"><spring:message
                                    code="admin.assistants"/></a></li>
                            <li><a class="menu-element-li" href="/professors"><spring:message
                                    code="admin.professors"/></a></li>
                            <li><a class="menu-element-li" href="/blocked"><spring:message code="admin.blocked"/></a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element" data-toggle="dropdown" href="#"><spring:message
                                code="admin.orders"/><b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a class="menu-element-li" href="/allOrders"><spring:message
                                    code="admin.allOrders"/></a></li>
                            <li><a class="menu-element-li" href="/completedOrders"><spring:message
                                    code="admin.completedOrders"/></a></li>
                            <li><a class="menu-element-li" href="/uncompletedOrders"><spring:message
                                    code="admin.uncompletedOrders"/></a></li>
                        </ul>
                    </li>
                    <li><a id="editProfile" class="menu-element active" href="/editAdminProfile"><spring:message
                            code="admin.edit"/></a></li>
                    <li><a class="menu-element" href="/"><spring:message code="admin.logout"/></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div id="profEdit">
        <form:form id="passChangeForm" action="editAdminProfile" method="post" commandName="passChangeForm">
            <table border="0">
                <tr>
                    <td class="centralWord" colspan="2" align="center"><spring:message
                            code="admin.hello"/></td>
                </tr>
                <tr>
                    <td><label class="labels"><spring:message code="registration.firstName"/></label></td>
                    <td><form:input id="firstName" path="firstName" name="firstName" type="text" value=""
                                    class="form-control form-style"/></td>
                    <td><form:errors path="firstName" class="regErrors" id="firstName.errors"
                                     cssStyle="color: #ff0000;"/></td>
                </tr>
                <tr>
                    <td><label class="labels"><spring:message code="registration.lastName"/></label></td>
                    <td><form:input id="lastName" path="lastName" name="lastName" type="text" value=""
                                    class="form-control form-style"/></td>
                    <td><form:errors path="lastName" class="regErrors" id="lastName.errors"
                                     cssStyle="color: #ff0000;"/></td>
                </tr>
                <tr>
                    <td><label class="labels"><spring:message code="registration.email"/></label></td>
                    <td><form:input id="email" path="email" name="email" type="text" value=""
                                    class="form-control form-style" READONLY="true"/></td>
                    <td><form:errors path="email" class="regErrors" id="email.errors" cssStyle="color: #ff0000;"/></td>
                </tr>
                <tr>
                    <td><label class="labels"><spring:message code="admin.oldPassword"/></label></td>
                    <td><form:input path="oldPassword" type="password" value=""
                                    class="form-control form-style"/></td>
                    <td><form:errors path="oldPassword" class="regErrors" id="oldPassword.errors"
                                     cssStyle="color: #ff0000;"/></td>
                </tr>
                <tr>
                    <td><label class="labels"><spring:message code="admin.newPassword"/></label></td>
                    <td><form:input path="password" type="password" value=""
                                    class="form-control form-style"/></td>
                    <td><form:errors path="password" class="regErrors" id="password.errors"
                                     cssStyle="color: #ff0000;"/></td>
                </tr>
                <tr>
                    <td><label class="labels"><spring:message code="registration.confPassword"/></label></td>
                    <td><form:input path="confPassword" type="password" value=""
                                    class="form-control form-style"/></td>
                    <td><form:errors path="confPassword" class="regErrors" id="confPassword.errors"
                                     cssStyle="color: #ff0000;"/></td>
                </tr>
            </table>
            <div class="form-group">
                <div class="col-md-offset-7  col-md-4">
                    <button id="change-pass-button" type="submit" value="editAdminProfile"
                            class="btn btn-primary btn-block button-style"><spring:message code="admin.confirm"/>
                    </button>
                </div>
            </div>
        </form:form>
    </div>

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
