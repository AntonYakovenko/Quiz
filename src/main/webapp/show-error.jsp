<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/11/2018
  Time: 11:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>

<head>
    <title>Show-error</title>
</head>

<body>
<p>Exception is <%=exception%></p>
<%--<p>Exception stack trace:<% exception.printStackTrace(response.getWriter()); %></p>--%>
</body>

</html>