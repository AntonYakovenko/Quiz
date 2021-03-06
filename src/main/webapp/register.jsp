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
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="shortcut icon" type="image/png" href="./quiz_favicon.jpg"/>
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="pages.css">
</head>

<body>
<div class="container-fluid">
    <h2 class="header"><b><fmt:message key="Registration" bundle="${bundle}"/></b></h2>
    <form action="register" method="post" enctype="application/x-www-form-urlencoded">
        <div class="form-row">
            <div class="col-3">
                <label for="login"><fmt:message key="Login" bundle="${bundle}"/>:</label>
                <br><input name="login" id="login" type="text" class="form-control" placeholder
                    ="<fmt:message key="Enter_login" bundle="${bundle}"/>..."/>
                <c:if test="${errorMap.login ne null}"><span class="alert-danger"> ${errorMap.login}</span></c:if>
            </div>

            <div class="col-3">
                <label for="name"><fmt:message key="Name" bundle="${bundle}"/>:</label>
                <br><input name="name" id="name" type="text" class="form-control" placeholder
                    ="<fmt:message key="Enter_name" bundle="${bundle}"/>..."/>
                <c:if test="${errorMap.name ne null}"><span class="alert-danger"> ${errorMap.name}</span></c:if>
            </div>
        </div>

        <div class="form-row">
            <div class="col-3">
                <label for="password"><fmt:message key="Password" bundle="${bundle}"/>:</label>
                <br><input name="password" id="password" type="password" class="form-control"
                           placeholder="<fmt:message key="Enter_password" bundle="${bundle}"/>..."/>
                <c:if test="${errorMap.password ne null}"><span class="alert-danger"> ${errorMap.password}</span></c:if>
            </div>

            <div class="col-3">
                <label for="email"><fmt:message key="Email" bundle="${bundle}"/>:</label>
                <br><input name="email" id="email" type="text" class="form-control" placeholder
                ="<fmt:message key="Enter_email" bundle="${bundle}"/>..."/>
                <c:if test="${errorMap.email ne null}"><span class="alert-danger"> ${errorMap.email}</span></c:if>
            </div>
        </div>

        <br><input type="submit" class="btn btn-success" value="<fmt:message key="submit" bundle="${bundle}"/>"/>
        <input type="reset" class="btn btn-danger" value="<fmt:message key="reset" bundle="${bundle}"/>">
    </form>
    <p><a href="index.jsp"><fmt:message key="Main_page" bundle="${bundle}"/></a></p>
</div>
</body>
</html>
