<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><spring:message code="admin.orders.mainTitle"/></title>
    <link href="../../../resources/img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>
    <script src="../../../resources/js/tsort.js"></script>
    <script>
        $.ajax({
            url: '/admin/getAssistants',
            type: 'GET',
            success: function (data) {
                $('#assistant').html('');// to clear the previous option
                $('#assistant').append($('<option>').text("Set empty").attr('value', 0));
                $.each(data, function (i, user) {
                    $('#assistant').append($('<option>').text(user.lastName).attr('value', user.id));
                });
            }
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
        $(document).ready(function () {
            var orderId;
            $('#myModal').on('show.bs.modal', function (e) {
                orderId = $(e.relatedTarget).attr('data-id');
            });

            $("#setAssist").click(function () {

                var setAssist = $("#assistant").val();
                setAssistance = {
                    assistID: setAssist,
                    orderID: orderId
                };

                var postParamForDatatable = $.ajax({
                    url: "/admin/setAssistToOrder",
                    type: "POST",
                    contentType: "application/json",
                    data: JSON.stringify(setAssistance), success: function (response) {
                        $.ajax({
                            url: '/admin/getAllOrders',
                            type: 'GET',
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
                                $.each(sorted, function (i, order) {
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
                    }
                });
            });
        });

    </script>
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
                <a href="/admin/allUsers"><img id="adm_logo" alt="brand" src="../../../resources/img/logo.png"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element" data-toggle="dropdown" href="#"><spring:message
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
                        <a class="dropdown-toggle menu-element active" data-toggle="dropdown" href="#"><spring:message
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

    <div class="table-align bottom-block top-table">
        <table id="records_table" class="tbl table table-striped admin-table">
            <thead>
            <tr>
                <th class="title-col-orders-th"><spring:message
                        code="admin.orders.title"/><img class="icon-sort" src="../../../resources/img/sort15.png"
                                                        width="8px" height="14px"></th>
                <th class="auditorium-col-orders-th"><spring:message
                        code="admin.orders.auditorium"/><img class="icon-sort" src="../../../resources/img/sort15.png"
                                                             width="8px" height="14px"></th>
                <th class="workplace-col-orders-th"><spring:message code="admin.orders.workplace"/><img
                        class="icon-sort" src="../../../resources/img/sort15.png"
                        width="8px" height="14px"></th>
                <th class="user-col-orders-th"><spring:message code="admin.orders.user"/><img class="icon-sort"
                                                                                              src="../../../resources/img/sort15.png"
                                                                                              width="8px" height="14px">
                </th>
                <th class="assistant-col-orders-th"><spring:message
                        code="admin.orders.assistant"/><img class="icon-sort" src="../../../resources/img/sort15.png"
                                                            width="8px" height="14px"></th>
                <th class="date-col-orders-th"><spring:message code="admin.orders.date"/><img class="icon-sort"
                                                                                              src="../../../resources/img/sort15.png"
                                                                                              width="8px" height="14px">
                </th>
                <th class="status-col-orders-th"><spring:message code="admin.orders.status"/><img class="icon-sort"
                                                                                                  src="../../../resources/img/sort15.png"
                                                                                                  width="8px"
                                                                                                  height="14px"></th>
                <th class="indent"></th>
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
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
        <div class="modal-dialog" style="width:300px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <center><h4 class="modal-title" id="myModalLabel1"><spring:message
                            code="admin.set.assistants"/></h4></center>
                </div>
                <div class="modal-body">
                    <select id="assistant">
                        <option value="0">it's me</option>
                    </select>
                </div>
                <div class="modal-footer">
                    <center>
                        <button type="button" id="setAssist" type="submit" class="btn btn-default"
                                data-dismiss="modal">Ok
                        </button>
                    </center>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
