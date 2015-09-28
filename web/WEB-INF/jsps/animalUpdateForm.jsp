<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Animal</title>
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
        <legend align="center">Update animal:</legend>
        <form action="/update_animal" method="post">
            Name:<br><input type="text" name="name" value="${animal.name}">
            <br>
            Price:<br><input type="text" name="price" value="${animal.price}">
            <br>
            Type:<br><input type="text" name="type" value="${animal.type}">
            <br>
            <input type="hidden" name="id" value="${animal.id}">
            <input type="hidden" name="foodId" value="${food.id}">
            <input type="hidden" name="accsId" value="${accessorie.id}">
            <input type="submit" value="Update">
            <input type="reset" value="Clean"><input type="button" onclick="location.href='/animal_list';" value="Update existing" />
        </form>
    </fieldset>
</div>
</body>
</html>