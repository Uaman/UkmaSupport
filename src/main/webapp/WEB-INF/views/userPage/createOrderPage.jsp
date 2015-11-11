<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="Shortcut Icon" href="" type="image/x-icon"/>
    <link rel="stylesheet" href="../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../resources/css/main.css" type="text/css" media="screen"/>

  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

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
    <title></title>

</head>
<body>

<div id="wrap">

<div id="header">

    <div>
        <a href="/"><img id="logo" alt="brand" src="../resources/img/logo.png"/></a>
    </div>

    <div class="collapse navbar-collapse">

    </div>
</div>

<form:form id="newOrder" action="/createOrder" method="post" commandName="newOrder">
    <div class="form-group">
        <div class="col-md-offset-3 col-md-7">
            <input type="text" class="form-control form-style" id="title" name="title" placeholder="title" path="title">
        </div>
    </div>
<br>
<br>

    <div class="col-md-offset-3 col-md-3">
        <div class="form-inline">
            <label for="sel1">auditorium:</label>
            <select name="auditorium" class="form-control" id="sel1"  onchange="getWorkPlace()" >
              <option value="" disabled selected>Select auditorium</option>
              <c:forEach items="${auditoriums}" var="item" varStatus="count">
                    <option value="${item.number}">${item.number}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-inline">
            <label for="sel2">workplace:</label>
            <select name="workplace_access_num" class="form-control" id="sel2" path="workplace_access_num">
              <option value="" disabled selected>Select workplace</option>
                <option value="adf">adgvadg</option>
            </select>
        </div>
    </div>

<div class="form-group">
    <div class="col-md-offset-3 col-md-7">
    <textarea  id="content" style="resize: none" rows="4" cols="50" name="content" path="content" placeholder="description"></textarea>
    </div>
</div>

<div class="form-group">
    <div class="col-md-offset-4 col-md-6">
        <button class="button" type="submit">Create order</button>
    </div>
</div>



</div>
</form:form>


<div class="navbar-fixed-bottom">
    <div class="thick"></div>
    <div class="thin"></div>
    <div><p class="footertext"> © 2015 All Rights Reserved</p></div>
</div>
</body>
</html>