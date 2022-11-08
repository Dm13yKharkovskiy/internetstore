<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 11.09.2022
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Users</title>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <c:url value="/css/style-block.css" var="myCss">
        <c:param name="version" value="${initParam.resourceVersion}" />
    </c:url>
    <link rel="stylesheet" href="${myCss}" type="text/css" />
</head>
<body>
<div id="container">
    <jsp:include page="/include/header.jsp"/>
    <jsp:include page="/include/navigation.jsp"/>
    <jsp:include page="/include/sidebar.jsp"/>
    <div id="content">
        <table class="table-users">
            <caption>Пользователи</caption>
            <tr>
                <th>№</th>
                <th>Почта</th>
                <th>Пароль</th>
                <th>Права</th>
                <th>Удалить</th>
            </tr>
        <c:forEach var="user" items="${allUsers}">
            <tr>
                <td>${user.id}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>
                    <form class="form-remove-user" action="/users/remove" method="post">
                        <button type="submit" name="id" value="${user.id}">Удалить пользователя</button>
                    </form>
                    <form class="form-edit-user" action="/users/remove" method="post">
                        <button type="submit" name="id" value="${user.id}">Редактировать</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </table>
    </div>
        <div id="clear"></div>
        <jsp:include page="/include/footer.jsp"/>
</body>
</html>
