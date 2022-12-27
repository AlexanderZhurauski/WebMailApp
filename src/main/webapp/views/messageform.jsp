
<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         pageEncoding="UTF-8"%>

<%@ include file="getlogin.jsp"%>

<html>
<head>
    <title>Message Form</title>
    <style>
        form {
            text-align: center;
        }
    </style>
</head>
<body>
<form action="/api/message" method="post">
    <h2>User <%= getLogin(session)%>'s Mailbox: Send a Message</h2>
    <label for="recipient">Message recipient:</label><br>
    <input type="text" id="recipient" name="recipient"><br>
    <br>
    <label for="message">Message Text:</label><br>
    <textarea id="message" name="message" rows="10" cols="50"></textarea><br>
    <br>
    <input type="submit" value="Send">
</form>
</body>
</html>

