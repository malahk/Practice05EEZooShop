<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Accs List</title>
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
    <legend>List of accs:</legend>
    <table>
        <tr>
            <th>Item name</th>
            <th>Action</th>
        </tr>
        <c:forEach var="accs" items="${accsList}">
            <p>
                <tr>
                    <td>${accs.accsName}</td>
                    <td>
                        <a href="
      <c:url value="/update_accs">
        <c:param name="id" value="${accs.id}"/>
      </c:url>
      " >Edit</a>

                        <a href="
      <c:url value="/delete_accs">
        <c:param name="id" value="${accs.id}"/>
      </c:url>
      " >Delete</a>
                    </td>
                </tr>
            </p>
        </c:forEach>
    </table>
    <br>
    <input type="button" onclick="location.href='/create_accs';" value="Add new accs" />
</fieldset>
</body>
</html>