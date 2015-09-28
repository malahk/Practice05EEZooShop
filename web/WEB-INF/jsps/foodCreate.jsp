<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 22.07.2015
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new food</title>
    <style>
        legend {
            font-size: 150%;
            text-align: center;
        }
        div.form
        {
            display: block;
            text-align: center;
        }
        form
        {
            display: inline-block;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
        }
    </style>
</head>
<body>
<h1 align="center">${message}</h1>
<div class="form">
    <fieldset>
        <legend align="center">Create food:</legend>
        <form action="/create_food" method="post">
            Role Name:<input type="text" name="foodName" >
            <input type="submit" value="Create">
            <input type="reset" value="Clean">
            <input type="button" onclick="location.href='/food_list';" value="Update existing" />
        </form>
    </fieldset>
</div>
</body>
</html>