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

@WebServlet(name = "ChatsServlet", urlPatterns = "/ui/user/message")
public class ChatsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        UserSessionDTO user = (UserSessionDTO) session.getAttribute("user");
        String login = user.getLogin();
        req.setAttribute("login", login);

        IMessageService service = ServiceProvider.getInstance()
                .getMessageService();
        List<MessageRecipientDTO> messageList = service.get(login);
        session.setAttribute("messageList", messageList);

        getServletContext().getRequestDispatcher("chats.jsp")
                .forward(req, resp);
    }
}
