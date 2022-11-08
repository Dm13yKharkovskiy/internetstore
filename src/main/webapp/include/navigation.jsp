<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 27.09.2022
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="nav" id="navigation">
  <a href="/cart">КОРЗИНА</a>
  <a href="/register">РЕГИСТРАЦИЯ</a>
  <c:choose>
    <c:when test="${sessionScope.user != null}">
      <a href="/sing-out">ВЫХОД</a>
    </c:when>
    <c:otherwise>
      <a href="/sing-in">ВХОД</a>
    </c:otherwise>
  </c:choose>
  <a href="/">ДОМОЙ</a>
</div>
