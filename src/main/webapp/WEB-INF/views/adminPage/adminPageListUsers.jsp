<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>Admin Page List Users</title>
  <link rel="Shortcut Icon" href="" type="image/x-icon"/>
  <link rel="stylesheet" href="../resources/css/bootstrap.css">
  <link rel="stylesheet" href="../resources/css/main.css" type="text/css" media="screen"/>
</head>

<body>

<div id="wrap">
  <nav id="header">
    <div class="container-fluid">
      <div class="navbar-header">
        <a href=""></a><img id="logo" alt="brand" src="../resources/img/logo.png"></a>
      </div>
      <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav navbar-right">
          <li ><a class="menu-element active" href="#">Users</a></li>
          <li class="dropdown">
            <a class="dropdown-toggle menu-element" data-toggle="dropdown" href="#">Orders<b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a class="menu-element-li" href="#">Complited orders</a></li>
              <li><a class="menu-element-li" href="#">Uncomplited orders</a></li>
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
        <th>Last name</th>
        <th>First name</th>
        <th>Role</th>
        <th>Status</th>
      </tr>
      </thead>
      <tbody>
      <tr data-href="#">
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr data-href="#">
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr data-href="#">
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr data-href="#">
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr data-href="#">
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr data-href="#">
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr data-href="#">
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr data-href="#">
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      </tbody>
    </table>
  </div>
</div>
<div class="navbar-fixed-bottom">
  <div class="thick"></div>
  <div class="thin"></div>
  <div><p class="footertext"> © 2015 All Rights Reserved</p></div>
</div>
<script src="../resources/js/jquery-1.11.3.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>