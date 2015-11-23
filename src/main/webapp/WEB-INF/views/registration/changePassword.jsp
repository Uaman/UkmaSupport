<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title><spring:message code="changePassword.title"/></title>
  <link href="../../../resources/img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
  <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
  <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>

  <script src="../../../resources/js/jquery-1.11.3.js"></script>
  <script src="../../../resources/js/bootstrap.min.js"></script>
</head>

<body>

<div id="wrap">
  <div id="header">
    <div>
      <a href="/"><img id="logo" alt="brand" src="../../../resources/img/logo.png"/></a>
    </div>
  </div>

  <spring:url var="authUrl" value="/static/j_spring_security_check"/>
  <div class="col-md-offset-4 col-md-4 vertalign bottom-block" id="auth-block">

    <form class="form-horizontal" method="POST" name="changePassword" action="/changePassword">

      <form:input path = "user_id" name="user_id" value="${user_id}" type="text" cssClass="invisible" cssStyle="position: absolute" />

      <div class="form-group">
        <div class="col-md-offset-1 col-md-10">
          <input name="newPassword" value="${newPassword}" type="password"  class="form-control form-style" placeholder="<spring:message code="admin.newPassword"/>"/>
        </div>
      </div>

      <div class="form-group">
        <div class="col-md-offset-1 col-md-10">
          <input name="newPasswordConfirm" value="${newPasswordConfirm}" type="password"  class="form-control form-style" placeholder="<spring:message code="assist.ConfirmNewPassword"/>"/>
        </div>
      </div>

      <div class="form-group">
        <div class="col-md-offset-1 col-md-10">
          <button id="changepass-button" type="submit" class="btn btn-primary btn-block button-style">
            <spring:message code="changePassword.title"/>
          </button>
        </div>
      </div>

      <div class="form-group">
        <div class="col-md-offset-1 col-md-10">
          <c:if test="${not empty error}">
            <spring:message code="changePassword.error.empty"/>
          </c:if>
          <c:if test="${not empty success}">
            ${success}
          </c:if>
          <c:if test="${not empty notEqual}">
            <spring:message code="changePassword.error.notEqual"/>
          </c:if>
          <c:if test="${not empty incorect}">
            <spring:message code="changePassword.error.incorrect"/>
          </c:if>
        </div>
      </div>

      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    </form>
  </div>

  <div id="footer">
    <div class="thick"></div>
    <div class="thin"></div>
    <div><p class="footertext"><spring:message code="login.footer"/></p></div>
    <div id="localization">
      <a href="?lang=en" class="language"><spring:message code="language.en"/></a>
      <a href="?lang=ua" class="language"><spring:message code="language.ua"/></a>
    </div>
  </div>
</div>
</body>
</html>
