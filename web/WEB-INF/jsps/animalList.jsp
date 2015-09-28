<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
    <title>Animals List</title>
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
<%--<div id="header">--%>
<%--Hi ${user.firstName}, you are ${user.role.roleName}.--%>
<%--<a class="logOut" href="<c:url value="/logOut" />">logout</a>--%>
<%--</div>--%>
<fieldset>
    <legend>List of animals:</legend>
    <table>
        <tr>
            <th>Animal name</th>
            <th>Animal price</th>
            <th>Food</th>
            <th>Accs</th>
            <th>Type</th>
                <th>Action</th>
        </tr>
        <c:forEach var="animal" items="${animalList}">
            <p>
                <tr>
                    <td>${animal.name}</td>
                    <td>${animal.price}</td>
                    <td>${animal.food.title}</td>
                    <td>${animal.accs.title}</td>
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
        <input type="button" onclick="location.href='/create_animal';" value="Add new user" />
        <input type="button" onclick="location.href='/accs_list';" value="Edit accs" />
        <input type="button" onclick="location.href='/food_list';" value="Edit food" />
</fieldset>
</body>
</html>