
<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 27.09.2022
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h2>Основной контент страницы</h2>
    </div>
    <div id="clear"></div>
    <jsp:include page="/include/footer.jsp"/>
</div>
</body>
</html>
