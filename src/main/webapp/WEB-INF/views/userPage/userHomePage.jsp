<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html xmlns:th="http://www.thymeleaf.org">
<html>
<head>

    <meta http-equiv = "Content-Type" content="text/html; charset=utf-8" />
    <link rel="Shortcut Icon" href="" type="image/x-icon" />
    <link rel="stylesheet" href="../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../resources/css/main.css" type="text/css" media="screen" />


</head>
<body>
<div id = "header">

    <div>
        <a href="/"><img id="logo" alt="brand" src="../resources/img/logo.png" /></a>
    </div>

    <div class="collapse navbar-collapse">

    </div>
</div>
<h1>Form</h1>
<form:form method="GET" action="createOrder">
    <form:errors path="*" cssClass="errorblock" element="div"/>

    <tr>
        <td colspan="2">
            <input type="submit" value="Create Order"/>
        </td>
    </tr>
    </table>
</form:form>

<div align="center">
    <h1>Excel View </h1>
    <h3><a href="/downloadExcel">Download Excel Document</a></h3>
</div>


<div class="navbar-fixed-bottom">
    <div class="thick"></div>
    <div class="thin"></div>
    <div><p class="footertext"> © 2015 All Rights Reserved</p></div>
</div>
</body>
</html>
