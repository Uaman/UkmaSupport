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
            url: '/admin/get_all_orders',
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
                        trHTML += "<tr><td>" + '<a href="/addComment/' + order.id + '">' + order.title + '</a>' + "</td>" +
                        '   <td>' + order.auditorium + "</td>" +
                        '   <td>' + order.workplace_access_num + "</td>" +
                        '   <td>' + order.userId + "</td>" +
                        '   <td>' + order.assistantLastName + "</td>" +
                        '   <td>' + formatDate(new Date(order.createdAt), '%d.%M.%Y %H:%m') + "</td>" +
                        '   <td>' + order.status + "</td></tr>";
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
            <div class="navbar-header" style="width: 290px;">
                <a href=""><img id="logo" alt="brand" src="../../../resources/img/logo.png"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
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
                    <li><a id="orders" class="menu-element active" href="/admin/allOrders"><spring:message
                            code="admin.orders"/></a></li>
                    <li><a id="editProfile" class="menu-element" href="/admin/editProfile"><spring:message
                            code="admin.edit"/></a></li>
                    <li><a class="menu-element" href="/logout"><spring:message code="admin.logout"/></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="table-align bottom-block top-table">
        <table id="records_table" class="tbl table table-striped">
            <thead>
            <tr>
                <th class="no-sort"><spring:message code="admin.orders.title"/></th>
                <th><spring:message code="admin.orders.auditorium"/></th>
                <th><spring:message code="admin.orders.workplace"/></th>
                <th><spring:message code="admin.orders.user"/></th>
                <th><spring:message code="admin.orders.assistant"/></th>
                <th><spring:message code="admin.orders.date"/></th>
                <th><spring:message code="admin.orders.status"/></th>
            </tr>
            </thead>
        </table>
    </div>
    <div class="footer">
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
