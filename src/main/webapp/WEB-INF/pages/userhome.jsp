<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%--
  Created by IntelliJ IDEA.
  User: viktor
  Date: 03.11.15
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<html xmlns:th="http://www.thymeleaf.org">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>


    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <link href="http://cdn.jsdelivr.net/webjars/bootstrap/3.3.4/css/bootstrap.min.css"
          th:href="@{/webjars/bootstrap/3.3.4/css/bootstrap.min.css}"
          rel="stylesheet" media="screen" />

    <script src="http://cdn.jsdelivr.net/webjars/jquery/2.1.4/jquery.min.js"
            th:src="@{/webjars/jquery/2.1.4/jquery.min.js}"></script>

    <link href="../static/css/guru.css"
          th:href="@{css/guru.css}" rel="stylesheet" media="screen"/>
</head>
<body>
<h1>Form</h1>
<form:form method="POST" action="userhome">
  <form:errors path="*" cssClass="errorblock" element="div" />
  <table>
    <tr>
      <td><form:label path="title">Title</form:label></td>
      <td><form:input path="title" /></td>
      <td><form:errors path="title" cssClass="error" /></td>
    </tr>
    <tr>
      <td><form:label path="order">Order</form:label></td>
      <td><th:textarea path="order" /></td>
      <td><form:errors path="order" cssClass="error" /></td>
    </tr>
    <tr>
      <td><form:label path="id">id</form:label></td>
      <td><form:input path="id" /></td>
      <td><form:errors path="id" cssClass="error" /></td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" value="Submit"/>
      </td>
    </tr>
  </table>
</form:form>
</body>
</html>
