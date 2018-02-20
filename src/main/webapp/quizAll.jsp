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
    <title>AllQuizzes</title>
</head>
<body>
<h2 class="header"><fmt:message key="Quizzes_list" bundle="${bundle}"/></h2>
<p><fmt:message key="Hello" bundle="${bundle}"/>, ${user.login}</p>
<ul>
    <c:forEach var="quiz" items="${quizzesSimpleInfo}">
        <li><a href="quiz.do?id=${quiz.id}">${quiz.name}</a> ${sessionScope.completedQuizzes[quiz.id]}</li>
    </c:forEach>
</ul>
<p><a href="index.jsp"><fmt:message key="Main_page" bundle="${bundle}"/></a></p>
<p><a href="logout.do?redirectTo=quizAll.do"><fmt:message key="Logout" bundle="${bundle}"/></a></p>
</body>
</html>
