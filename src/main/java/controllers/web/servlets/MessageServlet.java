package controllers.web.servlets;

import dao.MessageMemoryDAO;
import dto.MessageDTO;
import dto.UserSessionDTO;
import services.MessageService;
import services.ServiceProvider;
import services.api.IMessageService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "MessageServlet", urlPatterns = "/api/message")
public class MessageServlet extends HttpServlet {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("HH:mm:ss, dd.MM.yyyy");
    private static final ServiceProvider provider = ServiceProvider.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        IMessageService service = provider.getMessageService();

        HttpSession currentSession = req.getSession();
        UserSessionDTO user = getUserData(currentSession);
        String login = getUserLogin(user);

        writer.append("<h1>")
                .append(user.getLogin())
                .append("'s current messages: </h1> <br>");

        service.get(login)
                .stream()
                .forEach(message -> writer.append(message.getSender())
                        .append(", ")
                        .append(message.getTime().format(formatter))
                        .append(":<br>")
                        .append(message.getText())
                        .append("<br><hr>"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");
        HttpSession currentSession = req.getSession();
        IMessageService service = provider.getMessageService();

        UserSessionDTO user = getUserData(currentSession);
        String text = getRequestParam(req, "text");
        String sender = getUserLogin(user);
        String recipient = getRequestParam(req, "recipient");
        service.send(new MessageDTO(text, sender, recipient));
    }

    private UserSessionDTO getUserData(HttpSession session) {
        UserSessionDTO user = (UserSessionDTO) session.getAttribute("user");
        if (user == null) {
            throw new IllegalStateException("No user is logged in");
        }

        return user;
    }
    private String getUserLogin(UserSessionDTO user) {
        String login = user.getLogin();
        if (login == null || login.isBlank()) {
            throw new IllegalStateException("User login data in the" +
                    " session is corrupted");
        }

        return login;
    }

    private String getRequestParam(HttpServletRequest req, String name) {
        String[] param = req.getParameterValues(name);
        if (param == null || param.length == 0) {
            throw new IllegalArgumentException("User failed to provide a " +
                    name);
        }
        if (param.length > 1) {
            throw new IllegalArgumentException("More than one " + name +
                    " has been provided");
        }
        if (param[0] == null || param[0].isBlank()) {
            throw new IllegalArgumentException("Invalid " + name +
                    " provided");
        }

        return param[0];
    }
}
