<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.11.2017
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>ShareYourFind</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/login.css" type="text/css" rel="stylesheet">
</head>
<body>


<jsp:include page="fragment/navbar.jspf"/>

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Sign in to continue to ShareYourFind</h1>
            <div class="account-wall">
                <img class="profile-img"
                     src="https://cs.rin.ru/forum/download/file.php?avatar=723065_1497742853.png"
                     alt="">
                <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/j_security_check'}">
                    <div class="alert alert-danger">
                        <strong>Your username or password is incorrect. Please try again.</strong>
                    </div>
                </c:if>
                <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/vote'}">
                    <div class="alert alert-danger">
                        <strong>You need to login to vote!</strong>
                    </div>
                </c:if>
                <form class="form-signin" action="j_security_check" method="post">
                    <input name="j_username" type="text" class="form-control" placeholder="Username" required autofocus>
                    <input name="j_password" type="password" class="form-control" placeholder="Password" required>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        Sign in
                    </button>
                </form>
            </div>
            <a href="/register" class="text-center new-account">Create an account</a>
        </div>
    </div>
</div>

<jsp:include page="fragment/footer.jspf"/>

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="../resources/js/bootstrap.js"></script>
</body>
</html>
