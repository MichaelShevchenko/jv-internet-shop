<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding new product</title>
</head>
<body>
<h1>Welcome! Please, provide necessary data to create a new product.</h1>

<form method="post" action="${pageContext.request.contextPath}/products/add">
    Please, provide products name: <input type="text" name="name" required="*"><p>
    Please, provide products price: <input type="number" name="price" required="*"><p>

    <button type="submit">Create</button>
</form>

</body>
</html>
