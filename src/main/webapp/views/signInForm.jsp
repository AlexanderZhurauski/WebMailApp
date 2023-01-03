<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>SignIn Form</title>
    <style>
        form {
            text-align: center;
        }
    </style>
</head>
<body>
<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/api/login">
    <input type="hidden" name="command" value="login"/>
    Login:<br/>
    <input type="text" name="login" value=""/> <br/>
    Password:<br/>
    <input type="password" name="password" value=""/> <br/>
    <input type="submit" value="Log in"/>
</form>
<h3>Don't have an account yet? <a href="${pageContext.request.contextPath}/ui/signUp">Sign Up!</a></h3>
<hr/>
</body>
</html>
