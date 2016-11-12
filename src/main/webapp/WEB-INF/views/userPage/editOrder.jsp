<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="assist.title.EditOrder"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="../../../resources/img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>

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
                    <li><a class="menu-element" href="/user/userhome"> <spring:message
                            code="admin.orders"/></a></li>
                    <li><a id="editProfile" class="menu-element" href="/user/editProfile"><spring:message
                            code="admin.edit"/></a></li>
                    <li><a class="menu-element" href="/logout"><spring:message code="admin.logout"/></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="col-md-offset-4 col-md-8 vertalign bottom-block">
        <form:form class="form-horizontal" id="edit" action='/user${pageContext.request.contextPath}/editOrder/save'
                   method="post" commandName="editOrder">
            <div class="form-group">
                <div class="col-md-6" display="inline-block">
                    <input type="text" class="form-control form-style" id="title" name="title" value="${title}"
                           path="title">
                </div>
                <div class="error" id="title-error"><form:errors path="title" class="" id="title.errors"/></div>
            </div>

            <div class="form-group">
                <div class="col-md-3">
                    <label class="label-style" for="auditorium"><spring:message
                            code="user.auditoriums.title"/>:</label>
                    <form:input id="auditorium" path="auditorium" name="auditorium" type="text" value="${auditorium}"
                                class="form-control select-style" READONLY="true"/>
                </div>
                <div class="col-md-3">
                    <label class="label-style" for="workplace_access_num"><spring:message
                            code="user.workplace.number"/>:</label>
                    <form:input id="workplace_access_num" path="workplace_access_num" name="workplace_access_num"
                                type="text" value=""
                                class="form-control select-style" READONLY="true"/>
                </div>

                <div class="error" id="workplace-error"><form:errors path="workplace_access_num" class=""
                                                                     id="workplace_access_num.errors"/></div>

            </div>

            <div class="form-group">
                <div class="col-md-6">
                    <textarea class="col-md-12" rows="5" id="content" name="content" path="content"
                            >${content}</textarea>
                </div>
                <div class="error" id="content-error"><form:errors path="content" class="" id="content.errors"/></div>
            </div>
            <input type="hidden" name="id" value="${id}"/>

            <div class="form-group">
                <div class="col-md-6">
                    <button id="btn-create-order" type="submit" class="btn btn-primary btn-block"><spring:message
                            code="user.order.edit"/></button>
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

