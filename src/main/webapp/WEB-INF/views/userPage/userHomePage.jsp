<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv = "Content-Type" content="text/html; charset=utf-8" />
    <title>userhome</title>
    <link rel="Shortcut Icon" href="" type="image/x-icon" />
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen" />
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>
    <script>
        jQuery( function($) {
            $('tbody tr[data-href]').addClass('clickable').click( function() {
                window.location = $(this).attr('data-href');
            });
        });
    </script>
</head>

<body>

<div id="wrap">
    <nav id="header">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href=""><img id="logo" alt="brand" src="../../../resources/img/logo.png"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element" data-toggle="dropdown" href="#"> My orders<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li class="drop-menu-element"><a class="menu-element-li" href="#">Complited orders</a></li>
                            <li class="drop-menu-element"><a class="menu-element-li" href="#">Uncomplited orders</a></li>
                        </ul>
                    </li>
                    <li><a class="menu-element" href="#">Edit profile</a></li>
                    <li><a class="menu-element" href="#">Log out</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div>
        <p id="hello">Hello, User!</p>
    </div>

    <div class="table-align">
        <table class="tbl table table-striped">
            <thead>
            <tr>
                <th>Title</th>
                <th>Auditorium</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userOrder}" var="order">
                <tr>
                    <td>${order.title}</td>
                    <td>${order.workplace_access_num}</td>
                    <td>${order.createdAt}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="col-md-offset-7">
        <form class="form-horizontal">
            <div class="form-group">
                <div class="col-md-offset-7 col-md-4">
                    <button id="btn-add-order" type="submit" class="btn btn-primary btn-block">add order</button>
                </div>
            </div>
        </form>
    </div>

    <div class="navbar-fixed-bottom">
        <div class="thick"></div>
        <div class="thin"></div>
        <div><p class="footertext"> © 2015 All Rights Reserved</p></div>
    </div>

</div>
</body>
</html>
