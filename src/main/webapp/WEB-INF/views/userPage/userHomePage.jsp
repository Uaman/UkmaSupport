<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv = "Content-Type" content="text/html; charset=utf-8" />
    <title>Home</title>
    <link rel="Shortcut Icon" href="" type="image/x-icon" />
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen" />
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="jquery.tablesort.js"></script>

    <script>
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
        jQuery( function($) {
            $('tbody tr[data-href]').addClass('clickable').click( function() {
                window.location = $(this).attr('data-href');
            });
        });

        $.ajax({
            url: 'allUserOrders',
            type: 'GET',
            data:{
                text : $("#sel2").val()
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
                $.each( sorted, function (i, order) {
                    trHTML +=' <tbody>'+'<tr><td>' + order.title + '</td><td>' + order.workplace_access_num+'</td><td>' + formatDate(new Date(order.createdAt), '%d.%M.%Y   %H:%m:%s')+'<tbody>' ;
                });
                $('#records_table tbody').empty();
                $('#records_table th.title').data('sortBy', function(th, td, tablesort) {
                    return App.People.get(td.text());
                });
                $('#records_table').append(trHTML);
            }
        });

        function getUncoplited()
        {
            $.ajax({
            url: 'unComplited',
                    type: 'GET',
                data:{
            text : $("#sel2").val()
        },
            success: function (response) {
                var trHTML = '';
                $.each(response, function (i, order) {
                    trHTML +=' <tbody>'+'<tr><td>' + order.title + '</td><td>' + order.workplace_access_num+'</td><td>' + formatDate(new Date(order.createdAt), '%d.%M.%Y   %H:%m:%s')+'<tbody>' ;
                });
                $('#records_table tbody').empty();
                $('#records_table').append(trHTML);
            }
        });
        }
        function getCoplited()
        {
            $.ajax({
                url: 'allComplited',
                type: 'GET',
                data:{
                    text : $("#sel2").val()
                },
                success: function (response) {
                    var trHTML = '';

                    $.each(response, function (i, order) {
                        trHTML +=' <tbody>'+'<tr><td>' + order.title + '</td><td>' + order.workplace_access_num+'</td><td>' + formatDate(new Date(order.createdAt), '%d.%M.%Y   %H:%m:%s')+'<tbody>' ;
                    });
                    $('#records_table tbody').empty();
                    $('#records_table').append(trHTML);
                }
            });
        }

    </script>

</head>

<body>

<div id="wrap">
    <nav id="header">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="/"><img id="logo" alt="brand" src="../../../resources/img/logo.png" style="width: 305px; height:65px; margin-top:11px;"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element" data-toggle="dropdown" href="#"> My orders<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li class="drop-menu-element"><a class="menu-element-li" href="javascript:getCoplited();" >Complited orders</a></li>
                            <li class="drop-menu-element"><a class="menu-element-li" href="javascript:getUncoplited();">Uncomplited orders</a></li>
                        </ul>
                    </li>
                    <li><a id = "editProfile" class="menu-element" href="/editProfile">Edit profile</a></li>
                    <li><a class="menu-element" href="#">Log out</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div>
        <p id="hello">Hello, User!</p>
    </div>

    <div class="table-align">
        <table id="records_table" class="tbl table table-striped table-hover">
            <thead>
            <tr>
                <th>Title</th>
                <th>Auditorium</th>
                <th href="">Date</th>
            </tr>
            </thead>
        </table>
    </div>

<form method="get" action="/createOrder">
    <div class="col-md-offset-7">
        <form class="form-horizontal">
            <div class="form-group">
                <div class="col-md-offset-7 col-md-4">
                    <button id="btn-add-order" type="submit" class="btn btn-primary btn-block">add order</button>
                </div>
            </div>
        </form>
    </div>
</form>
    <div class="navbar-fixed-bottom">
        <div class="thick"></div>
        <div class="thin"></div>
        <div><p class="footertext"> © 2015 All Rights Reserved</p></div>
    </div>

</div>
</body>
</html>
