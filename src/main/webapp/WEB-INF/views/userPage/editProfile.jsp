<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv = "Content-Type" content="text/html; charset=utf-8" />
    <title><spring:message code="assist.editProfile"/></title>
    <link rel="Shortcut Icon" href="" type="image/x-icon" />
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen" />
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>
    <script>
        jQuery.ajax(url,{
            complete:function() { location='/userhome';}
        });
    </script>
</head>

<body>

<div id="wrap">
    <nav id="header">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href=""><img id="logo" alt="brand" src="../../../resources/img/logo.png"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a class="menu-element"  href="/userhome"><spring:message
                            code="admin.orders"/></a></li>
                    <li><a id = "editProfile" class="menu-element" href="/editProfile"><spring:message
                            code="admin.edit"/></a></li>
                    <li><a class="menu-element" href="/logout"><spring:message code="admin.logout"/></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <%--<div>--%>
    <%--<p id="hello">Hello, you may edit your e-mail or your password here.</p>--%>
    <%--</div>--%>

    <div id="profEdit" class="bottom-block top-block">
        <form:form id="passChangeForm" action="editProfile" method="post" commandName="passChangeForm">
            <table border="0">
                <tr>
                    <td class="centralWord" colspan="2" align="center"><spring:message
                            code="admin.hello"/></td>
                </tr>
                <tr>
                    <td><label class="labels"><spring:message code="registration.firstName"/></label></td>
                    <td><form:input id="firstName" path="firstName" name="firstName" type="text" value=""
                                    class="form-control form-style" READONLY="true"/></td>
                    <td><form:errors path="firstName" class="regErrors" id="firstName.errors"
                                     cssStyle="color: #ff0000;"/></td>
                </tr>
                <tr>
                    <td><label class="labels"><spring:message code="registration.lastName"/></label></td>
                    <td><form:input id="lastName" path="lastName" name="lastName" type="text" value=""
                                    class="form-control form-style" READONLY="true"/></td>
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
                    <td><form:input path = "oldPassword" type="password" value=""
                                    class="form-control form-style"/></td>
                    <td><form:errors path = "oldPassword" class="regErrors" id="oldPassword.errors"
                                     cssStyle="color: #ff0000;"/></td>
                </tr>
                <tr>
                    <td><label class="labels"><spring:message code="admin.newPassword"/></label></td>
                    <td><form:input path = "password" type="password" value=""
                                    class="form-control form-style"/></td>
                    <td><form:errors path = "password" class="regErrors" id="password.errors"
                                     cssStyle="color: #ff0000;"/></td>
                </tr>
                <tr>
                    <td><label class="labels"><spring:message code="registration.confPassword"/></label></td>
                    <td><form:input path = "confPassword" type="password" value=""
                                    class="form-control form-style"/></td>
                    <td><form:errors path = "confPassword" class="regErrors" id="confPassword.errors"
                                     cssStyle="color: #ff0000;"/></td>
                </tr>
            </table>
            <div class="form-group">
                <div class="col-md-offset-7  col-md-4">
                    <button id="change-pass-button" type="submit" value="editProfile"
                            class="btn btn-primary btn-block button-style"><spring:message code="admin.confirm"/>
                    </button>
                </div>
            </div>
        </form:form>
    </div>
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