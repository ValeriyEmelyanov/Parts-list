<%--
  User: Valera
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Список деталей</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="<c:url value="/resources/partList.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
    <h1>Список деталей</h1>
    <table>
        <tr>
            <td>
                <form action="/" accept-charset="UTF-8" method="GET">
                    <input class="findInput" type="text" name="searchName" id="searchName" value="${searchName}">
                    <input type="hidden" name="filter" id="filter" value="NAME_SEARCH">
                    <input type="submit" value="Найти">
                </form>
            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td class="filter">
                Фильтр:&nbsp
                <c:url value="/" var="url">
                    <c:param name="filter" value="ALL"/>
                </c:url>
                <a href="${url}">Все</a>
                <c:url value="/" var="url">
                    <c:param name="filter" value="ESSENTIAL"/>
                </c:url>
                <a href="${url}">Необходимые</a>
                <c:url value="/" var="url">
                    <c:param name="filter" value="OPTIONAL"/>
                </c:url>
                <a href="${url}">Опциональные</a>
                <i>(текущий ${filter.title})</i>
            </td>
        </tr>
    </table>
    <table class="list">
        <tr>
            <th>Наименование</th>
            <th>Необходимость</th>
            <th>Количество</th>
            <th>Команда</th>
        </tr>
        <c:forEach var="part" items="${partsList}">
            <tr>
                <td class="bordered">${part.name}</td>
                <td class="bordered">${part.essential ? 'Да' : 'Нет'}</td>
                <td class="bordered">${part.quantity}</td>
                <td class="bordered">
                    <c:url value="/edit/${part.id}" var="url">
                        <c:param name="page" value="${page}"/>
                        <%--                        <c:param name="filter" value="${filter}"/>--%>
                        <%--                        <c:param name="searchName" value="${searchName}"/>--%>
                    </c:url>
                    <a href="${url}">Изменить</a>
<%--                    <a href="/edit/${part.id}">Изменить</a>--%>
                    <c:url value="/delete/${part.id}" var="url">
                        <c:param name="page" value="${page}"/>
<%--                        <c:param name="filter" value="${filter}"/>--%>
<%--                        <c:param name="searchName" value="${searchName}"/>--%>
                    </c:url>
                    <a href="${url}">Удалить</a>
<%--                    <a href="/delete/${part.id}">Удалить</a>--%>

                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td class="bordered">Можно собрать</td>
            <td class="bordered">${ability}</td>
            <td class="bordered" colspan="2">компьютеров</td>
        </tr>
    </table>
    <p>Страницы:&nbsp
        <c:forEach begin="1" end = "${pagesCount}" step="1" varStatus="i">
            <c:url value="/" var="url">
                <c:param name="page" value="${i.index}"/>
                <c:param name="filter" value="${filter}"/>
                <c:param name="searchName" value="${searchName}"/>
            </c:url>
            <a href="${url}">${i.index}</a>
        </c:forEach>
        &nbsp;<i>(текущая ${page})</i>
    </p>
    <c:url value="/add" var="add">
        <c:param name="page" value="${page}"/>
    </c:url>
    <a href="${add}">Добавить новую деталь</a>
</body>
</html>
