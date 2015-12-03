<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Home</title>
    <link href="../../../resources/img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>
    <script src="../../../resources/js/tsort.js"></script>
    <script>

        $(document).ready(function () {
            $("#records_table").tablesort();
        });
        function formatDate(date, fmt) {
            function pad(value) {
                return (value.toString().length < 2) ? '0' + value : value;
            }

            return fmt.replace(/%([a-zA-Z])/g, function (_, fmtCode) {
                switch (fmtCode) {
                    case 'Y':
                        return date.getUTCFullYear();
                    case 'M':
                        return pad(date.getUTCMonth() + 1);
                    case 'd':
                        return pad(date.getUTCDate());
                    case 'H':
                        return pad(date.getUTCHours() + 2);
                    case 'm':
                        return pad(date.getUTCMinutes());
                    case 's':
                        return pad(date.getUTCSeconds());
                    default:
                        throw new Error('Unsupported format code: ' + fmtCode);
                }
            });
        }

        $.ajax({
            url: '${pageContext.request.contextPath}/user/allUserOrders',
            type: 'GET',
            success: function (response) {
                var sorted = response.sort(function (a, b) {
                    if (a.status < b.status) {
                        return 1;
                    }
                    if (a.status > b.status) {
                        return -1;
                    }
                    return 0;
                });
                var trHTML = '';
                $.each(sorted, function (i, order) {
                    if (order.status == "not done") {
                        trHTML += "<tr>"
                                + "<td class='title-col-user'>" + '<a href="/addComment/' + order.id + '">' + order.title.substring(0,15) + '</a>' + "</td>" +
                                "<td class='auditorium-col'>" + order.auditorium + "</td>" +
                                "<td class='workplace-col'>" + order.workplace_access_num + "</td>" +
                                "<td class='status-col-user'>" + order.status + "</td>" +
                                "<td class='date-col'>" + formatDate(new Date(order.createdAt), '%d.%M.%Y %H:%m') + "</td>" +
                                "<td class='btn-col'>" + '<form action="/user${pageContext.request.contextPath}/editOrder/' + order.id + '"><button class="icon-btn btn btn-primary btn-block" type="submit"><span class="glyphicon glyphicon-pencil icon" aria-hidden="true"></span></button></form>' + "</td>" +
                                "<td class='btn-col'>" + '<form action="/user${pageContext.request.contextPath}/delete/' + order.id + '"><button class="icon-btn btn btn-primary btn-block" type="submit"><span class="glyphicon glyphicon-remove icon" aria-hidden="true"></span></button></form>' + "</td>" +
                                "</tr>";
                    } else {
                        trHTML += "<tr>" +
                                "<td class='title-col-user'>" + '<a href="/addComment/' + order.id + '">' + order.title.substring(0,15) + '</a>' + "</td>" +
                                "<td class='auditorium-col'>" + order.workplace_id + "</td>" +
                                "<td class='workplace-col'>" + order.workplace_access_num + "</td>" +
                                "<td class='status-col-user'>" + order.status + "</td>" +
                                "<td class='date-col'>" + formatDate(new Date(order.createdAt), '%d.%M.%Y %H:%m') + "</td>" +
                                "<td class='btn-col'>" + "  " + "</td>" +
                                "<td class='btn-col'>" + '<form action="/user${pageContext.request.contextPath}/delete/' + order.id + '"><button class="icon-btn btn btn-primary btn-block" type="submit"><span class="glyphicon glyphicon-remove icon" aria-hidden="true"></span></button></form>' + "</td>" +
                                "</tr>";
                    }
                });
                $('#records_table tbody').empty();
                $('#records_table').append(trHTML);
            }
        });
    </script>
</head>

<body>

<div id="wrap">
    <nav id="header">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="/user/userhome"><img id="logo" alt="brand" src="../../../resources/img/logo.png"
                                 style="width: 305px; height:65px; margin-top:11px;"></a>
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
    <div class="top-block col-md-6">
        <p id="hello"><spring:message
                code="user.hello"/> ${currentUser.firstName}</p>
    </div>

    <div class="col-md-offset-7 top-block">
        <form id="add-order-form" class="form-horizontal" method="get" action="/user/createOrder">
            <div class="col-md-offset-7 col-md-4">
                <button id="Adm_Button" type="submit" class="btn btn-primary btn-block"><spring:message
                        code="user.order"/></button>
            </div>
        </form>
    </div>
    <div class="table-align bottom-block">
        <table id="records_table" class="tbl table table-striped user-table order-table">
            <thead>
            <tr>
                <th class="title-col"><spring:message code="admin.orders.title"/><img class="icon-sort"
                                                                                              src="../../../resources/img/sort15.png"
                                                                                              width="8px" height="14px">
                </th>
                <th class="auditorium-col"><spring:message code="admin.orders.auditorium"/><img class="icon-sort"
                                                                                                src="../../../resources/img/sort15.png"
                                                                                                width="8px"
                                                                                                height="14px"></th>
                <th class="workplace-col"><spring:message code="assist.order.workplace"/><img class="icon-sort"
                                                                                              src="../../../resources/img/sort15.png"
                                                                                              width="8px" height="14px">
                </th>
                <th class="status-col"><spring:message code="admin.orders.status"/><img class="icon-sort"
                                                                                        src="../../../resources/img/sort15.png"
                                                                                        width="8px" height="14px"></th>
                <th class="date-col"><spring:message code="admin.orders.date"/><img class="icon-sort"
                                                                                    src="../../../resources/img/sort15.png"
                                                                                    width="8px" height="14px"></th>
                <th class="btn-col"></th>
                <th class="btn-col"></th>
            </tr>
            </thead>
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