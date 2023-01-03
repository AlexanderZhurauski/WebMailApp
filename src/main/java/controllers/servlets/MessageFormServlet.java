package controllers.servlets;

import dto.UserSessionDTO;

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

        UserSessionDTO user = (UserSessionDTO) req.getSession()
                .getAttribute("user");
        String login = user.getLogin();
        req.setAttribute("login", login);

        getServletContext().getRequestDispatcher("messageform.jsp")
                .forward(req, resp);
    }
}
