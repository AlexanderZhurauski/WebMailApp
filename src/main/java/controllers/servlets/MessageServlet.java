package controllers.servlets;

import dto.MessageDTO;
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
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "MessageServlet", urlPatterns = "/api/message")
public class MessageServlet extends HttpServlet {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("HH:mm:ss, dd.MM.yyyy");
    private final String MESSAGE_PARAM_NAME = "text";
    private final String RECIPIENT_PARAM_NAME = "recipient";
    private final String USER_ATTRIBUTE = "user";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        IMessageService service = ServiceProvider.getInstance()
                .getMessageService();

        HttpSession currentSession = req.getSession();
        UserSessionDTO user = (UserSessionDTO) currentSession.getAttribute(USER_ATTRIBUTE);
        String login = user.getLogin();

        writer.append("<h1>")
                .append(user.getLogin())
                .append("'s current messages: </h1> <br>");

        service.get(login)
                .forEach(message -> writer.append(message.getSender())
                        .append(", ")
                        .append(message.getTime().format(formatter))
                        .append(":<br>")
                        .append(message.getText())
                        .append("<br><hr>"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        HttpSession currentSession = req.getSession();
        IMessageService service = ServiceProvider.getInstance()
                .getMessageService();

        UserSessionDTO user = (UserSessionDTO) currentSession.getAttribute(USER_ATTRIBUTE);
        String text = getRequestParam(req, MESSAGE_PARAM_NAME);
        String sender = user.getLogin();
        String recipient = getRequestParam(req, RECIPIENT_PARAM_NAME);
        service.send(new MessageDTO(text, sender, recipient));

        writer.append("<h2>Message to " + recipient + " sent successfully!</h2>");
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
