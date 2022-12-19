<%@ page import="dto.MessageRecipientDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="services.MessageService" %>
<%@ page import="dao.MessageMemoryDAO" %>
<%@ page import="services.api.IMessageService" %>
<%@ page import="dto.UserSessionDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% IMessageService service = new MessageService(new MessageMemoryDAO());
    List<MessageRecipientDTO> messageList = service.get(getLogin(session));%>

<html>
<head>
    <title>ReceivedMessages</title>
</head>
<body>
    <!-- TODO -->
</body>
</html>

<%! private String getLogin(HttpSession session) {
    UserSessionDTO user = (UserSessionDTO) session.getAttribute("user");
    if (user == null) {
        throw new IllegalStateException("No user is logged in");
    }
    String login = user.getLogin();
    if (login == null || login.isBlank()) {
        throw new IllegalStateException("User login data in the" +
                " session is corrupted");
    }
    return login;
} %>