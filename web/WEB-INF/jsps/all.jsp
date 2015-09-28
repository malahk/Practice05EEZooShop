<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 23.07.2015
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
    <title>User List</title>
    <style>
        table, th, td {
            border: 2px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
        }
    </style>
</head>
<body>
<h1>${message}</h1>
<fieldset>
    <legend>List of users:</legend>
    <table>
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Age</th>
            <th>Country</th>
            <th>Street</th>
            <th>Zip Code</th>
            <th>Login</th>
            <th>Password</th>
            <th>Action</th>
        </tr>
        <c:forEach var="animal" items="${animalList}">
            <p>
                <tr>
                    <td>${animal.name}</td>
                    <td>${animal.price}</td>
                    <td>${animal.food.itemName}</td>
                    <td>${animal.accessorie.accsName}</td>
                    <td>${animal.type}</td>
                        <td>
                            <a href=" <c:url value="/update_animal"> <c:param name="id" value="${animal.id}"/> </c:url>" >Edit</a>

                            <a href=" <c:url value="/delete_animal"> <c:param name="id" value="${animal.id}"/> </c:url>" >Delete</a>
                        </td>
                </tr>
            </p>
        </c:forEach>
    </table>
    <br>
        <input type="button" onclick="location.href='/create_animal';" value="Add new animal" />
        <input type="button" onclick="location.href='/list_food';" value="Edit food" />
        <input type="button" onclick="location.href='/role_accs';" value="Edit accessries" />
</fieldset>
</body>
</html>
