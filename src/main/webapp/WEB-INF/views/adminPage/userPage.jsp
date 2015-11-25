<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><spring:message code="admin.user.title"/></title>
    <link href="../../../resources/img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
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
                <a href=""><img id="logo" alt="brand" src="../../../resources/img/logo.png"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a class="menu-element" href="/admin/auditoriums"><spring:message
                            code="admin.auditoriums"/></a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element active" data-toggle="dropdown" href="#"><spring:message
                                code="admin.users"/><b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a class="menu-element-li" href="/admin/allUsers"><spring:message
                                    code="admin.allUsers"/></a></li>
                            <li><a class="menu-element-li" href="/admin/users"><spring:message code="admin.users"/></a>
                            </li>
                            <li><a class="menu-element-li" href="/admin/assistants"><spring:message
                                    code="admin.assistants"/></a></li>
                            <li><a class="menu-element-li" href="/admin/professors"><spring:message
                                    code="admin.professors"/></a></li>
                            <li><a class="menu-element-li" href="/admin/blockedUsers"><spring:message
                                    code="admin.blocked"/></a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element" data-toggle="dropdown" href="#"><spring:message
                                code="admin.orders"/><b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a class="menu-element-li" href="/admin/allOrders"><spring:message
                                    code="admin.orders"/></a></li>
                            <li><a class="menu-element-li" href="/admin/myOrders"><spring:message
                                    code="admin.myOrders"/></a>
                            </li>
                        </ul>
                    </li>
                    <li><a id="editProfile" class="menu-element" href="/admin/editProfile"><spring:message
                            code="admin.edit"/></a></li>
                    <li><a class="menu-element" href="/logout"><spring:message code="admin.logout"/></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div id="profEdit" class="top-block bottom-block">
        <form:form id="passChangeForm" class="form-horizontal" action="/admin/allUsers" method="get"
                   commandName="passChangeForm">
            <div class="centralWord row col-md-offset-1" colspan="2" align="center"><spring:message
                    code="admin.users.account"/></div>
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
                <label class="col-md-4 control-label" for="role"><spring:message
                        code="admin.users.role"/></label>

                <div class="col-md-4">
                    <form:input id="role" path="role" name="role" type="text" value=""
                                class="form-control form-style text-lowercase" READONLY="true"/>
                </div>
            </div>
        </form:form>
    </div>
    <div class="table-align bottom-block top-table padding-tom">
        <table id="records_table" class="tbl table table-striped admin-table" style="margin-top: -170px;">
            <thead>
            <tr>
                <th class="no-sort title-col-orders-th" style="margin-left: 20px;width:50px;"><spring:message
                        code="admin.orders.title"/><img class="icon-sort" src="../../../resources/img/sort15.png"
                                                        width="8px" height="14px"></th>
                <th class="auditorium-col-orders-th" style="width: 70px;"><spring:message
                        code="admin.orders.auditorium"/><img class="icon-sort" src="../../../resources/img/sort15.png"
                                                             width="8px" height="14px"></th>
                <th class="workplace-col-orders-th" style="width: 70px;"><spring:message code="admin.orders.workplace"/><img
                        class="icon-sort" src="../../../resources/img/sort15.png" width="8px" height="14px"></th>
                <th class="assistant-col-orders" style="width: 50px;"><spring:message
                        code="admin.orders.assistant"/><img class="icon-sort" src="../../../resources/img/sort15.png"
                                                            width="8px" height="14px"></th>
                <th class="date-col-orders-th" style="width: 50px;"><spring:message code="admin.orders.date"/><img
                        class="icon-sort" src="../../../resources/img/sort15.png" width="8px" height="14px"></th>
                <th class="status-col-orders-th" style="width: 50px;"><spring:message code="admin.orders.status"/><img
                        class="icon-sort" src="../../../resources/img/sort15.png" width="8px" height="14px"></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${orders}" var="orders">
                <tr data-href="#">
                    <td>${fn:substring(orders.title,0,15)}</td>
                    <td>${orders.auditorium}</td>
                    <td>${orders.workplace_access_num}</td>
                    <td>${orders.assistantLastName}</td>
                    <td>${orders.createdAt}</td>
                    <td>${orders.status}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
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
