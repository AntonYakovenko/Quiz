<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale eq null ? 'en_US' : sessionScope.locale}"/>
<c:if test="${sessionScope.locale eq null}">
    <c:set var="locale" value="en_EN" scope="session"/>
</c:if>
<fmt:setBundle basename="lang" var="bundle"/>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="pages.css">
</head>

<body>
<p>
<h1 class="header">
    <fmt:message key="Welcome" bundle="${bundle}"/>
    <c:if test="${sessionScope.user ne null}">
        , ${sessionScope.user.name}
    </c:if>
</h1>
<p>
<h2 class="blueHeader"><fmt:message key="to_world_of_quizzes" bundle="${bundle}"/> </h2>

<form action="/language" method="post">
    <fmt:message key="language" bundle="${bundle}"/>:
    <select name="language">
        <option value="en_US">english</option>
        <option value="uk_UA">українська</option>
        <option value="ru_RU">русский</option>
    </select>
    <input type="submit" value="<fmt:message key="select" bundle="${bundle}"/>"/>
</form>

<c:if test="${sessionScope.user eq null}">
    <p><a href="register"><fmt:message key="Registration" bundle="${bundle}"/></a></p>
    <p><a href="login.jsp"><fmt:message key="LogIn" bundle="${bundle}"/></a></p>
</c:if>
<p><a href="quizAll.do"><fmt:message key="All_quizzes" bundle="${bundle}"/></a></p>
<c:if test="${sessionScope.user ne null}">
    <p><a href="logout.do"><fmt:message key="Logout" bundle="${bundle}"/></a></p>
</c:if>
</body>
</html>
