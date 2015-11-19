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
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular-resource.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#test").click(function (e) {
                var data='role'+$('input:radio[name=role]:checked').val()
                var role =$('input:radio[name=role]:checked').val();
                alert(role);
                $.ajax({
                    url: "/admin/changeRole",
                    type: 'POST',
                    dataType: 'json',
                    data: JSON.stringify("{\"role\":\"+admin\"}"),
                    contentType: 'application/json',
                    mimeType: 'application/json',
                    success: function(data) {
                        alert(data.role);
                    },
                    error:function(data,status,er) {
                        alert("error: "+data+" status: "+status+" er:"+er);
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
                            <li><a class="menu-element-li" href="/admin/allUsers"><spring:message code="admin.allUsers"/></a></li>
                            <li><a class="menu-element-li" href="/admin/users"><spring:message code="admin.users"/></a></li>
                            <li><a class="menu-element-li" href="/admin/assistants"><spring:message
                                    code="admin.assistants"/></a></li>
                            <li><a class="menu-element-li" href="/admin/professors"><spring:message
                                    code="admin.professors"/></a></li>
                            <li><a class="menu-element-li" href="/admin/blockedUsers"><spring:message code="admin.blocked"/></a>
                            </li>
                        </ul>
                    </li>
                    <li><a id="orders" class="menu-element" href="/admin/allOrders"><spring:message
                            code="admin.orders"/></a></li>
                    <li><a id="editProfile" class="menu-element" href="/admin/editProfile"><spring:message
                            code="admin.edit"/></a></li>
                    <li><a class="menu-element" href="/logout"><spring:message code="admin.logout"/></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="table-align bottom-block top-table">
        <table class="tbl table table-striped">
            <thead>
            <tr>
                <th><spring:message code="registration.lastName"/></th>
                <th><spring:message code="registration.firstName"/></th>
                <th style="width:120px;"><spring:message code="admin.users.role"/></th>
                <th width="25px;"></th>
                <th style="width:150px;"><spring:message code="admin.users.status"/></th>
                <th width="25px;"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="item" varStatus="count">
                <tr data-href="#">
                    <td>${item.lastName}</td>
                    <td>${item.firstName}</td>
                    <td>${fn:toLowerCase(item.role)}</td>
                    <td><input type="image" src="../../../resources/img/edit.jpg" data-toggle="modal" data-target="#myModal" width="15px" height="15px" style="margin-left: 5px; margin-top: 0px;float:left;"></td>
                    <td>${item.accountStatus}</td>
                    <td><input type="image" src="../../../resources/img/edit.jpg" data-toggle="modal" data-target="#myModalStatus" width="15px" height="15px" style="margin-left: 5px; margin-top: 0px;float:left;"></td>
                </tr>
            </c:forEach>
            </tbody>
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
    <div class="modal-dialog" style="width:300px;" >
        <div class="modal-content" >
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
                <center>  <button type="button" id="test" type="submit"  onclick="changeRole()" class="btn btn-default" data-dismiss="modal">Ok</button></center>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModalStatus" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:300px;" >
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <center><h4 class="modal-title" id="myModalLabelStatus">Edit Status</h4></center>
            </div>
            <div class="modal-body">
                <input type="radio" name="browser" value="ie"> Active<Br>
                <input type="radio" name="browser" value="opera"> Blocked<Br>
            </div>
            <div class="modal-footer">
                <center>  <button type="button" class="btn btn-default" data-dismiss="modal">Ok</button></center>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
