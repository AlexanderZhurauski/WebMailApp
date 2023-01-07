
<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         pageEncoding="UTF-8"%>


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
<form action="${pageContext.request.contextPath}/api/message" method="post">
    <h2>User ${login}'s Mailbox: Send a Message</h2>
    <label for="recipient">Message recipient:</label><br>
    <input type="text" id="recipient" name="recipient"><br>
    <br>
    <label for="text">Message Text:</label><br>
    <textarea id="text" name="text" rows="10" cols="50"></textarea><br>
    <br>
    <input type="submit" value="Send">
</form>
<form action="${ContextPath}/ui">
    <button>Main page</button>
</form>
</body>
</html>

