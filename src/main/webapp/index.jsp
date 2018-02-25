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
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="pages.css">
</head>

<body>
<div class="container-fluid">
    <h1 class="header"><b>
        <fmt:message key="Welcome" bundle="${bundle}"/>
        <c:if test="${sessionScope.user ne null}">
            , ${sessionScope.user.name}
        </c:if>
    </b></h1>
    <p>
    <h2 class="header"><fmt:message key="to_world_of_quizzes" bundle="${bundle}"/></h2>

    <form action="/language" method="post">
        <fmt:message key="language" bundle="${bundle}"/>:
        <select name="language" class="custom-select col-2">
            <option selected><fmt:message key="choose" bundle="${bundle}"/>...</option>
            <option value="en_US">english</option>
            <option value="uk_UA">українська</option>
            <option value="ru_RU">русский</option>
        </select>
        <input type="submit" class="btn btn-primary" value="<fmt:message key="select" bundle="${bundle}"/>"/>
    </form>

    <c:if test="${sessionScope.user eq null}">
        <p><a href="register"><fmt:message key="Registration" bundle="${bundle}"/></a></p>
        <p><a href="login.jsp"><fmt:message key="LogIn" bundle="${bundle}"/></a></p>
    </c:if>
    <p><a href="quizAll.do"><fmt:message key="All_quizzes" bundle="${bundle}"/></a></p>
    <c:if test="${sessionScope.user ne null}">
        <p><a href="logout.do"><fmt:message key="Logout" bundle="${bundle}"/></a></p>
    </c:if>
</div>
</body>
</html>
