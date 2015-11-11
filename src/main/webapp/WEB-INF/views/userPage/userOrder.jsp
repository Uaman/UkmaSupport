<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link rel="Shortcut Icon" href="" type="image/x-icon"/>
  <link rel="stylesheet" href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">
  <link rel="stylesheet" href="../resources/css/bootstrap.css">
  <link rel="stylesheet" href="../resources/css/main.css" type="text/css" media="screen"/>

  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

  <script type="text/javascript">
    $(document).ready(function() {
      $("table").tablesorter();
      $("#ajax-append").click(function() {
        $.get("assets/ajax-content.html", function(html) {
          // append the "ajax'd" data to the table body
          $("table tbody").append(html);
          // let the plugin know that we made a update
          $("table").trigger("update");
          // set sorting column and direction, this will sort on the first and third column
          var sorting = [[2,1],[0,0]];
          // sort on the first column
          $("table").trigger("sorton",[sorting]);
        });
        return false;
      });
    });
  </script>
</head>

<body>
<div id="wrap">

  <div id="header">
    <div>
      <a href="/"><img id="logo" alt="brand" src="../resources/img/logo.png"/></a>
    </div>
    <div class="collapse navbar-collapse">
    </div>
    <div class="generic-container">
      <div class="panel panel-default">
        <!-- Default panel contents -->

        <table id="example" class="display" cellspacing="0" width="100%">
          <thead>
          <tr>
            <th>id</th>
            <th>title</th>
            <th>content</th>
            <th>assistantId</th>
            <th>workplaceId</th>
            <th>accountStatus</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${userOrder}" var="order">
            <tr>
              <td>${order.id}</td>
              <td>${order.title}</td>
              <td>${order.content}</td>
              <td>${order.assistantId}</td>
              <td>${order.workplace_access_num}</td>
              <td>${order.status}</td>

            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>

    </div>


  </div>
  </div>

</body>
</html>
