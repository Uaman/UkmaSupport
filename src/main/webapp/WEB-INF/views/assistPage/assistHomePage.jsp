<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><spring:message code="assist.homeTitle"/></title>
    <link rel="Shortcut Icon" href="" type="image/x-icon"/>
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
                        return pad(date.getUTCHours());
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
            url: 'all_my_orders',
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
                    trHTML += "<tr><td>" + order.title + "</td>" +
                    '   <td>' + order.workplace_access_num + "</td>" +
                    '   <td>' + formatDate(new Date(order.createdAt), '%d.%M.%Y   %H:%m:%s') + "</td></tr>";
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
                <a href="/assistHome"><img id="logo" alt="brand" src="../../../resources/img/logo.png"></a>
            </div>

            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element" data-toggle="dropdown" href="#"><spring:message
                                code="assist.menuOrders"/><b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li class="drop-menu-element"><a class="menu-element-li"
                                                             href="/assistHome"><spring:message
                                    code="assist.menuAssigned"/></a></li>
                            <li class="drop-menu-element"><a class="menu-element-li"
                                                             href="/assistUserhome"><spring:message
                                    code="assist.menuUserOrders"/></a></li>
                            <li class="drop-menu-element"><a class="menu-element-li"
                                                             href="/createAssistOrder"><spring:message
                                    code="assist.menuAddOrder"/></a></li>
                        </ul>
                    </li>

                    <li><a id="editAssistProfile" class="menu-element" href="/editAssistProfile"><spring:message
                            code="assist.editProfile"/></a></li>
                    <li><a class="menu-element" href="/logout"><spring:message code="assist.logOut"/></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div>
        <p id="hello" class="top-block"><spring:message code="assist.hello"/></p>
    </div>

    <div>
        <form class="form-horizontal" method="get" action="/myComplOrders">
            <button id="assist_Compl" class="btn btn-primary btn-block"><spring:message
                    code="assist.CompletedOrders"/></button>
        </form>
    </div>

    <div>
        <form class="form-horizontal" method="get" action="/myUncomplOrders">
            <button id="assist_Uncompl" class="btn btn-primary btn-block"><spring:message
                    code="assist.UncompletedOrders"/></button>
        </form>
    </div>

    <div class="table-align">
        <table id="records_table" class="tbl table table-striped table-hover ">
            <thead>
            <tr>
                <th class="no-sort"><spring:message code="assist.orders.title"/></th>
                <th class="no-sort"><spring:message code="assist.orders.auditorium"/></th>
                <th><spring:message code="assist.orders.date"/></th>
            </tr>
            </thead>
        </table>
    </div>

    <div class="footer">
        <div class="thick"></div>
        <div class="thin"></div>
        <div><p class="footertext"><spring:message code="login.footer"/></p></div>
        <div class="text-center">
            <a href="?lang=en" class="language"><spring:message code="language.en"/></a>
            <a href="?lang=ua" class="language"><spring:message code="language.ua"/></a>
        </div>
    </div>
</div>
</body>
</html>
