<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 17-6-6
  Time: 上午11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>添加用户</h1>
    <hr/>
    <form:form action="/home/users/addUserP" method="post" role="form">
        <div class="form-group">
            <label>Nickname:</label>
            <input type="text" class="form-control" id="nickname" name="nickname"
                   placeholder="Enter NICKNAME"/>
        </div>

        <div class="form-group">
            <label>First Name:</label>
            <input type="text" class="form-control" id="firstName" name="firstName"
                   placeholder="Enter First Name"/>
        </div>

        <div class="form-group">
            <label>Last Name:</label>
            <input type="text" class="form-control" id="lastName" name="lastName"
                   placeholder="Enter Last Name"/>
        </div>

        <div class="form-group">
            <label>Password</label>
            <input type="text" class="form-control" id="password" name="password"
                   placeholder="Enter Password"/>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">提交</button>
        </div>
    </form:form>
</body>
</html>
