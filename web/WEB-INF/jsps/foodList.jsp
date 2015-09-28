<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Roles List</title>
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
    <legend>List of feeds:</legend>
    <table>
        <tr>
            <th>Item name</th>
            <th>Action</th>
        </tr>
        <c:forEach var="food" items="${foodList}">
            <p>
                <tr>
                    <td>${food.itemName}</td>
                    <td>
                        <a href="
      <c:url value="/update_food">
        <c:param name="id" value="${food.id}"/>
      </c:url>
      " >Edit</a>

                        <a href="
      <c:url value="/delete_food">
        <c:param name="id" value="${food.id}"/>
      </c:url>
      " >Delete</a>
                    </td>
                </tr>
            </p>
        </c:forEach>
    </table>
    <br>
    <input type="button" onclick="location.href='/create_food';" value="Add new feed" />
</fieldset>
</body>
</html>