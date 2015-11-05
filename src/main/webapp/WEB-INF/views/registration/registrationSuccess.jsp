<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration Success</title>
</head>
<body>
<div align="center">
    <table border="0">
        <tr>
            <td colspan="2" align="center"><h2>Registration Succeeded!</h2></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <h3>Thank you for registering! Here's the review of your details:</h3>
            </td>
        </tr>
        <tr>
            <td>First Name:</td>
            <td>${userForm.firstName}</td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td>${userForm.lastName}</td>
        </tr>
        <tr>
            <td>E-mail:</td>
            <td>${userForm.email}</td>
        </tr>

    </table>
</div>
</body>
</html>