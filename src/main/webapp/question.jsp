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
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="pages.css">
    <title>Question</title>
</head>
<body>
<div class="container-fluid">
    <c:if test="${question.id eq -1}">
        <div class="col-4">
            <div class="alert alert-success" role="alert">
                <h3 class="alert-heading"><fmt:message key="Well_done!" bundle="${bundle}"/></h3>
                <p><fmt:message key="You've_done_it_congratulations" bundle="${bundle}"/></p>
            </div>
        </div>
        <form action="quizAll.do" method="post">
            <input type="submit" class="btn btn-primary" value="<fmt:message key="Show_result" bundle="${bundle}"/>">
        </form>
    </c:if>

    <c:if test="${question.id ne -1}">

        <h2 class="header"><b><fmt:message key="Question" bundle="${bundle}"/> №${question.name}</b></h2>
        <h5>${question.description}</h5>
        <form action="./question.do?id=${nextQuestionId}" method="post">
            <c:forEach var="answer" items="${answers}">
                <div class="custom-control custom-radio">
                    <input type="radio" id="answer${answer.id}" name="answer" value="${answer.correct}"
                           class="custom-control-input">
                    <label class="custom-control-label" for="answer${answer.id}"> ${answer.answer}</label>
                </div>
            </c:forEach>
            <c:if test="${isLast eq false}">
                <input type="submit" class="btn btn-primary btn-margin" value="<fmt:message key="Next" bundle="${bundle}"/>">
            </c:if>
            <c:if test="${isLast eq true}">
                <input type="submit" class="btn btn-primary btn-margin" value="<fmt:message key="Finish" bundle="${bundle}"/>">
            </c:if>
        </form>

        <%--<fmt:message key="Explanation" bundle="${bundle}"/>: ${question.explanation}--%>

    </c:if>
    <p><a href="logout.do?redirectTo=question.do!id*${question.id}@quizId*${quiz}">
        <fmt:message key="Logout" bundle="${bundle}"/></a></p>
</div>
</body>
</html>
