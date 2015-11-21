<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title></title>
    <sec:csrfMetaTags/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="../../../resources/img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../../resources/css/main.css" type="text/css" media="screen"/>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="../../../resources/js/jquery-1.11.3.js"></script>
    <script src="../../../resources/js/bootstrap.min.js"></script>

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

    <div id="order-inf">
        <p class="body-text" id="order-title">Title:</p>
        <p class="body-text" id="order-auditorium">Auditorium:</p>
        <p class="body-text" id="order-workplace">Workplace:</p>
        <p class="body-text" id="order-date">Date:</p>
    </div>
    <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>

    <div id="comments" class="col-md-offset-2 col-md-8">
        <c:forEach var="comment" items="${allCommentaries}" varStatus="count" >
            <p class="comment body-text">comment#: By ${comment.author.firstName} ROLE ${comment.author.role} TIME ${comment.time} CONTENT ${comment.content} </p>
        </c:forEach>


        <%--<p class="comment body-text">comment#2: dfsdfjsasdfasdfsFdfsdfs</p>--%>
        <%--<p class="comment body-text">comment#3: dfsdfjsdfsDSfSDfSFDdfs</p>--%>
        <%--<p class="comment body-text">comment#4: dfsdfjsdfasdfasfdafsdfs</p>--%>
        <%--<p class="comment body-text">comment#5: dfsdfjsSDFSDfSDFSDFSVxzcgstuhjtydjfyumfgmfgymgfSADASDASDASDcASCACASCdfsdfs</p>--%>
        <%--<p class="comment body-text">comment#6: dfsdfjsASCACASCACSdfsdfs</p>--%>
        <%--<p class="comment body-text">comment#7: dfsdfjsCzASCcSACASCdfsdfs</p>--%>
        <%--<p class="comment body-text">comment#8: dfsdfjsASCASCASDcasjef;oiaj;foasjkf;doiawckpawecffawedfsdfs</p>--%>
        <%--<p class="comment body-text">comment#9: dfsdfsdcSdfkjaso;efj;oaefaefjsdfsdfs</p>--%>
        <%--<p class="comment body-text">comment#10: dfsdfjSDfake;AJ;DJ;aCSM;mksc;cssdfsdfs</p>--%>
        <%--<p class="comment body-text">comment#11: dfsdCAPWECKPACMKPAC,PCfjsdfsdfs</p>--%>
        <%--<p class="comment body-text">comment#12: dfsdfjCEWCJA[CE[CK[ACEECsdfsdfs</p>--%>
        <%--<p class="comment body-text">comment#13: dfsdfjsCEAckjaPECJPaecCEdfsdfs</p>--%>
        <%--<p class="comment body-text">comment#14: dfsdfCeck['CM[Aecjsdfsdfs</p>--%>
    </div>
    <div id="add-comment" class="col-md-offset-2 col-md-8">
        <form:form class="form-horizontal" action="/addComment/${id}" method="post" commandName="comment">
            <div class="form-group">
                <div class="col-md-12">
                    <textarea id="content" name="content" path="comment.content"  class="col-md-12 txt-area" rows="4" placeholder="write comment"></textarea>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-offset-9">
                    <button id="btn-add-comment" type="submit" class="btn btn-primary btn-block">Add comment</button>
                </div>
            </div>
        </form:form>
    </div>

<div class="footer">
    <div class="thick"></div>
    <div class="thin"></div>
    <div><p class="footertext" style="padding-bottom: 10px;"><spring:message code="login.footer"/></p></div>

    <div class="text-center">
        <a href="?lang=en" class="language"><spring:message code="language.en"/></a>
        <a href="?lang=ua" class="language"><spring:message code="language.ua"/></a>
    </div>
</div>

</div>

</body>
</html>
