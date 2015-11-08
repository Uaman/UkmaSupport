<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta http-equiv = "Content-Type" content="text/html; charset=utf-8" />
    <title>Registration</title>
    <link rel="Shortcut Icon" href="" type="image/x-icon" />
    <link rel="stylesheet" href="../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../resources/css/main.css" type="text/css" media="screen" />
</head>

<body>
<div id="wrap">
    <div id = "header">

        <div>
            <a href="#"><img id="logo" alt="brand" src="../resources/img/logo.png" /></a>
        </div>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a id="login" class = "leftWord" href="/">Log in</a></li>
            </ul>
        </div>
    </div>


    <div id="mainRegister">
        <form:form id="userForm" action="register" method="post" commandName="userForm">
            <table border="0">
                <tr>
                    <td class="centralWord" colspan="2" align="center">Registration</td>
                </tr>

                <tr>
                    <td><label class="labels">First Name:</label></td>
                    <td><form:input id="firstName" path="firstName" name="firstName" type="text" value="" class="form-control form-style"/></td>
                    <td><form:errors path="firstName" class="regErrors" id="firstName.errors" cssStyle="color: #ff0000;"/></td>
                </tr>
                <tr>
                    <td><label class="labels">Last Name:</label></td>
                    <td><form:input id="lastName" path="lastName" name="lastName" type="text" value="" class="form-control form-style"/></td>
                    <td><form:errors path="lastName" class="regErrors" id="lastName.errors" cssStyle="color: #ff0000;"/></td>
                </tr>
                <tr>
                    <td><label class="labels">E-mail:</label></td>
                    <td><form:input id="email" path="email" name="email" type="text" value=""  class="form-control form-style"/></td>
                    <td><form:errors path="email" class="regErrors" id="email.errors" cssStyle="color: #ff0000;"/></td>
                </tr>
                <tr>
                    <td><label class="labels">Password:</label></td>
                    <td><form:input id="password" path="password" name="password" type="password" value="" class="form-control form-style" /></td>
                    <td><form:errors path="password" class="regErrors" id="password.errors" cssStyle="color: #ff0000;"/></td>
                </tr>
                <tr>
                    <td><label class="labels">Confirm password:</label></td>
                    <td><form:input id="confPassword" path="confPassword" name="confPassword" type="password" value="" class="form-control form-style"/></td>
                    <td><form:errors path="confPassword" class="regErrors" id="confPassword.errors" cssStyle="color: #ff0000;"/></td>

                </tr>

            </table>
            <!-- <div>

            <input type="hidden" name="_csrf" value="8ab59d35-bc06-4000-aa49-fa9e5c21a538" />
            </div>-->
            <div class="form-group">
                <div class="col-md-offset-7 col-md-4">
                    <button id="register-button" type="submit" value="Register" class="btn btn-primary btn-block button-style">Register</button>
                </div>
            </div>
        </form:form>
    </div>

    <div class="navbar-fixed-bottom">
        <div class="thick"></div>
        <div class="thin"></div>
        <div><p class="footertext"> © 2015 All Rights Reserved</p></div>
    </div>

</div>
</body>
</html>