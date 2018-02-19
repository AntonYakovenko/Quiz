<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/17/2018
  Time: 12:53 PM
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Register</title>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <link rel="stylesheet" href="pages.css">
</head>

<body>
<h2 class="header"><fmt:message key="Registration" bundle="${bundle}"/></h2>
<form action="register" method="post" enctype="application/x-www-form-urlencoded">
    <label for="login"><fmt:message key="Login" bundle="${bundle}"/>:</label>
    <br><input name="login" id="login" type="text" value="${login}" placeholder
        ="<fmt:message key="Enter_login" bundle="${bundle}"/>..."/><span class="error"> ${errorMap.login}</span>

    <br><label for="name"><fmt:message key="Name" bundle="${bundle}"/>:</label>
    <br><input name="name" id="name" type="text" value="${name}" placeholder
        ="<fmt:message key="Enter_name" bundle="${bundle}"/>..."/><span class="error"> ${errorMap.name}</span>

    <br><label for="password"><fmt:message key="Password" bundle="${bundle}"/>:</label>
    <br><input name="password" id="password" type="password" value="${password}" placeholder
        ="<fmt:message key="Enter_password" bundle="${bundle}"/>..."/> <span class="error"> ${errorMap.password}</span>

    <br><label for="email"><fmt:message key="Email" bundle="${bundle}"/>:</label>
    <br><input name="email" id="email" type="text" value="${email}" placeholder
        ="<fmt:message key="Enter_email" bundle="${bundle}"/>..."/> <span class="error"> ${errorMap.email}</span>

    <br><br><input type="submit" value="<fmt:message key="submit" bundle="${bundle}"/>"/>
    <input type="reset" value="reset">
</form>
<p><a href="index.jsp"><fmt:message key="Main_page" bundle="${bundle}"/></a></p>
</body>
</html>
