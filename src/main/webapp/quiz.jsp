<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="show-error.jsp" %>
<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
<fmt:setLocale value="${sessionScope.locale eq null ? 'en_US' : sessionScope.locale}"/>
<c:if test="${sessionScope.locale eq null}">
    <c:set var="locale" value="en_EN" scope="session"/>
</c:if>
<fmt:setBundle basename="lang" var="bundle"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="pages.css">
    <title>Quiz</title>
    <%--<%@ include file="WEB-INF/jspf/cssinclude.jspf"%>--%>
</head>
<body>
<p><fmt:message key="Login" bundle="${bundle}"/>: ${user.login}</p>
<p><fmt:message key="Name" bundle="${bundle}"/>: ${user.name}</p>

<h2 class="header"><span style="text-decoration: underline;"><fmt:message key="Quiz" bundle="${bundle}"/></span>: ${quiz.name}</h2>
<ul>
    <li><fmt:message key="Description" bundle="${bundle}"/>: ${quiz.description}</li>
    <form action="./question.do?id=${firstId}" method="post">
        <c:if test="${completed eq true}">
            <p><fmt:message key="You've_already_passed_this_quiz" bundle="${bundle}"/></p>
        </c:if>
        <c:if test="${completed eq false}">
            <input type="submit" value="<fmt:message key="Start" bundle="${bundle}"/>">
        </c:if>
    </form>
</ul>

<a href="quizAll.do"><fmt:message key="Quizzes_list" bundle="${bundle}"/></a>

<%--Add quiz to bucket--%>
<p><a href="./quizAddToBucket.do?id=${quiz.id}">
    <fmt:message key="Add_this_quiz_to_bucket" bundle="${bundle}"/>
</a></p>

<%--Show quiz bucket--%>
<h2 class="header"><fmt:message key="Bucket" bundle="${bundle}"/></h2>
<ul>
    <c:forEach var="quizInBucket" items="${quizzesInBucket}">
        <li><a href="./quiz.do?id=${quizInBucket.id}">${quizInBucket.name}</a>(<a
                href="./quizRemoveFromBucket.do?id=${quizInBucket.id}&redirectToId=${redirectToQuizId}">X</a>)
        </li>
    </c:forEach>
</ul>
<p><a href="index.jsp"><fmt:message key="Main_page" bundle="${bundle}"/></a></p>
<p><a href="logout.do?redirectTo=quiz.do!id*${quiz.id}"><fmt:message key="Logout" bundle="${bundle}"/></a></p>
</body>
</html>
