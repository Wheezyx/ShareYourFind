<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.11.2017
  Time: 22:15
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

<div class="container numberColor">
    <div class="row">
        <div class="col-md-3">
            <ul class="nav nav-pills nav-stacked admin-menu">
                <li class="active"><a href="" data-target-id="profile"><i class="glyphicon glyphicon-user"></i> Profile</a>
                </li>
                <li><a href="" data-target-id="change-password"><i class="glyphicon glyphicon-lock"></i> Change Password</a>
                </li>
            </ul>
        </div>
        <div class="col-md-9 admin-content" id="profile">
            <div class="panel panel-info" style="margin: 1em;">
                <div class="panel-heading">
                    <h3 class="panel-title">Username</h3>
                </div>
                <div class="panel-body">
                    <c:out value="${user.username}"/>
                </div>
            </div>
            <div class="panel panel-info" style="margin: 1em;">
                <div class="panel-heading">
                    <h3 class="panel-title">Email</h3>
                </div>
                <div class="panel-body">
                    <c:out value="${user.email}"/>
                </div>
            </div>
        </div>

        <div class="col-md-9 admin-content" id="change-password">
            <form action="profile" method="post">
                <div class="panel panel-info" style="margin: 1em;">
                    <div class="panel-heading">
                        <h3 class="panel-title"><label for="new_password" class="control-label panel-title">New
                            Password</label></h3>
                    </div>
                    <div class="panel-body">
                        <div class="form-group">
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="inputPassword" id="new_password">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-info" style="margin: 1em;">
                    <div class="panel-heading">
                        <h3 class="panel-title"><label for="confirm_password" class="control-label panel-title">Confirm
                            password</label></h3>
                    </div>
                    <div class="panel-body">
                        <div class="form-group">
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="password_confirmation"
                                       id="confirm_password">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-info border" style="margin: 1em;">
                    <div class="panel-body">
                        <div class="form-group">
                            <div class="pull-left">
                                <input type="submit" class="form-control btn btn-primary" name="submit" id="submit">
                            </div>
                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>


<footer class="footer">
    <div class="container">
        <p class="navbar-text">ShareYourFind - developed by <a target="_blank" href="https://github.com/Wheezyx">Mateusz
            Wedeł</a></p>
    </div>
</footer>

<script src="http://code.jquery.com/jquery-1.11.2.min.2"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/profile.js"></script>
</body>
</html>
