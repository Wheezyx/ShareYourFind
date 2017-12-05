<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.11.2017
  Time: 00:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add new</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<!--HEADER-->
<jsp:include page="fragment/navbar.jspf"/>

<!--Adding formula-->

<div class="container">
    <div class="col-md-8 col-md-offset-2">
        <c:if test="${banOperation != null}">
            <div class="alert alert-warning">
                <strong>Danger!</strong> <c:out value="${banOperation}"/>
            </div>
        </c:if>
        <c:if test="${deleteFindOperation != null}">
            <div class="alert alert-warning">
                <strong>Danger!</strong> <c:out value="${deleteFindOperation}"/>
            </div>
        </c:if>
        <form class="form-signin" method="post" action="admin">
            <h2 class="form-signin-heading">Manage your users</h2>
            <input name="inputUsername" type="text" class="form-control" placeholder="Type username"/>
            <input name="inputEmail" type="email" class="form-control" maxlength="35" placeholder="or email"
                   autofocus/>
            <select class="form-control numberColor" name="option">
                <option value="ban">Ban</option>
                <option value="unban">Unban</option>
            </select>
            <br>
            <h2 class="form-signin-heading">Delete any find?</h2>
            <input name="inputFindName" type="text" class="form-control" placeholder="Type name"
                   autofocus/>
            <input class="btn btn-lg btn-primary btn-block" type="submit"
                   value="Do it!"/>
        </form>
    </div>
</div>

<!--FOOTER-->

<jsp:include page="fragment/footer.jspf"/>

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
