<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 19.11.2017
  Time: 01:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<!--HEADER-->
<nav class="navbar navbar-inverse navbar-fixed-top header">
    <div class="container">
        <a href="#" class="navbar-brand">ShareYourFind</a>

        <div class="collapse navbar-collapse navHeaderCollapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/add">Add</a></li>
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

<!--Adding formula-->

<div class="container">
    <div class="col-md-8 col-md-offset-2">
        <form class="form-signin" method="post" action="add">
            <h2 class="form-signin-heading">Add new find!</h2>
            <c:if test="${requestScope.existingFind != null}">
                <div class="alert alert-danger">
                    <strong>Danger!</strong> <c:out value="${requestScope.existingFind}"/>
                </div>
            </c:if>
            <input name="inputName" type="text" class="form-control" placeholder="What?"
                   required autofocus />
            <input name="inputUrl" type="url" class="form-control" placeholder="URL"
                   required autofocus />
            <textarea name="inputDescription" rows="5" name="inputUsername"
                      class="form-control" placeholder="Description" required autofocus></textarea>
            <input class="btn btn-lg btn-primary btn-block" type="submit"
                   value="Add!" />
        </form>
    </div>
</div>

<!--FOOTER-->

<footer class="footer">
    <div class="container">
        <p class="navbar-text">ShareYourFind - developed by <a target="_blank" href="https://github.com/Wheezyx">Mateusz Wedeł</a></p>
    </div>
</footer>

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
</body>
</html>
