<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 17-6-6
  Time: 下午2:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
<c:forEach var="user" items="${list}">
    <div>
        <label>id:</label>
        <c:out value="${user.id}"/>
        <label>,Nickname:</label>
        <c:out value="${user.nickname}"/>
        <label>,FirstName:</label>
        <c:out value="${user.firstName}"/>
        <label>,LastName:</label>
        <c:out value="${user.lastName}"/>
    </div>
</c:forEach>
</body>
</html>
