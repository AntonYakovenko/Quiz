<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale eq null ? 'en_US' : sessionScope.locale}"/>
<c:if test="${sessionScope.locale eq null}">
    <c:set var="locale" value="en_US" scope="session"/>
</c:if>
<fmt:setBundle basename="lang" var="bundle"/>

<html>
<head>
</head>
<body>
<div style="text-align: center;">
    <form action="/language" method="post">
        <p><fmt:message key="button" bundle="${bundle}"/></p>
        <fmt:message key="language" bundle="${bundle}"/>:
        <select name="language"/>">
        <option value="en_US">english</option>
        <option value="uk_UA">українська</option>
        <option value="ru_RU">русский</option>
        </select>
        <input type="hidden" name="address" value="${pageContext.request.requestURL}"/>
        <input type="submit" name="setLanguage" value="<fmt:message key="select" bundle="${bundle}"/>"/>
    </form>
</div>
</body>
</html>