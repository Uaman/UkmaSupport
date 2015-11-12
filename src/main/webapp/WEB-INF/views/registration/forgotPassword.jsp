<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgot password?</title>
</head>
<body>
  <c:if test="${not empty error}">
    ${error}
  </c:if>
  <c:if test="${not empty success}">
    ${success}
  </c:if>

  <form method="POST" name="forgotPassword" action="/forgotPassword">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    Email: <input name="email" value="${email}" type="text" />

    <br />
    <input value="Send email" type="submit" />
  </form>
</body>
</html>
