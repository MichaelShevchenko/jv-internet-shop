<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login page</h1>
<h4 style="color: red">${errorMessage}</h4>

<form method="post" action="${pageContext.request.contextPath}/login">
    Please, provide your login: <input type="text" name="login" required="*"><p>
    Please, repeat your password to confirm it: <input type="password" name="pwd" required="*">

    <button type="submit">Login</button>
</form>

<c:out value="New at our shop?" /> &emsp;
<a href="${pageContext.request.contextPath}/registration">Register!</a>
</body>
</html>
