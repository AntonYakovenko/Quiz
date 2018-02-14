<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/12/2018
  Time: 3:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
    <link rel="stylesheet" href="pages.css">
</head>
<body>
<h2 class="header">Registration page</h2>
<form action="login.do?redirectTo=${param.redirectTo}" method="post"
      enctype="application/x-www-form-urlencoded">
    <label for="login">Login:</label>
    <br><input name="login" id="login" type="text" placeholder="Enter login..."/>
    <br><label for="password">Password:</label>
    <br><input name="password" id="password" type="password" placeholder="Enter password..."/>
    <br><br><input type="submit"/>
</form>
<p class="error">${wrongAuthorization}</p>
// you can be Mike/123 or Sara/123
</body>
</html>
