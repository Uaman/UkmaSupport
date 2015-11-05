<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <td><label>First Name:</label></td>
                <td><form:input path="firstName" value=""/></td>
                <td><form:errors path="firstName" cssStyle="color: #ff0000;"/></td>
            </tr>
            <tr>
                <td><label>Last Name:</label></td>
                <td><form:input path="lastName" value=""/></td>
                <td><form:errors path="lastName" cssStyle="color: #ff0000;"/></td>
            </tr>
            <tr>
                <td><label>E-mail:</label></td>
                <td><form:input path="email" value=""/></td>
                <td><form:errors path="email" cssStyle="color: #ff0000;"/></td>
            </tr>
            <tr>
                <td><label>Password:</label></td>
                <td><form:password path="password" showPassword="true"/></td>
                <td><form:errors path="password" cssStyle="color: #ff0000;"/></td>
            </tr>
            <tr>
                <td><label>Confirm password:</label></td>
                <td><form:password path="confPassword" showPassword="true"/></td>
                <td><form:errors path="confPassword" cssStyle="color: #ff0000;"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Register"/></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>