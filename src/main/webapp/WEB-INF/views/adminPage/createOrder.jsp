<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Create Order</title>
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
                url: '/admin/showWorkplaces',
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
    <nav id="header">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="/"><img id="adm_logo" alt="brand" src="../../../resources/img/logo.png"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element" data-toggle="dropdown" href="#"><spring:message
                                code="admin.reports"/><b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a class="menu-element-li" href="/admin/report_auditorium"><spring:message
                                    code="admin.Auditorium_Stats"/></a></li>
                            <li><a class="menu-element-li" href="/admin/report_assist"><spring:message
                                    code="admin.Assist_Stats"/></a>
                            </li>
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

    <div class="col-md-offset-4 col-md-8 vertalign bottom-block">
        <form:form class="form-horizontal" id="newOrder" action="/admin/orders/createOrder" method="post"
                   commandName="newOrder">
            <div class="form-group">
                <div class="col-md-6">
                    <div class="error" id="form-error"><form:errors class="" path="auditorium"
                                                                    id="auditorium.errors"/></div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-6" display="inline-block">
                    <input type="text" class="form-control form-style" id="title" value="${newOrder.title}" name="title"
                           placeholder="<spring:message
                            code="user.title"/>" path="title">
                </div>
                <div class="error" id="title-error"><form:errors path="title" class="" id="title.errors"/></div>
            </div>
            <div class="form-group">
                <div class="col-md-3">
                    <label class="label-style" for="sel1"><spring:message
                            code="user.auditoriums.title"/>:</label>
                    <select name="auditorium" class="form-control select-style" id="sel1" path="auditorium"
                            onchange="getWorkPlace()">
                        <option value="" disabled selected><spring:message
                                code="user.auditoriums.title"/></option>
                        <c:forEach items="${auditoriums}" var="item" varStatus="count">
                            <option value="${item.number}">${item.number}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-3">
                    <label class="label-style" for="sel2"><spring:message
                            code="user.workplace.number"/>:</label>
                    <select name="workplace_access_num" class="form-control select-style" id="sel2"
                            path="workplace_access_num">
                        <option value="" disabled selected><spring:message
                                code="user.workplace.number"/></option>
                    </select>
                </div>
                <div class="error" id="workplace-error"><form:errors path="workplace_access_num" class=""
                                                                     id="workplace_access_num.errors"/></div>

            </div>

            <div class="form-group">
                <div class="col-md-6">
                    <textarea class="col-md-12" rows="5" id="content" name="content" path="content" placeholder="<spring:message
                            code="user.description"/>">${newOrder.content}</textarea>
                </div>
                <div class="error" id="content-error"><form:errors path="content" class="" id="content.errors"/></div>
            </div>

            <div class="form-group">
                <div class="col-md-6">
                    <button id="btn-create-order" type="submit" class="btn btn-primary btn-block"><spring:message
                            code="user.order.add"/></button>
                </div>
            </div>
        </form:form>
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