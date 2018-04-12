<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="show-error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
<fmt:setLocale value="${sessionScope.locale eq null ? 'en_US' : sessionScope.locale}"/>
<c:if test="${sessionScope.locale eq null}">
    <c:set var="locale" value="en_EN" scope="session"/>
</c:if>
<fmt:setBundle basename="lang" var="bundle"/>
<html>
<head>
    <title>Quiz</title>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="shortcut icon" type="image/png" href="./quiz_favicon.jpg"/>
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="pages.css">
</head>
<body>
<div class="container-fluid">
    <p><fmt:message key="Login" bundle="${bundle}"/>: ${user.login}</p>
    <p><fmt:message key="Name" bundle="${bundle}"/>: ${user.name}</p>

    <h2 class="header"><b><fmt:message key="Quiz" bundle="${bundle}"/>: ${quiz.name}</b></h2>
    <ul>
        <li><fmt:message key="Description" bundle="${bundle}"/>: ${quiz.description}</li>
        <form action="./question.do?id=${firstId}" method="post">
            <c:choose>
                <c:when test="${completed eq true}">
                    <div class="alert alert-danger col-4" role="alert">
                        <fmt:message key="You've_already_passed_this_quiz" bundle="${bundle}"/>
                    </div>
                </c:when>
                <c:otherwise>
                    <input type="submit" class="btn btn-primary btn-margin"
                           value="<fmt:message key="Start" bundle="${bundle}"/>">
                </c:otherwise>
            </c:choose>
        </form>
    </ul>

    <a href="quizAll.do"><fmt:message key="Quizzes_list" bundle="${bundle}"/></a>

    <%--Add quiz to bucket--%>
    <p><a href="./quizAddToBucket.do?id=${quiz.id}">
        <fmt:message key="Add_this_quiz_to_bucket" bundle="${bundle}"/>
    </a></p>

    <%--Show quiz bucket--%>
    <h3 class="header"><b><fmt:message key="Bucket" bundle="${bundle}"/></b></h3>
    <ul>
        <c:forEach var="quizInBucket" items="${quizzesInBucket}">

            <li><a href="./quiz.do?id=${quizInBucket.id}">${quizInBucket.name}</a>(<a
                    href="./quizRemoveFromBucket.do?id=${quizInBucket.id}&redirectToId=${redirectToQuizId}">X</a>)
            </li>
        </c:forEach>
    </ul>
    <p><a href="index.jsp"><fmt:message key="Main_page" bundle="${bundle}"/></a></p>
    <p><a href="logout.do?redirectTo=quiz.do!id*${quiz.id}"><fmt:message key="Logout" bundle="${bundle}"/></a></p>
</div>
</body>
</html>
