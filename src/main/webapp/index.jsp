<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="pages.css">
</head>

<body>
<p>
<h1 class="header">
    Welcome
    <c:if test="${sessionScope.user ne null}">
        , ${sessionScope.user.name}
    </c:if>
</h1>
<p>
<h2 class="blueHeader">to world of quizzes</h2>
<hr>
<c:if test="${sessionScope.user eq null}">
    <p><a href="register">Registration</a></p>
    <p><a href="login.jsp">Login</a></p>
</c:if>
<p><a href="quizAll.do">All quizzes</a></p>
<c:if test="${sessionScope.user ne null}">
    <p><a href="logout.do">LOGOUT</a></p>
</c:if>
</body>
</html>
