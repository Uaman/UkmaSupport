<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="commentaries.title"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="../../../resources/img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('.click-to-drop').click(function() {
                $(this).siblings('.drop-el').slideToggle('fast');
                if ($(this).children('.drop-inf').hasClass('glyphicon-chevron-down')) {
                    $(this).children('.drop-inf').removeClass('glyphicon-chevron-down');
                    $(this).children('.drop-inf').addClass('glyphicon-chevron-up');
                } else {
                    $(this).children('.drop-inf').removeClass('glyphicon-chevron-up');
                    $(this).children('.drop-inf').addClass('glyphicon-chevron-down');
                }
            });
            $('#comments').scrollTop($('#comments').prop('scrollHeight'));
            $('.add-comment-form').submit(function() {
                return false;
            });
            $('#content').keyup(function() {
                if ($('#content').val() != '') {
                    $('#btn-add-comment').removeClass('disabled');
                    $('.add-comment-form').unbind();
                } else {
                    $('#btn-add-comment').addClass('disabled');
                    $('.add-comment-form').submit(function() {
                        return false;
                    });
                }
            });
        });
    </script>

</head>
<body>

<div id="wrap">
    <nav id="header">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="/user/userhome"><img id="logo" alt="brand" src="../../../resources/img/logo.png"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a class="menu-element" href="/user/userhome"> <spring:message
                            code="admin.orders"/></a></li>
                    <li><a id="editProfile" class="menu-element" href="/user/editProfile"><spring:message
                            code="admin.edit"/></a></li>
                    <li><a class="menu-element" href="/logout"><spring:message code="admin.logout"/></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="col-md-12 top-block comment-title">
        <p>${order.title}</p>
    </div>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div class="col-md-12 comments-body ">
        <div class="col-md-4 order-inf">
            <div>
                <p class="click-to-drop"><i class="drop-inf glyphicon glyphicon-chevron-down"></i>auditorium</p>
                <p class="drop-el">${order.auditorium}</p><hr>
            </div>
            <div>
                <p class="click-to-drop"><i class="drop-inf glyphicon glyphicon-chevron-down"></i>workplace</p>
                <p class="drop-el">${order.workplace_access_num}</p><hr>
            </div>
            <div>
                <p class="click-to-drop"><i class="drop-inf glyphicon glyphicon-chevron-down"></i>date</p>
                <p class="drop-el">${order.createdAt.toLocaleString()}</p><hr>
            </div>
            <div>
                <p class="click-to-drop"><i class="drop-inf glyphicon glyphicon-chevron-down"></i>description</p>
                <p class="drop-el">${order.content}</p><hr>
            </div>
            <div>
                <button type="submit" action="" class="btn btn-primary btn-block btn-edit-order">Edit order</button>
            </div>
        </div>
        <div class="col-md-offset-4">
            <div id="comments" class="col-md-offset-1 col-md-10">
                <c:forEach var="comment" items="${allCommentaries}" varStatus="count">
                    <c:choose>
                        <c:when test="${comment.author.role == 'USER'}">
                            <div class="col-md-12 comment-block">
                                <div class="col-md-3 comment-date">${comment.time.toLocaleString()}</div>
                                <div class="col-md-offset-3 comment user-comment"><span
                                        class="bold-text">${comment.author.firstName}, ${comment.author.role}</span><br>${comment.content}
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${comment.author.role == 'ASSISTANT'}">
                            <div class="col-md-12 comment-block">
                                <div class="col-md-9 comment assistant-comment"><span
                                        class="bold-text">${comment.author.firstName}, ${comment.author.role}</span><br>${comment.content}
                                </div>
                                <div class="col-md-offset-9 comment-date">${comment.time.toLocaleString()}</div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-md-12 comment-block">
                                <div class="col-md-9 comment admin-comment"><span
                                        class="bold-text">${comment.author.firstName}, ${comment.author.role}</span><br>${comment.content}
                                </div>
                                <div class="col-md-offset-9 comment-date">${comment.time.toLocaleString()}</div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <div id="add-comment" class="col-md-offset-1 col-md-10">
                <form:form class="form-horizontal add-comment-form" action="/addComment/${id}" method="post" commandName="comment">
                    <div class="form-group">
                        <div class="col-md-12">
                    <textarea id="content" name="content" path="comment.content" class="col-md-12 txt-area" rows="4"
                              placeholder="write comment"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-offset-8">
                            <button id="btn-add-comment" type="submit" class="btn btn-primary btn-block disabled">Add comment
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
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

