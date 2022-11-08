<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 27.09.2022
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="sidebar">
    <ul class="side">
        <a href="/catalog">Все товары</a>
        <hr>
        <c:if test="${sessionScope.user.role == 'admin'}">
            <a href="/add-product">Добавить товар</a>
            <hr>
        <a href="/admin/users">Все пользователи</a>
        </c:if>
    </ul>
</div>
