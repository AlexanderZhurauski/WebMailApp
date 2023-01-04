<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>WebMail app</title>
    </head>
    <body>
        <h1>Welcome to main page</h1>
        <h2>Create a new account <a href="${pageContext.request.contextPath}/ui/signUp">Sign up</a></h2>
        <h2>Authenticate <a href="${pageContext.request.contextPath}/ui/signIn">Sign in</a></h2>
        <h2>View incoming messages <a href="${pageContext.request.contextPath}/ui/user/chats">Chats</a></h2>
        <h2>Send a message <a href="${pageContext.request.contextPath}/ui/user/message">Message</a></h2>
    </body>
</html>