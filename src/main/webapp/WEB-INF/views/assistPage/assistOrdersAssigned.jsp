<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><spring:message code="assist.title.AssignedOrders"/></title>
    <link href="../../../resources/img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>
    <script src="../../../resources/js/tsort.js"></script>
    <script>
        $(document).ready(function () {
            $("#records_table").tablesort();
            var deleteLink = $("a:contains('Delete')");
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
        jQuery(function ($) {
            $('tbody tr[data-href]').addClass('clickable').click(function () {
                window.location = $(this).attr('data-href');
            });
        });
        $.ajax({
            url: '/assist/get_all_assigned_orders',
            type: 'GET',
            data: {
                text: $("#sel2").val()
            },
            success: function (response) {
                var sorted = response.sort(function (a, b) {
                    if (a.createdAt < b.createdAt) {
                        return 1;
                    }
                    if (a.createdAt > b.createdAt) {
                        return -1;
                    }
                    return 0;
                });
                var trHTML = '';
                $.each(response, function (i, order) {
                    trHTML += "<tr><td class='title-col'>" + '<a href="/addComment/' + order.id + '">' + order.title + '</a>' + "</td>" +
                    '   <td class="auditorium-col">' + order.auditorium + "</td>" +
                    '   <td class="workplace-col">' + order.workplace_access_num + "</td>" +
                    '   <td class="status-col-ord">' + '<a href="${pageContext.request.contextPath}/assist/mark_done/' + order.id + '">'  + order.status + '<a>'+ "</td>" +
                    '   <td class="date-col">' + formatDate(new Date(order.createdAt), '%d.%M.%Y %H:%m') + "</td>" +
                    "</tr>";
                });
                $('#records_table tbody').empty();
                $('#records_table').append(trHTML);
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
                            <a class="dropdown-toggle menu-element active" data-toggle="dropdown" href="#">
                                <spring:message code="assist.menu.Orders"/><b
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

        <div>
            <p id="helloAssist"><spring:message code="assist.hello"/>, ${
                    currentUser.firstName}!</p>
        </div>

        <div class="table-align bottom-block">
            <table id="records_table" class="tbl table table-striped admin-table assist-order-table">
                    <thead>
                        <tr>
                            <th class="title-col"><spring:message code="assist.orders.title"/><img class="icon-sort" src="../../../resources/img/sort15.png"
                                                                                                           width="8px" height="14px"></th>
                            <th class="auditorium-col"><spring:message code="assist.orders.auditorium"/><img class="icon-sort" src="../../../resources/img/sort15.png"
                                                                                                             width="8px" height="14px"></th>
                            <th class="workplace-col-admin-th"><spring:message code="assist.orders.workplace"/><img class="icon-sort" src="../../../resources/img/sort15.png"
                                                                                                                    width="8px" height="14px"></th>
                            <th class="status-col-ord"><spring:message code="admin.orders.status"/><img class="icon-sort" src="../../../resources/img/sort15.png"
                                                                                                        width="8px" height="14px"></th>
                            <th class="date-col"><spring:message code="admin.orders.date"/><img class="icon-sort" src="../../../resources/img/sort15.png"
                                                                                                width="8px" height="14px"></th>
                            <th class="indent"></th>
                        </tr>
                    </thead>
            </table>
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
