<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>

<html>
    <head>
        <title>SignUp Form</title>
        <style>
            form {
                text-align: center;
            }
        </style>
    </head>

    <body>
        <form action="${pageContext.request.contextPath}/api/user" method="post">
            <h2>Please, fill out the form below to register in application</h2>

            Login: <label for="login"></label>
            <input type="text" id="login" name="login"><br>
            <br>

            Password: <label for="password"></label>
            <input type="text" id="password" name="password"><br>
            <br>

            FirstName: <label for="firstName"></label>
            <input type="text" id="firstName" name="firstName"><br>
            <br>

            LastName: <label for="lastName"></label>
            <input type="text" id="lastName" name="lastName"><br>
            <br>

            Patronymic: <label for="patronymic"></label>
            <input type="text" id="patronymic" name="patronymic"><br>
            <br>

            Birthday (dd.MM.yyyy): <label for="birthday"></label>
            <input type="text" id="birthday" name="birthday"><br>
            <br>

            <input type="submit" value="Send">
        </form>
    </body>
</html>