<jsp:useBean id="quiz" scope="request" type="com.company.quiz.entity.Quiz"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="show-error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="pages.css">
    <title>Quiz</title>
    <%--<%@ include file="WEB-INF/jspf/cssinclude.jspf"%>--%>
</head>
<body>
<p>Login: ${user.login}</p>
<p>Name: ${user.name}</p>

<h2 class="header"><span style="text-decoration: underline;">Quiz</span>: ${quiz.name}</h2>
<ul>
    <li>Intro: ${quiz.intro}</li>
    <li>Questions:
        <ul>
            <c:forEach var="question" items="${quiz.questions}">
                <li><a href="./question.do?id=${question.id}&quizId=${quiz.id}">${question.id}</a></li>
            </c:forEach>
        </ul>
    </li>
    <li>Themes:
        <c:forEach var="theme" items="${quiz.themes}">
            <a href="theme.do?id=${theme.id}">${theme.name}</a>
        </c:forEach>
    </li>
</ul>

<a href="quizAll.do">All quizzes</a>

<%--Add quiz to bucket--%>
<p><a href="./quizAddToBucket.do?id=${quiz.id}">Add this quiz to bucket</a></p>

<%--Show quiz bucket--%>
<h2 class="header">Quizzes in bucket</h2>
<ul>
    <c:forEach var="quizInBucket" items="${quizzesInBucket}">
        <li><a href="./quiz.do?id=${quizInBucket.id}">${quizInBucket.name}</a>(<a
        href="./quizRemoveFromBucket.do?id=${quizInBucket.id}&redirectToId=${redirectToQuizId}">X</a>)</li>
    </c:forEach>
</ul>
<p><a href="logout.do?redirectTo=quiz.do!id*${quiz.id}">LOGOUT</a></p>
</body>
</html>
