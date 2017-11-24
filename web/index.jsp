<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Mateusz Wedeł
  Date: 18.11.2017
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ShareYourFind</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top header">
    <div class="container">
        <a href="#" class="navbar-brand">ShareYourFind</a>

        <div class="collapse navbar-collapse navHeaderCollapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="index.jsp">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/add">Add</a></li>
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <li><a href="logout">Log out!</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="login">Log in!</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

<c:if test="${not empty requestScope.finds}">
    <c:forEach var="find" items="${requestScope.finds}">
        <div class="container">
            <div class="row bs-callout bs-callout-primary">
                <div class="col col-md-1 col-sm-2">
                    <a href="#" class="btn btn-block btn-primary btn-success"><span
                            class="glyphicon glyphicon-chevron-up"></span> </a>
                    <div class="well well-sm centered numberColor"><c:out value="${find.upVote - find.downVote}"/></div>
                    <a href="#" class="btn btn-block btn-primary btn-warning"><span
                            class="glyphicon glyphicon-chevron-down"></span> </a>
                </div>
                <div class="col col-md-11 col-sm-10">
                    <h3 class="centered"><a href="<c:out value="${find.url}" />"><c:out value="${find.name}"/></a></h3>
                    <h6>
                        <small>Added by: <c:out value="${find.user.username}"/>,
                            Date: <fmt:formatDate value="${find.timestamp}" pattern="dd/MM/YYYY"/></small>
                    </h6>
                    <p><c:out value="${find.descritpion}"/></p>
                    <a href="<c:out value="${find.url}"/>" class="btn btn-default btn-xs">Check out!</a>
                </div>
            </div>
        </div>
    </c:forEach>
</c:if>

<footer class="footer">
    <div class="container">
        <p class="navbar-text">ShareYourFind - developed by <a target="_blank" href="https://github.com/Wheezyx">Mateusz
            Wedeł</a></p>
    </div>
</footer>

<script src="http://code.jquery.com/jquery-1.11.2.min.2"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
