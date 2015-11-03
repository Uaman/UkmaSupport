<%--
  Created by IntelliJ IDEA.
  User: Roma
  Date: 02.11.2015
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Registration</title>
</head>
<body>
<div align="center">
  <form:form action="register" method="post" commandName="userForm">
    <table border="0">
      <tr>
        <td colspan="2" align="center"><h2>Registration</h2></td>
      </tr>
      <tr>
        <td>First Name:</td>
        <td><form:input path="firstName" /></td>
      </tr>
      <tr>
        <td>Last Name:</td>
        <td><form:input path="lastName" /></td
      </tr>
      <tr>
        <td>E-mail:</td>
        <td><form:input path="email" /></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><form:password path="password" /></td>
      </tr>
      <tr>
        <td>Confirm password:</td>
        <td><form:password path="confPassword" showPassword="true"/></td>
      </tr>
      <tr>
        <td colspan="2" align="center"><input type="submit" value="Register" /></td>
      </tr>
    </table>
  </form:form>
</div>
</body>
</html>