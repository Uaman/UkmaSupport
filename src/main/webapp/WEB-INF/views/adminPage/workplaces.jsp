<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><spring:message code="admin.workplaces.title"/></title>
    <link href="../../../resources/img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>
    <script src="../../../resources/js/tsort.js"></script>
    <script>
        $(document).ready(function () {
            $("#test").click(function (e) {

                var dataWorkplaces = parseInt($("#lol").val());
                addWorkplaces = {
                    workplaces: dataWorkplaces,
                    number: "${name}"
                };


                $.ajax({
                    url: "${pageContext.request.contextPath}/admin/createWorkplaces",
                    type: "POST",
                    contentType: "application/json",
                    data: JSON.stringify(addWorkplaces),
                    success: function (response) {
                        $.ajax({
                            url: '/admin/auditoriums/${name}/getWorkplaces',
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
                                $.each(sorted, function (i, workplace) {
                                    trHTML += "<tr>" +
                                            "<td class='workpl-col'>" + workplace.accessNumber + "</td>" +
                                            "<td class='icon-col'>" + '<form action="/admin/auditoriums/${name}/workplaces/delete/' + workplace.id + '"><center><button class="icon-btn btn btn-primary btn-block" type="submit"><span class="glyphicon glyphicon-remove icon" aria-hidden="true"></span></button></center></form>' + "</td>" +
                                            "</tr>";
                                });
                                $('#records_table tbody').empty();
                                $('#records_table').append(trHTML);
                            }
                        });
                    }
                });

            });
        });
        $(document).ready(function () {
            $("#records_table").tablesort();
        });

        $.ajax({
            url: '/admin/auditoriums/${name}/getWorkplaces',
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
                $.each(sorted, function (i, workplace) {
                    trHTML += "<tr >" +
                            "<td class='workpl-col'>" + workplace.accessNumber + "</td>" +
                            "<td class='icon-col'>" + '<form action="/admin/auditoriums/${name}/workplaces/delete/' + workplace.id + '"><center><button class="icon-btn btn btn-primary btn-block" type="submit"><span class="glyphicon glyphicon-remove icon" aria-hidden="true"></span></button></center></form>' + "</td>" +
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

                    <li><a class="menu-element active" href="/admin/auditoriums"><spring:message
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


    <div class="table-align top-table">
        <div>
        <table id="records_table" class="tbl-workplaces tbl table table_auditorium table-striped">
            <thead class="fixedHeader">
            <tr>
                <th class="workpl-col"><spring:message code="admin.workplaces.workplace"/><img class="icon-sort"
                                                                            src="../../../resources/img/sort15.png"
                                                                            width="8px" height="14px"></th>
                <th class="icon-col"></th>
            </tr>
            </thead>
            <tbody class="scrollContent">
            </tbody>
        </table>
        </div>
    </div>
    <center><button data-toggle="modal" data-target="#addWorkplace" class="btn btn-primary button-style"><spring:message
            code="admin.workplaces.addWorkplace"/></button></center>
    <div id="footer">
        <div class="thick"></div>
        <div class="thin"></div>
        <div><p class="footertext"><spring:message code="login.footer"/></p></div>
        <div id="localization">
            <a href="?lang=en" class="language"><spring:message code="language.en"/></a>
            <a href="?lang=ua" class="language"><spring:message code="language.ua"/></a>
        </div>
    </div>

    <div class="modal fade" id="addWorkplace" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-window">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <center><h4 class="modal-title" id="myModalLabel"><spring:message
                            code="admin.workplaces.addWorkplace"/></h4></center>
                </div>
                <div class="modal-body">
                    <center><input id="lol" type="email" class="form-control form-style form-auditorium" placeholder=""
                                   style="text-align: center;"></center>
                </div>
                <div class="modal-footer">
                    <center>
                        <button type="button" id="test" class="btn button-style" data-dismiss="modal"><p class="modal-btn-text"><spring:message
                                code="admin.workplaces.addWorkplace"/></p>
                        </button>
                    </center>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
