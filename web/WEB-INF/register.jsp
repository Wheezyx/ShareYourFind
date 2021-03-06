<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.11.2017
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>ShareYourFind - register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/login.css" type="text/css" rel="stylesheet">
</head>
<body>
<!--HEADER-->
<jsp:include page="fragment/navbar.jspf"/>

<!--Register formula-->

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Create your account!</h1>
            <div class="account-wall">
                <img class="profile-img"
                     src="https://cs.rin.ru/forum/download/file.php?avatar=723065_1497742853.png"
                     alt="">
                <c:if test="${existingUser != null}">
                    <div class="alert alert-danger">
                        <strong>Danger!</strong> <c:out value="${existingUser}"/>
                    </div>
                </c:if>
                <form class="form-signin" method="post" action="register">
                    <input name="inputEmail" type="email" class="form-control" maxlength="35" placeholder="Email"
                           required autofocus/>
                    <input name="inputUsername" type="text" class="form-control" maxlength="20" placeholder="Username"
                           required autofocus/>
                    <input name="inputPassword" type="password" class="form-control" maxlength="35"
                           placeholder="Password" id="password" required/>
                    <input name="inputPassword" type="password" class="form-control" maxlength="35"
                           placeholder="Confirm Password" id="confirm_password" required>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Sing up</button>
                </form>
            </div>
            Already have an account? <a href="/login" class="text-center">Log in!</a>
        </div>
    </div>
</div>

<!--FOOTER-->

<jsp:include page="fragment/footer.jspf"/>

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/repeatPassword.js"></script>
</body>
</html>
