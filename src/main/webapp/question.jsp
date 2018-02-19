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
    <link rel="stylesheet" type="text/css" href="pages.css">
    <title>Question</title>
</head>
<body>

<h2 class="header"><fmt:message key="Question" bundle="${bundle}"/> №${question.id}</h2>
<hr>
<ul>
    <li>Id: ${question.id}</li>
    <li><fmt:message key="Name" bundle="${bundle}"/>: ${question.name}</li>
    <li><fmt:message key="Description" bundle="${bundle}"/>: ${question.description}</li>
    <li><fmt:message key="Answers" bundle="${bundle}"/>: ${question.answers}</li>
    <li><fmt:message key="Explanation" bundle="${bundle}"/>: ${question.explanation}</li>
</ul>

<hr>
<a href="quiz.do?id=${quiz}"><fmt:message key="All_questions" bundle="${bundle}"/></a>
<p><a href="index.jsp"><fmt:message key="Main_page" bundle="${bundle}"/></a></p>
<p><a href="logout.do?redirectTo=question.do!id*${question.id}@quizId*${quiz}">LOGOUT</a></p>

</body>
</html>
