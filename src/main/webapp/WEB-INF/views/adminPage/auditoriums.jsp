<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><spring:message code="admin.auditoriums.title"/></title>
    <link href="../../../resources/img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>
    <script src="../../../resources/js/tsort.js"></script>


    <script>
        function populateTable() {
            $.ajax({
                url: '/admin/getAuditoriums',
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
                    $.each(sorted, function (i, auditorium) {
                        if (auditorium.userId == 0) {
                            trHTML += "<tr>" +
                            '<td width="200px">' + '<a href="/admin/auditoriums/' + auditorium.number + '">' + auditorium.number + '</a>' + "</td>" +
                            '<td>' + '<select  id="myselect" data-hidden="true" class="assist select-style" data-id="' + auditorium.number + '" onchange="func($(this))"><option class="current-assist" value="' + auditorium.userId + '">' + auditorium.assistantName + '</option></select>' + "</td>" +
                            "<td>" + '<button data-id="' + auditorium.id + '" class="icon-btn btn btn-primary btn-block" type="submit"><span class="glyphicon glyphicon-remove icon" aria-hidden="true"></span></button>' + "</td>" +
                            "</tr>";
                        } else {
                            trHTML += "<tr>" +
                            '<td width="200px">' + '<a href="/admin/auditoriums/' + auditorium.number + '">' + auditorium.number + '</a>' + "</td>" +
                            '<td>' + '<select class="assist select-style" data-hidden="true" data-id="' + auditorium.number + '" onchange="func($(this))"><option class="current-assist" value="' + auditorium.userId + '">' + auditorium.assistantName + '</option></select>' + "</td>" +
                            "<td>" + '<button data-id="' + auditorium.id + '" class="icon-btn btn btn-primary btn-block" type="submit"><span class="glyphicon glyphicon-remove icon" aria-hidden="true"></span></button>' + "</td>" +
                            "</tr>";
                        }
                    });
                    $('#records_table tbody').empty();
                    $('#records_table').append(trHTML);
                    $.ajax({
                        url: '/admin/getAssistants',
                        type: 'GET',
                        success: function (data) {
                            $('.assist').append($('<option>').text('Set empty').attr('value', 0));
                            $.each(data, function (i, user) {
                                $('.assist').append($('<option>').text(user.firstName + ' ' + user.lastName).attr('value', user.id));
                            });
                        }
                    });
                    $('.icon-btn').click(function () {
                        $(this).parents('tr').hide();
                        $.ajax({
                            url: '/admin/auditoriums/delete/' + $(this).attr("data-id"),
                            type: "GET",
                            success: function (response) {
                            }
                        });
                    });
                }
            });
        }

        $(document).ready(function () {
            populateTable();
            $("#records_table").tablesort();
        });

        var auditoriumId;
        var setAssistant;

        function func(el) {

            auditoriumId = el.attr('data-id');
            setAssistant = el.val();

            setAssistance = {
                assistID: setAssistant,
                auditoriumID: auditoriumId
            };

            $.ajax({
                url: "${pageContext.request.contextPath}/admin/setAssistToAuditorium",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(setAssistance), success: function (response) {
                    populateTable();
                }
            });
        }

        $(document).ready(function () {
            $("#test").click(function (e) {

                var dataAuditorium = $("#auditoriumName").val();

                addAuditorium = {
                    auditorium: dataAuditorium
                };

                $.ajax({
                    url: "${pageContext.request.contextPath}/admin/createAuditoriums",
                    type: "POST",
                    contentType: "application/json",
                    data: JSON.stringify(addAuditorium),
                    success: function (response) {
                        populateTable();
                    }
                });
            });
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
            <table id="records_table" class="tbl table table-auditorium table-striped">
                <thead class="fixedHeader">
                <tr>
                    <th width="187px" style="vertical-align: middle;"><spring:message
                            code="admin.auditoriums.number"/><img class="icon-sort"
                                                                  src="../../../resources/img/sort15.png" width="8px"
                                                                  height="14px"></th>
                    <th width="230px"><spring:message code="admin.auditoriums.assistantName"/> <img class="icon-sort"
                                                                                                    src="../../../resources/img/sort15.png"
                                                                                                    width="8px"
                                                                                                    height="14px"></th>
                    <th width="50px" class="no-sort"></th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <center><a data-toggle="modal" data-target="#addAuditorium"
                       class="btn btn-primary button-style"><spring:message
                    code="admin.addAuditorium"/></a></center>
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
    <div class="modal fade" id="addAuditorium" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-window">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <center><h4 class="modal-title" id="myModalLabel"><spring:message code="admin.AddAuditorium"/></h4>
                    </center>
                </div>
                <div class="modal-body">
                    <center><input type="email" id="auditoriumName" class="form-control form-style form-auditorium"
                                   placeholder=""
                                   style="text-align: center;"></center>
                </div>
                <div class="modal-footer">
                    <center>
                        <button type="button" id="test" class="btn button-style" data-dismiss="modal">
                            <p class="modal-btn-text"><spring:message code="admin.AddAuditorium"/></p>
                        </button>
                    </center>
                </div>
            </div>

        </div>
    </div>


</div>
</body>
</html>
