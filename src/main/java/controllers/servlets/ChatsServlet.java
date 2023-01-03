package controllers.servlets;

import dto.MessageRecipientDTO;
import dto.UserSessionDTO;
import services.ServiceProvider;
import services.api.IMessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MessageFormServlet", urlPatterns = "/ui/user/message")
public class ChatsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("login", getLogin(req.getSession()));
        IMessageService service = ServiceProvider.getInstance()
                .getMessageService();
        HttpSession session = req.getSession();
        List<MessageRecipientDTO> messageList = service.get(getLogin(session));
        session.setAttribute("messageList", messageList);

        getServletContext().getRequestDispatcher("chats.jsp")
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
