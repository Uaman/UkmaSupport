<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>createOrder</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="Shortcut Icon" href="" type="image/x-icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>

  <script type="text/javascript">

      function  getWorkPlace() {
        $.ajax({
          url : 'ajaxtest',
          type : 'GET',
          data:{
            text : $("#sel1").val()
          },
          success : function(data) {
              $('#sel2').html('');// to clear the previous option
              $('#res').html(data);



              $.each(data, function(i, workplace) {
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
                <a href="/"><img id="logo" alt="brand" src="../../../resources/img/logo.png"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element" data-toggle="dropdown" href="/userhome"> My orders<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li class="drop-menu-element"><a class="menu-element-li" href="/complited">Completed orders</a></li>
                            <li class="drop-menu-element"><a class="menu-element-li" href="/uncomplited">Uncompleted orders</a></li>
                        </ul>
                    </li>
                    <li><a id = "editProfile" class="menu-element" href="/editProfile">Edit profile</a></li>
                    <li><a class="menu-element" href="#">Log out</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="col-md-offset-4 col-md-4 vertalign without-top bottom-block">
        <form:form class="form-horizontal" id="newOrder" action="/createOrder" method="post" commandName="newOrder">
            <div class="form-group">
                <div class="col-md-12" display="inline-block">
                    <span><input type="text" class="form-control form-style" id="title" name="title" placeholder="title" path="title"></span>
                        <span><form:errors path="title" class="" id="title.errors"/></span>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-6">
                    <label class="label-style" for="sel1">auditorium:</label>
                    <select name="auditorium" class="form-control select-style" id="sel1" path="auditorium" onchange="getWorkPlace()" >
                        <option value="" disabled selected>auditorium</option>
                        <c:forEach items="${auditoriums}" var="item" varStatus="count">
                            <option value="${item.number}">${item.number}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-6">
                    <label class="label-style" for="sel2">workplace:</label>
                    <select name="workplace_access_num" class="form-control select-style" id="sel2" path="workplace_access_num">
                        <option value="" disabled selected>workplace</option>
                    </select>
                    <form:errors path="workplace_access_num" class="" id="workplace_access_num.errors"/>
                </div>

            </div>

            <div class="form-group">
                <div class="col-md-12">
                    <textarea class="col-md-12" rows="5"  id="content"  name="content" path="content" placeholder="description"></textarea>
                    <form:errors path="content" class="" id="content.errors"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-12">
                    <button id="btn-create-order" type="submit" class="btn btn-primary btn-block">Create order</button>
                </div>
            </div>
        </form:form>
    </div>


    <div class="footer">
        <div class="thick"></div>
        <div class="thin"></div>
        <div><p class="footertext"> © 2015 All Rights Reserved</p></div>
    </div>
</div>
</body>
</html>
