<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: safar
  Date: 28.08.2019
  Time: 3:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Home page</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<h1>Добро пожаловать в онлайн библиотеку! </h1>
<h2>Вы можете производить поиск по автору или книге.</h2>
<h2> Так же, Вы можете сохранить или загрузить книгу.</h2>

<br/>
<br/>

<h2>Выберите дальнейшее действие: </h2>
<table class="tg">

    <tr>
        <td><a href="<c:url value="/authors"/>">Перейти к авторам</a></td>
        <td><a href="<c:url value="/books"/>">Перейти к книгам</a></td>
    </tr>

</table>
</body>
</html>
