<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Change password</title>
</head>
<body>
<c:if test="${not empty error}">
  ${error}
</c:if>
<c:if test="${not empty success}">
  ${success}
</c:if>

<form method="POST" name="changePassword" action="/changePassword">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

  ${user_id}: <input name="newPassword" value="${newPassword}" type="password"  />

  <br />
  <input value="Change password" type="submit" />
</form>
</body>
</html>