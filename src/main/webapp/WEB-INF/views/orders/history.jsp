<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders history</title>
</head>
<body>
<h1>Welcome to your orders history page</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Products</th>
        <th>Details</th>
    </tr>
    <c:forEach var="order" items="${orders}">
    <tr>
        <td>
            <c:out value="${order.id}"/>
        </td>
        <td>
            <c:out value="${order.products}"/>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/orders/details?id=${order.id}">Details</a>
        </td>
    </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/products/all">Go to products catalog</a>
</body>
</html>
