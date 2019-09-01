<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Books</title>

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
            background-color: #d8fffc;
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
<a href="<c:url value="/"/>">Вернуться в главное меню</a>

<br/>
<br/>

<h1>Список книг</h1>

<c:if test="${empty listBooks}">
    <h2 style="color:#ff0013">Книги данного автора не найдены</h2>
</c:if>

<c:if test="${!empty listBooks}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="160">Книга</th>
            <th width="60">Изменить</th>
            <th width="60">Удалить</th>
            <th width="60">Скачать</th>
            <th width="120">Просмотреть автора</th>
        </tr>
        <c:forEach items="${listBooks}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td><a href="<c:url value='/books/edit/${book.id}'/>">Изменить</a></td>
                <td><a href="<c:url value='/books/delete/${book.id}'/>">Удалить</a></td>
                <td><a href="<c:url value='/download/${book.id}'/>"> Скачать </a></td>
                <td><a href="<c:url value='/authors/${book.id}'/>"> Поиск </a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<c:url var="addBook" value="/books/add"/>

<%--@elvariable id="book" type="ru.newrishman.library.domain.Book"--%>
<form:form action="${addBook}" modelAttribute="book">


    <table>
        <c:if test="${!empty book.title}">
        <h3>Переименовать книгу</h3>
        <tr>
            <td>
                <form:label path="id">
                    <spring:message text="ID"/>
                </form:label>
            </td>
            <td>
                <form:input path="id" readonly="true" size="8" disabled="true"/>
                <form:hidden path="id"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="title">
                    <spring:message text="Название"/>
                </form:label>
            </td>
            <td>
                <form:input path="title"/>
            </td>
            <td colspan="2">
                <input type="submit"
                       value="<spring:message text="Изменить"/>"/>
            </td>
            </c:if>

        </tr>
    </table>
</form:form>


<form method="POST" action="books/upload" enctype="multipart/form-data">
    <h2>Сохранить книгу</h2>
    <%-- "&nbsp" - сделал для выравнивания вводимой информации по вертикали, ибо некрасиво иначе--%>
    <%-- да, я понимаю, что это на грани говнокода, но я не верстальщик =) --%>
    Автор:&nbsp&nbsp&nbsp&nbsp&nbsp <input type="text" name="name"><br/>
    Название: <input type="text" name="title"><br/>
    Книга:&nbsp&nbsp&nbsp&nbsp&nbsp <input type="file" name="file"><br/> <br/>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
