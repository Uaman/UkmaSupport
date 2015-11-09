<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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


</head>
<body>
<h1>Form</h1>
<div align="center">
    <h1>Excel View </h1>
    <h3><a href="/downloadExcel">Download Excel Document</a></h3>
</div>
<form:form method="POST" action="userhome">
    <form:errors path="*" cssClass="errorblock" element="div"/>

    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
    </table>
</form:form>
</body>
</html>
