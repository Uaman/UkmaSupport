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
</head>
<body>
<div align="center">
    <c:url var="addUrl" value="/register"/>
    <form:form action="${addUrl}" method="get" commandName="userForm">
        <table border="0">
            <h1>${message}</h1>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="registration"/></td>
            </tr>
        </table>
    </form:form>
</div>


<br>
<br>
<br>
<br>

<form>
    <br>

    <div class="col-lg-3">
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon">Email:</span>
            <input type="text" class="form-control" placeholder="email" aria-describedby="basic-addon1">
        </div>
    </div>
    <br>
    <br>

    <div class="col-lg-3">
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Password:</span>
            <input type="password" class="form-control" placeholder="password" aria-describedby="basic-addon1">
        </div>
    </div>
    <br><br>

    <div class="col-lg-3">
        <button type="button" class="btn btn-success">Log in</button>
    </div>
</form>
</body>
</html>

