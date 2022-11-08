<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 15.09.2022
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <title>Add product</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:url value="css/style-block.css" var="myCss">
        <c:param name="version" value="${initParam.resourceVersion}"/>
    </c:url>
    <link rel="stylesheet" href="${myCss}" type="text/css"/>
</head>
<body>
<div id="container">
    <jsp:include page="/include/header.jsp"/>
    <jsp:include page="/include/navigation.jsp"/>
    <jsp:include page="/include/sidebar.jsp"/>
    <div id="content">
        <p>Для добавления товара заполните поля:</p>
        <form id="add-form" action="/add-product" method="post">
            <br>
            Название товара
            <br>
            <input name="name" type="text">
            <br>
            Цена
            <br>
            <input name="price" type="text">
            <br>
            Описание
            <br>
            <textarea name="description" rows="4" cols="50"></textarea>
            <br>
            <br>
            <button type="submit">Добавить товар</button>
        </form>
    </div>
    <div id="clear"></div>
    <jsp:include page="/include/footer.jsp"/>
</body>
</html>
