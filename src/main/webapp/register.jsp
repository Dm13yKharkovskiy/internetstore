<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 08.09.2022
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
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
        <c:if test="${error != null}">
            <p  class="error-message"><c:out value="${error}"/></p>
        </c:if>
    <p>Для регистрации заполните поля:</p>
    <form class="form-register" action="/register" method="post">
        Адрес электронной почты
        <br>
        <input name="email" type="email">
        <br>
        Пароль
        <br>
        <input name="password" type="password">
        <br>
        Повторите пароль
        <br>
        <input name="repeatPassword" type="password">
        <br>
        <br>
    <button type="submit">Подтвердить</button> <button type="reset">Очистить</button>
</form>
    </div>
        <div id="clear"></div>
        <jsp:include page="/include/footer.jsp"/>
</body>
</html>
