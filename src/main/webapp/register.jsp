<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/17/2018
  Time: 12:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <link rel="stylesheet" href="pages.css">
</head>

<body>
<h2 class="header">Register new User</h2>
<form action="register" method="post" enctype="application/x-www-form-urlencoded">
    <label for="login">Login:</label>
    <br><input name="login" id="login" type="text" value="${login}"
               placeholder="Enter login..."/> ${errorMap.login}

    <br><label for="name">Name:</label>
    <br><input name="name" id="name" type="text" value="${name}"
               placeholder="Enter name..."/> ${errorMap.name}

    <br><label for="password">Password:</label>
    <br><input name="password" id="password" type="password" value="${password}"
               placeholder="Enter password..."/> ${errorMap.password}

    <br><label for="email">Email:</label>
    <br><input name="email" id="email" type="text" value="${email}"
               placeholder="Enter email..."/> ${errorMap.email}

    <br><br><input type="submit"/>
</form>

</body>
</html>
