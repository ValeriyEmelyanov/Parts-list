<%--
  User: Valera
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty part.name}">
        <title>Добавление</title>
    </c:if>
    <c:if test="${!empty part.name}">
        <title>Изменение</title>
    </c:if></head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="<c:url value="/resources/editPage.css"/>" rel="stylesheet" type="text/css"/>
    <script language="javascript">
        function validateForm() {
            var n = document.forms["edit"]["quantity"].value;
            if (/[^[0-9]/.test(n)) {
                alert("В поле Количество должно быть введено число!");
                return false;
            }
            return true;
        }
    </script>
<body>
    <h1>Деталь</h1>
    <c:if test="${empty part.name}">
        <c:url value="/add" var="var"/>
    </c:if>
    <c:if test="${!empty part.name}">
        <c:url value="/edit" var="var"/>
    </c:if>
    <div class="main">
    <form id="edit" action="${var}" accept-charset="UTF-8" onsubmit='return validateForm();' method="POST">
        <input form="edit" type="hidden" name="page" value="${page}">
        <c:if test="${!empty part.name}">
            <input form="edit" type="hidden" name="id" value="${part.id}">
        </c:if>
        <div class="field">
            <label for="name">Наименование</label>
            <input form="edit" class="inputfield" type="text" name="name" id="name" value="${part.name}" required>
        </div>
        <div class="field">
            <label>Необходимость</label>
            <c:if test="${part.essential == true}">
                <input form="edit" type="radio" name="essential" value="true" checked>Да
                <input form="edit" type="radio" name="essential" value="false">Нет
            </c:if>
            <c:if test="${part.essential != true}">
                <input form="edit" type="radio" name="essential" value="true">Да
                <input form="edit" type="radio" name="essential" value="false" checked>Нет
            </c:if>
        </div>
        <div class="field">
            <label for="quantity">Количество</label>
            <input form="edit" class="inputfield" type="text" name="quantity" id="quantity" value="${part.quantity}" pattern="[0-9]{,10}">
        </div>
        <p>
        <c:if test="${empty part.name}">
            <input form="edit" type="submit" value="Добавить деталь">
        </c:if>
        <c:if test="${!empty part.name}">
            <input form="edit" type="submit" value="Изменить">
        </c:if>
        </p>
    </form>
    <p>
        <c:url value="/" var="url">
            <c:param name="page" value="${page}"/>
            <c:param name="filter" value="${filter}"/>
            <c:param name="searchName" value="${searchName}"/>
        </c:url>
        <a href="${url}">Вернуться в список деталей</a>
    </p>
    </div>
</body>
</html>
