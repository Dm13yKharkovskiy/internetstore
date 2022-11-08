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
    <c:url value="css/style-block.css" var="myCss">
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
         <span>
            <c:if test="${message != null}">
                <c:out value="${message}"/>
            </c:if>
         </span>
    <table class="table-users">
        <caption>Каталог товаров</caption>
        <tr>
            <th>Название</th>
            <th>Цена $</th>
            <th>Описание</th>
            <th>Действия</th>
        </tr>
    <c:forEach var="product" items="${catalog}">
        <tr>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.description}</td>
            <td>
                <c:choose>
                    <c:when test="${sessionScope.user != null}">
                        <form class="form-edit-user" action="/add-cart" method="post">
                            <button type="submit" name="id" value="${product.id}">Добавить в корзину</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <p>Для добавления товара в козину войдите или зарегестрируйтесь</p>
                    </c:otherwise>
                </c:choose>
                <c:if test="${sessionScope.user.role == 'admin'}">
                <form class="form-remove-user" action="/remove-product" method="post">
                    <button type="submit" name="id" value="${product.id}">Удалить товар</button>
                </form>
                </c:if>
                <c:if test="${sessionScope.user.role == 'admin'}">
                    <form class="form-edit-user" action="/product/edit" method="get">
                        <button type="submit" name="id" value="${product.id}">Редактировать</button>
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </table>
    </div>
    <div id="clear"></div>
    <jsp:include page="/include/footer.jsp"/>
</body>
</html>