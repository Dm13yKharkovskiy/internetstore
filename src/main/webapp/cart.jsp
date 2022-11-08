<%@ page import="java.io.PrintWriter" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 9/17/2022
  Time: 3:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
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
        <br>
        <span>Сумма:</span>
        <div><c:if test="${amountCart != null}">
            <p class="error-message"><c:out value="${amountCart}"/> $</p>
            </c:if>
        </div>
        <form class="form-remove-user" action="/create-order" method="post">
            <button type="submit" name="idCart" value="${idCart}">Сформировать заказ</button>
        </form>
        <form class="form-remove-user" action="/orders" method="get">
            <button type="submit" name="idCart" value="${idCart}">Мои заказы</button>
        </form>
        <table class="table-users">
            <caption>Ваши товары</caption>
            <tr>
                <th>Название</th>
                <th>Цена $</th>
                <th>Описание</th>
                <th>Действия</th>
            </tr>
            <c:forEach var="product" items="${cart}">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.description}</td>
                    <td>
                        <form class="form-remove-user" action="/cart/remove-product" method="post">
                            <button type="submit" name="id" value="${product.id}">Удалить</button>
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