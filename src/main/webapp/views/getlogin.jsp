<%@ page import="dto.UserSessionDTO" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         pageEncoding="UTF-8"%>

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

