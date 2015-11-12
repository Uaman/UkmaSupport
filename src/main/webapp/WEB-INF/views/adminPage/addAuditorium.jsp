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

  <title></title>

</head>
<body>

<div id="wrap">

  <div id="header">

    <div>
      <a href="/"><img id="logo" alt="brand" src="../resources/img/logo.png" style="width: 305px; height:65px; margin-top:11px;"/></a>
    </div>

    <div class="collapse navbar-collapse">

    </div>
  </div>

  <form:form id="newAuditorium" action="/createAuditorium" method="post" commandName="newAuditorium">
  <div class="form-group">
    <div class="col-md-offset-3 col-md-7">
      <input type="text" class="form-control form-style" id="number" name="number" placeholder="number" path="number">
    </div>
  </div>
  <br>
  <br>
  <br>
  <br>
  <div class="col-md-offset-3 col-md-3">
    <div class="form-inline">
      <label for="sel1">assistant:</label>
      <select name="assistants" class="form-control" id="sel1" >
        <option value="" disabled selected>Select assistant</option>
        <c:forEach items="${assistants}" var="item" varStatus="count">
          <option value="${count.index}">${item.email}</option>
        </c:forEach>
      </select>
    </div>
  </div>
  <div class="form-group">
    <div class="col-md-offset-4 col-md-6">
      <button class="button" type="submit">Create Auditorium</button>
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
