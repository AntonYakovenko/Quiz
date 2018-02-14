<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="pages.css">
    <title>AllQuizzes</title>
</head>
<body>
    <h2 class="header">QuizAll page</h2>
    <p>Hello, ${user.login}</p>
    <ul>
        <c:forEach var="quiz" items="${quizzesList}">
            <li><a href="quiz.do?id=${quiz.id}">${quiz.name}</a></li>
        </c:forEach>
    </ul>
</body>
</html>
