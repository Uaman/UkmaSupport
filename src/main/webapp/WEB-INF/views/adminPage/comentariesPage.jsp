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
        $(document).ready(function () {
            $('#comments').scrollTop(99999999);
            $('#content').keyup(function () {
                if ($('#content').val() != '') {
                    $('#btn-add-comment').removeClass('disabled');
                } else {
                    $('#btn-add-comment').addClass('disabled');
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
                <a href="/"><img id="adm_logo" alt="brand" src="../../../resources/img/logo.png"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element" data-toggle="dropdown" href="#"><spring:message
                                code="admin.reports"/><b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a class="menu-element-li" href="/admin/report_auditorium"><spring:message
                                    code="admin.Auditorium_Stats"/></a></li>
                            <li><a class="menu-element-li" href="/admin/report_assist"><spring:message
                                    code="admin.Assist_Stats"/></a>
                            </li>
                        </ul>
                    </li>

                    <li><a class="menu-element" href="/admin/auditoriums"><spring:message
                            code="admin.auditoriums"/></a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element" data-toggle="dropdown" href="#"><spring:message
                                code="admin.users"/><b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a class="menu-element-li" href="/admin/allUsers"><spring:message
                                    code="admin.allUsers"/></a></li>
                            <li><a class="menu-element-li" href="/admin/users"><spring:message code="admin.users"/></a>
                            </li>
                            <li><a class="menu-element-li" href="/admin/assistants"><spring:message
                                    code="admin.assistants"/></a></li>
                            <li><a class="menu-element-li" href="/admin/professors"><spring:message
                                    code="admin.professors"/></a></li>
                            <li><a class="menu-element-li" href="/admin/blockedUsers"><spring:message
                                    code="admin.blocked"/></a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle menu-element active" data-toggle="dropdown" href="#"><spring:message
                                code="admin.orders"/><b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a class="menu-element-li" href="/admin/allOrders"><spring:message
                                    code="admin.orders"/></a></li>
                            <li><a class="menu-element-li" href="/admin/myOrders"><spring:message
                                    code="admin.myOrders"/></a>
                            </li>
                        </ul>
                    </li>
                    <li><a id="editProfile" class="menu-element" href="/admin/editProfile"><spring:message
                            code="admin.edit"/></a></li>
                    <li><a class="menu-element" href="/logout"><spring:message code="admin.logout"/></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div id="order-inf">
        <p class="body-text" id="order-title"><spring:message code="commentaries.titles"/>${order.title}</p>

        <p class="body-text" id="order-auditorium"><spring:message
                code="commentaries.auditorium"/>${order.auditorium}</p>

        <p class="body-text" id="order-workplace"><spring:message
                code="commentaries.workplace"/>${order.workplace_access_num}</p>

        <p class="body-text" id="order-date"><spring:message
                code="commentaries.date"/>${order.createdAt.toLocaleString()}</p>

        <p class="body-text" id="order-description"><spring:message
                code="commentaries.description"/>${order.content}</p>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div id="comments" class="col-md-offset-2 col-md-8">
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
    <div id="add-comment" class="col-md-offset-2 col-md-8">
        <form:form class="form-horizontal" action="/addComment/${id}" method="post" commandName="comment">
            <div class="form-group">
                <div class="col-md-12">
                    <textarea id="content" name="content" path="comment.content" class="col-md-12 txt-area" rows="4"
                              placeholder="write comment"></textarea>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-offset-9">
                    <button id="btn-add-comment" type="submit" class="btn btn-primary btn-block disabled">
                        <spring:message code="admin.AddComment"/>
                    </button>
                </div>
            </div>
        </form:form>
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
