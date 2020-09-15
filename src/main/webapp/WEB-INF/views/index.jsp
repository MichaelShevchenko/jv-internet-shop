<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello world!</h1>

<a href="${pageContext.request.contextPath}/products/all">All products</a> &emsp;
<a href="${pageContext.request.contextPath}/orders/history">Orders history</a> &emsp;
<a href="${pageContext.request.contextPath}/shopping-carts/products">Shopping cart</a> &emsp;
<a href="${pageContext.request.contextPath}/users/all">All users</a> &emsp;
<a href="${pageContext.request.contextPath}/logout">Logout</a> <p>
<a href="${pageContext.request.contextPath}/inject-data">Injecting test data in to DB</a>
</body>
</html>
