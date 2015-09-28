<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update accs</title>
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
<div class="form">
    <fieldset>
        <legend align="center">Accs update:</legend>
        <form action="/update_accs" method="post">
            Item name:<input type="text" name="accsName" value="${accs.accsName}">
            <input type="hidden" name="id" value="${accs.id}">
            <input type="submit" value="Update">
            <input type="reset" value="Clean">
        </form>
    </fieldset>
</div>
</body>
</html>