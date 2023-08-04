<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/3/2023
  Time: 1:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Create Customer</h1>
<form method="post" action="/CustomersServlet">

    <label for="name"></label>
    <input type="text" name="name" id="name"  >
    <label for="email"></label>
    <input type="text" name="email" id="email"  >
    <label for="address"></label>
    <input type="text" name="address" id="address"  >
    <input type="submit" value="ADD" name="action">

</form>
</body>
</html>
