<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Authors</title>

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

<h1>Все авторы</h1>

<c:if test="${!empty listAuthors}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="160">Автор</th>
            <th width="60">Изменить</th>
            <th width="60">Удалить</th>
            <th width="120">Просмотреть книги</th>
        </tr>
        <c:forEach items="${listAuthors}" var="author">
            <tr>
                <td>${author.id}</td>
                <td>${author.name}</td>
                <td><a href="<c:url value='/authors/edit/${author.id}'/>">Изменить</a></td>
                <td><a href="<c:url value='/authors/delete/${author.id}'/>">Удалить</a></td>
                <td><a href="<c:url value='/books/${author.id}'/>"> Поиск </a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<h2>Добавить автора</h2>

<c:url var="addAuthor" value="/authors/add"/>

<%--@elvariable id="author" type="ru.newrishman.library.domain.Author"--%>
<form:form action="${addAuthor}" modelAttribute="author">
    <table>
        <c:if test="${!empty author.name}">
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
        </c:if>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Автор: "/>
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>


        <tr>
            <td colspan="2">
                <c:if test="${!empty author.name}">
                    <input type="submit"
                           value="<spring:message text="Изменить"/>"/>
                </c:if>
                <c:if test="${empty author.name}">
                    <input type="submit"
                           value="<spring:message text="Добавить"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
