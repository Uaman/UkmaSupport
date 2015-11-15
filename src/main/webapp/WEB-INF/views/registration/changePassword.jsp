<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Change password</title>
  <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
</head>
<body>
<c:if test="${not empty error}">
  ${error}
</c:if>
<c:if test="${not empty success}">
  ${success}
</c:if>
<c:if test="${not empty notEqual}">
  ${notEqual}
</c:if>

<form method="POST" name="changePassword" action="/changePassword">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

  <form:input path = "user_id" name="user_id" value="${user_id}" type="text" cssClass="invisible" cssStyle="position: absolute" />
  New password: <form:input path = "newPassword" name="newPassword" value="${newPassword}" type="password"  />

  <br />

  Confirm new password: <form:input path = "newPasswordConfirm" name="newPasswordConfirm" value="${newPasswordConfirm}" type="password"  />

  <br />
  <input value="Change password" type="submit" />
</form>
</body>
</html>