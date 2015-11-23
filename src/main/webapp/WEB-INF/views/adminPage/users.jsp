<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><spring:message code="admin.users.title"/></title>
    <link href="../../../resources/img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>

    <script type="text/javascript">

        $(document).ready(function () {
            var userId;
            $('#myModal').on('show.bs.modal', function (e) {
                var userId = $(e.relatedTarget).attr('data-id');
                $("#test").click(function (e) {
                    var dataRole = $('input:radio[name=role]:checked').val();

                    changeRole = {
                        role: dataRole,
                        userId: userId
                    };

                    var postParamForDatatable = $.ajax({
                        url: "${pageContext.request.contextPath}/admin/changeRole",
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify(changeRole),
                        success: function (response) {
                            $.ajax({
                                url: '${pageContext.request.contextPath}/admin/getAllUsers',
                                type: 'GET',
                                success: function (response) {
                                    var trHTML = '';
                                    $.each(response, function (i, user) {
                                        trHTML += "<tr><td class='lastname-col'>" + '<a href="/admin/users/userProfile/' + user.id + '">' + user.lastName + "</td>" +
                                        '<td class="firstname-col">' + user.firstName + "</td>" +
                                        '<td class="role-col">' + user.role.toString().toLowerCase() + "</td>" +
                                        '   <td class="change-col">' + '<input type="image" src="../../../resources/img/edit.jpg" class="userId" data-toggle="modal"' + 'data-id="' + user.id + '"  data-target="#myModal" width="15px" height="15px" style="margin-left: 5px; margin-top: 0px;float:left;">' + "</td>" +

                                        '<td class="status-col">' + '<a href="/admin/users/changeStatus/' + user.id + '">' + user.accountStatus + '</a>' + "</td>" +
                                        "<td class='delete-col'>" + '<form action="/admin/users/delete/' + user.id + '"><center><button class="icon-btn btn btn-primary btn-block" type="submit"><span class="glyphicon glyphicon-remove icon" aria-hidden="true"></span></button></center></form>' + "</td>" +
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
        });

    </script>
    <script src="../../../resources/js/tsort.js"></script>
    <script>
        $(document).ready(function () {
            $("#records_table").tablesort();
            var deleteLink = $("a:contains('Delete')");
        });
        jQuery(function ($) {
            $('tbody tr[data-href]').addClass('clickable').click(function () {
                window.location = $(this).attr('data-href');
            });
        });
        $.ajax({
            url: '/admin/getAllUsers',
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
                $.each(response, function (i, user) {
                    trHTML += "<tr><td class='lastname-col'>" + '<a href="/admin/users/userProfile/' + user.id + '">' + user.lastName + "</td>" +
                    '<td class="firstname-col">' + user.firstName + "</td>" +
                    '<td class="role-col">' + user.role.toString().toLowerCase() + "</td>" +
                    '   <td class="change-col">' + '<input type="image" src="../../../resources/img/edit.jpg" class="userId" data-toggle="modal"' + 'data-id="' + user.id + '"  data-target="#myModal" width="15px" height="15px" style="margin-left: 5px; margin-top: 0px;float:left;">' + "</td>" +

                    '<td class="status-col">' + '<a href="/admin/users/changeStatus/' + user.id + '">' + user.accountStatus + '</a>' + "</td>" +
                    "<td class='delete-col'>" + '<form action="/admin/users/delete/' + user.id + '"><center><button class="icon-btn btn btn-primary btn-block" type="submit"><span class="glyphicon glyphicon-remove icon" aria-hidden="true"></span></button></center></form>' + "</td>" +
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

    <div class="table-align bottom-block top-table">
        <table id="records_table" class="tbl table table-striped admin-table">
            <thead>
            <tr>
                <th class="lastname-col"><spring:message code="registration.lastName"/></th>
                <th class="firstname-col"><spring:message code="registration.firstName"/></th>
                <th class="role-col"><spring:message code="admin.users.role"/></th>
                <th class="no-sort change-col""></th>
                <th class="status-col-th"><spring:message code="admin.users.status"/></th>
                <th class="no-sort delete-col-th"></th>
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
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width:300px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <center><h4 class="modal-title" id="myModalLabel">Edit Role</h4></center>
                </div>
                <div class="modal-body">
                    <input type="radio" name="role" value="Assistant"> Assistant<Br>
                    <input type="radio" name="role" value="User"> User<Br>
                    <input type="radio" name="role" value="Admin"> Admin<Br>
                    <input type="radio" name="role" value="Professor"> Professor<Br>
                </div>
                <div class="modal-footer">
                    <center>
                        <button type="button" id="test" type="submit" class="btn btn-default"
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
