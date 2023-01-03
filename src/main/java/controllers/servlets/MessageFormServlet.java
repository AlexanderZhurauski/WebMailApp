package controllers.servlets;

import dto.UserSessionDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MessageFormServlet", urlPatterns = "/ui/user/message")
public class MessageFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("login", getLogin(req.getSession()));

        getServletContext().getRequestDispatcher("messageform.jsp")
                .forward(req, resp);
    }

    private String getLogin(HttpSession session) {
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
    }
}
