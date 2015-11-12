<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Admin Page List Users</title>
    <link rel="Shortcut Icon" href="" type="image/x-icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>
</head>

<body>

<div id="wrap">
    <nav id="header">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href=""><img id="logo" alt="brand" src="../../../resources/img/logo.png" style="width: 305px; height:65px; margin-top:11px;"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element active" data-toggle="dropdown" href="#">Auditoriums<b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a class="menu-element-li" href="/createAuditorium">Add Auditorium</a></li>
                            <li><a class="menu-element-li" href="/auditoriums">Auditoriums</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element" data-toggle="dropdown" href="#">Users<b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a class="menu-element-li" href="/all">All</a></li>
                            <li><a class="menu-element-li" href="/users">Users</a></li>
                            <li><a class="menu-element-li" href="/assistants">Assistants</a></li>
                            <li><a class="menu-element-li" href="/professors">Professors</a></li>
                            <li><a class="menu-element-li" href="/blocked">Blocked users</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element" data-toggle="dropdown" href="#">Orders<b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a class="menu-element-li" href="/allOrders">All orders</a></li>
                            <li><a class="menu-element-li" href="/completedOrders">Complited orders</a></li>
                            <li><a class="menu-element-li" href="/uncompletedOrders">Uncomplited orders</a></li>
                        </ul>
                    </li>
                    <li><a class="menu-element" href="#">Edit profile</a></li>
                    <li><a class="menu-element" href="#">Log out</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div>
        <p id="hello">Hello, Admin!</p>
    </div>

    <div class="table-align">
        <table class="tbl table table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <%--<th>Assistant</th>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${auditoriums}" var="item" varStatus="count">
                <tr data-href="#">
                    <td>${item.number}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div class="navbar-fixed-bottom">
    <div class="thick"></div>
    <div class="thin"></div>
    <div><p class="footertext"> © 2015 All Rights Reserved</p></div>
</div>
<script src="../../../resources/js/jquery-1.11.3.js"></script>
<script src="../../../resources/js/bootstrap.min.js"></script>
</body>
</html>
