<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="assist.title.CreateOrder"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="../../../resources/img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function getWorkPlace() {
            $.ajax({
                url: '/ajaxAuditoriums',
                type: 'GET',
                data: {
                    text: $("#sel1").val()
                },
                success: function (data) {
                    $('#sel2').html('');// to clear the previous option
                    $('#res').html(data);
                    $.each(data, function (i, workplace) {
                        $('#sel2').append($('<option>').text(workplace.accessNumber).attr('value', workplace.accessNumber));
                    });
                }
            });
        }
    </script>
</head>
<body>

<div id="wrap">
    <div id="assistContent">
        <nav id="header">
            <div class="container-fluid">

                <div class="navbar-header">
                    <a href="/assist/home"><img id="logo" alt="brand" src="../../../resources/img/logo.png"></a>
                </div>

                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a class="dropdown-toggle menu-element" data-toggle="dropdown"
                               href="#"><spring:message code="assist.menu.Orders"/><b
                                    class="caret"></b></a>

                            <ul class="dropdown-menu">

                                <li class="drop-menu-element"><a class="menu-element-li"
                                                                 href="/assist/home">
                                    <spring:message code="assist.menu.Assigned"/></a></li>
                                <li class="drop-menu-element"><a class="menu-element-li"
                                                                 href="/assist/created_orders">
                                    <spring:message code="assist.menu.Created"/></a></li>

                            </ul>
                        </li>
                        <li><a id="editAssistProfile" class="menu-element" href="/assist/edit_profile">
                            <spring:message code="assist.menu.Profile"/></a></li>
                        <li><a class="menu-element" href="/logout"><spring:message code="assist.menu.LogOut"/></a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div id="assistCreateOrderBlock" class="col-md-offset-4 col-md-8 vertalign bottom-block">
            <form:form class="form-horizontal" id="newOrder" action="/assist/create_order" method="post"
                       commandName="newOrder">
                <div class="form-group">
                    <div class="col-md-6">
                        <div id="form-error">
                            <form:errors class="" path="auditorium" id="auditorium.errors"
                                         cssStyle="color: #ff0000;"/></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6" display="inline-block">
                        <input type="text" class="form-control form-style" id="title" value="${newOrder.title}"
                               name="title"
                               placeholder="<spring:message code="user.title"/>" path="title">

                    </div>
                    <div id="title-error"><form:errors path="title" class="" id="title.errors"
                                                       cssStyle="color: #ff0000;"/></div>
                </div>
                <div id="assistCreateCells" class="form-group">
                    <div class="col-md-3">
                        <label class="label-style" for="sel1">
                            <spring:message code="assist.order.auditorium"/>:</label>
                        <select name="auditorium" class="form-control select-style" id="sel1" path="auditorium"
                                onchange="getWorkPlace()">
                            <option value="" disabled selected>
                                <spring:message code="assist.order.auditorium"/></option>
                            <c:forEach items="${auditoriums}" var="item" varStatus="count">
                                <option value="${item.number}">${item.number}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <label class="label-style" for="sel2">
                            <spring:message code="assist.order.workplace"/>:</label>
                        <select name="workplace_access_num" class="form-control select-style" id="sel2"
                                path="workplace_access_num">
                            <option value="" disabled selected>
                                <spring:message code="assist.order.workplace2"/></option>
                        </select>
                    </div>
                    <div id="workplace-error"><form:errors path="workplace_access_num" class=""
                                                           id="workplace_access_num.errors"
                                                           cssStyle="color: #ff0000;"/></div>
                </div>

                <div class="form-group">
                    <div class="col-md-12">
                    <textarea class="col-md-12" rows="5" id="assist_description" name="content" path="content"
                              placeholder="<spring:message code="user.description"/>"></textarea>
                        <form:errors path="content" id="assist_content_errors"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-12">
                        <button id="assist_create_order_button" type="submit" class="btn btn-primary btn-block">
                            <spring:message code="assist.CreateOrder"/></button>
                    </div>
                </div>
            </form:form>
        </div>

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
</div>
</body>
</html>