<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><spring:message code="admin.Title.Reports"/></title>
    <link href="../../../resources/img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="../../../resources/css/calendar.css" type="text/css" media="screen"/>
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>
    <script src="../../../resources/js/tsort.js"></script>
    <script src="../../../resources/js/calendar.js"></script>
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
        jQuery(function ($) {
            $('tbody tr[data-href]').addClass('clickable').click(function () {
                window.location = $(this).attr('data-href');
            });
        });

        /*------------------------------------------------*/
        $.ajax({
            url: '/admin/getAllOrders',
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
                    trHTML += "<tr><td class='title-col-orders'>" + '<a href="/addComment/' + order.id + '">' + order.title.substr(0, 15) + '</a>' + "</td>" +
                    '   <td class="auditorium-col-orders">' + order.auditorium + "</td>" +
                    '   <td class="workplace-col-orders">' + order.workplace_access_num + "</td>" +
                    '   <td class="user-col-orders">' + order.userLastName + "</td>" +
                    '   <td class="assistant-col-orders">' + order.assistantLastName + "</td>" +
                    '   <td class="change-col">' + '<input type="image" src="../../../resources/img/edit.jpg" class="assistId" data-toggle="modal"' + 'data-id="' + order.id + '"  data-target="#myModal" width="15px" height="15px" style="margin-left: 5px; margin-top: 0px;float:left;">' + "</td>" +
                    '   <td class="date-col-orders">' + formatDate(new Date(order.createdAt), '%d.%M.%Y %H:%m') + "</td>" +
                    '   <td class="status-col-orders">' + order.status + "</td></tr>";
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
                <a href="/"><img id="adm_logo" alt="brand" src="../../../resources/img/logo.png"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element active" data-toggle="dropdown" href="#"><spring:message
                                code="admin.reports"/><b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a class="menu-element-li" href="/admin/report_all"><spring:message
                                    code="admin.All_Stats"/></a>
                            </li>
                            <li><a class="menu-element-li" href="/admin/report_assist"><spring:message
                                    code="admin.Assist_Stats"/></a>
                            </li>
                            <li><a class="menu-element-li" href="/admin/report_auditorium"><spring:message
                                    code="admin.Auditorium_Stats"/></a></li>
                        </ul>
                    </li>
                    <li><a class="menu-element" href="/admin/auditoriums"><spring:message
                            code="admin.auditoriums"/></a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element" data-toggle="dropdown" href="#"><spring:message
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

    <!--------------------------------------->
    <div class="options">
        <div id="From">
            <label class="label-style">
                <span class="from_label"><spring:message code="admin.report.dateFrom"/></span>
                <input class="from_field" autocomplete="off" id="date_from" name="date_from" value="26.11.2015"
                       type="text">
            </label>
        </div>

        <div id="To">
            <label class="label-style">
                <span class="to_label"><spring:message code="admin.report.dateTo"/></span>
                <input class="to_field" autocomplete="off" id="date_to" name="date_to" value="26.11.2015" type="text">
            </label>
        </div>
    </div>
    <!--------------------------------------->

    <div class="ReportsTable table-align bottom-block">
        <table id="records_table" class="Report_table tbl table table-striped user-table order-table">
            <thead>
            <tr>
                <th class="no-sort title-col"><spring:message code="admin.orders.title"/><img class="icon-sort"
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

    <!--------------------------------------->
    <div class="col-md-offset-7 top-block">
        <form id="download_report_button" class="form-horizontal" method="get" action="/admin/">
            <div class="col-md-offset-7 col-md-4">
                <button id="download_all" type="submit" class="btn btn-primary btn-block"><spring:message
                        code="admin.download"/></button>
            </div>
        </form>
    </div>
    <!--------------------------------------->

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