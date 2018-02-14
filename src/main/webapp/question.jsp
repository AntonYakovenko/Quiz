<jsp:useBean id="question" scope="request" type="com.company.quiz.entity.Question"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="pages.css">
    <title>Question</title>
</head>
<body>

<h2 class="header">Question №${question.id}</h2>
<hr>
<ul>
    <li>Id: ${question.id}</li>
    <li>Name: ${question.name}</li>
    <li>Intro: ${question.intro}</li>
    <li>Answers: ${question.answers}</li>
    <li>Explanation: ${question.explanation}</li>
</ul>

<hr>
<a href="quiz.do?id=${quiz}">All questions</a>

</body>
</html>
