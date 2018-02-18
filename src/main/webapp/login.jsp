<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/12/2018
  Time: 3:11 PM
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
    <title>Login</title>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
    <link rel="stylesheet" href="pages.css">
</head>
<body>
<h2 class="header"><fmt:message key="Login_page" bundle="${bundle}"/></h2>
<form action="login.do?redirectTo=${param.redirectTo}" method="post"
      enctype="application/x-www-form-urlencoded">
    <label for="login"><fmt:message key="Login" bundle="${bundle}"/>:</label>
    <br><input name="login" id="login" type="text" placeholder
        ="<fmt:message key="Enter_login" bundle="${bundle}"/>..."/> ${errorMap.login}
    <br><label for="password"><fmt:message key="Password" bundle="${bundle}"/>:</label>
    <br><input name="password" id="password" type="password" placeholder
        ="<fmt:message key="Enter_password" bundle="${bundle}"/>..."/> ${errorMap.password}
    <br><br><input type="submit" value="<fmt:message key="submit" bundle="${bundle}"/>"/>
</form>
<p><a href="register"><fmt:message key="Create_new_account" bundle="${bundle}"/></a></p>
<p><a href="index.jsp"><fmt:message key="Main_page" bundle="${bundle}"/></a></p>
</body>
</html>
