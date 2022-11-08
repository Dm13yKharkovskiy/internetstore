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
            <c:if test="${info != null}">
                <p class="error-message"><c:out value="${info}"/></p>
            </c:if>
        <div>
            <p>Чтобы подтдтвердить заказ, необходимо ввести код, который отправляется на почту</p>
            <p>Для этого рядом с нужным заказом нажмите "Отправить код", после чего заполните:</p>
            <form class="form-sing-in" action="/confirm-order" method="post">
                № Заказа
                <br>
                <input name="id_order" type="text">
                <br>
                Код подтверждения
                <br>
                <input name="confirm_code" type="text">
                <br>
                <br>
                <button type="submit" >Подтвердить заказ</button>
            </form>
        </div>
        <br>
        <table class="table-users">
            <caption>Заказы</caption>
            <tr>
                <th>№ Заказа</th>
                <th>Общая сумма $</th>
                <th>Кол. товаров</th>
                <th>Статус</th>
                <th>Отправка кода подтверждения</th>
            </tr>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.idOrder}</td>
                    <td>${order.amountOrder}</td>
                    <td>${order.countProducts}</td>
                    <td>${order.statusOrder}</td>
                    <td>
                        <c:choose>
                            <c:when test="${order.statusOrder == 'Требует подтв'}">
                                <form class="form-edit-user" action="/send-confirm-code" method="post">
                                    <button type="submit" name="idOrder" value="${order.idOrder}">Отправить</button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <p>Заказ уже подтвержден</p>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="clear"></div>
    <jsp:include page="/include/footer.jsp"/>
</body>
</html>