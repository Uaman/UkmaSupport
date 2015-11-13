<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
          integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ=="
          crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"
          integrity="sha384-aUGj/X2zp5rLCbBxumKTCw2Z50WgIr1vs/PFN4praOTvYXWlVyh2UtNUU0KAUhAX" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"
            integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ=="
            crossorigin="anonymous"></script>
    <title></title>
</head>
<body>

<spring:url var="authUrl" value="/static/j_spring_security_check"/>
<form method="post" class="signin" action="${authUrl}">
    <div class="col-lg-3">
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon">Email:</span>
            <input type="text" name="j_username" class="form-control" placeholder="email"
                   aria-describedby="basic-addon1">
        </div>
    </div>
    <br>
    <br>

    <div class="col-lg-3">
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Password:</span>
            <input type="password" name="j_password" class="form-control" placeholder="password"
                   aria-describedby="basic-addon1">
        </div>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <br><br>

    <div class="col-lg-3">
        <button type="submit" class="btn btn-success">Log in</button>
    </div>

</form>

</body>
</html>
