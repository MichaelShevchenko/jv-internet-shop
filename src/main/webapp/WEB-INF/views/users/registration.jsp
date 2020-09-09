<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Welcome! Please, provide your users data.</h1>

<h4 style="color: red">${message}</h4>

<form method="post" action="${pageContext.request.contextPath}/users/registration">
    Please, provide your login: <input type="text" name="login" required="*"><p>
    Please, provide your name: <input type="text" name="name" required="*"><p>
    Please, provide your password: <input type="password" name="pwd" required="*"><p>
    Please, repeat your password to confirm it: <input type="password" name="pwd-confirm" required="*">

    <button type="submit">Register</button>
</form>

</body>
</html>
