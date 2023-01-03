package controllers.servlets;

import dto.UserSessionDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MessageFormServlet", urlPatterns = "/ui/user/message")
public class MessageFormServlet extends HttpServlet {

    private final String LOGIN_PARAM_NAME = "login";
    private final String USER_ATTRIBUTE = "user";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        UserSessionDTO user = (UserSessionDTO) req.getSession()
                .getAttribute(USER_ATTRIBUTE);
        String login = user.getLogin();
        req.setAttribute(LOGIN_PARAM_NAME, login);

        getServletContext().getRequestDispatcher("/messageform")
                .forward(req, resp);
    }
}
