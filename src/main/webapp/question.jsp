<%@ page import="java.io.PrintWriter" %>
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

<h2 class="header"><fmt:message key="Question" bundle="${bundle}"/> №${currId}</h2>
<hr>
${question.description}
<form action="./question.do?id=${nextQuestionId}" method="post">
    <c:forEach var="answer" items="${answers}">
        <p><input type="radio" name="answers" value="${answer.correct}">${answer.answer}</p>
    </c:forEach>
    <c:if test="${isLast eq false}">
        <input type="submit" value="<fmt:message key="Next" bundle="${bundle}"/>">
    </c:if>
    <c:if test="${isLast eq true}">
        <input type="submit" value="<fmt:message key="Finish" bundle="${bundle}"/>" formaction
                ="quiz.do?id=${question.quizId}">
    </c:if>
</form>

<%--<fmt:message key="Explanation" bundle="${bundle}"/>: ${question.explanation}--%>

<hr>
<%--<a href="quiz.do?id=${quiz}"><fmt:message key="All_questions" bundle="${bundle}"/></a>--%>
<%--<p><a href="index.jsp"><fmt:message key="Main_page" bundle="${bundle}"/></a></p>--%>
<p><a href="logout.do?redirectTo=question.do!id*${question.id}@quizId*${quiz}">LOGOUT</a></p>

</body>
</html>
