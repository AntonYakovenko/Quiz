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
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="shortcut icon" type="image/png" href="./quiz_favicon.jpg"/>
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="pages.css">
    <title>AllQuizzes</title>
</head>
<body>
<div class="container-fluid">
    <h2 class="header"><b><fmt:message key="Quizzes_list" bundle="${bundle}"/></b></h2>
    <p><fmt:message key="Hello" bundle="${bundle}"/>, ${user.login}</p>
    <ul>
        <c:forEach var="quiz" items="${quizzesSimpleInfo}">
            <li><a href="quiz.do?id=${quiz.id}">${quiz.name}</a>
                <span class="completed">${sessionScope.completedQuizzes[quiz.id]}</span></li>
        </c:forEach>
    </ul>
    <p><a href="index.jsp"><fmt:message key="Main_page" bundle="${bundle}"/></a></p>
    <p><a href="logout.do?redirectTo=quizAll.do"><fmt:message key="Logout" bundle="${bundle}"/></a></p>
</div>
</body>
</html>
