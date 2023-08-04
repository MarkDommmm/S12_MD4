<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/3/2023
  Time: 1:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/CustomersServlet">
    <label for="id"></label>
    <input type="text" name="id" disabled id="id" value="${customer.id}">
    <input type="hidden" name="id" value="${customer.id}">
    <label for="name"></label>
    <input type="text" name="name" id="name" value="${customer.name}">
    <label for="email"></label>
    <input type="text" name="email" id="email" value="${customer.email}">
    <label for="address"></label>
    <input type="text" name="address" id="address" value="${customer.address}">
    <input type="submit" value="UPDATE" name="action">

</form>
</body>
</html>
