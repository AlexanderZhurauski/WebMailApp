package controllers.servletsforjsp;

import dto.MessageRecipientDTO;
import dto.UserSessionDTO;
import services.api.IMessageService;
import services.util.ServiceProviderFactory;
import services.util.ServiceType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChatsServlet", urlPatterns = "/ui/user/chats")
public class ChatsServlet extends HttpServlet {

    private final String LOGIN_PARAM_NAME = "login";
    private final String MESSAGE_LIST = "messageList";
    private final String USER_ATTRIBUTE = "user";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        UserSessionDTO user = (UserSessionDTO) session.getAttribute(USER_ATTRIBUTE);
        String login = user.getLogin();
        req.setAttribute(LOGIN_PARAM_NAME, login);

        IMessageService service = ServiceProviderFactory.getInstance(ServiceType.STANDARD)
                .getMessageService();
        List<MessageRecipientDTO> messageList = service.get(login);
        session.setAttribute(MESSAGE_LIST, messageList);
        req.setAttribute("ContextPath",req.getContextPath());

        getServletContext().getRequestDispatcher("/chats")
                .forward(req, resp);
    }
}
