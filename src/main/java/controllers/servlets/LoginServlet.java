package controllers.servlets;

import dto.UserLoginDTO;
import services.ServiceProvider;
import services.api.ILoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = "/api/login")
public class LoginServlet extends HttpServlet {
    private final ILoginService loginService;
    private final String LOGIN_PARAM_NAME = "login";
    private final String PASSWORD_PARAM_NAME = "password";
    private final String USER_PARAM_NAME = "user";

    public LoginServlet() {
        this.loginService = ServiceProvider.getInstance().getLoginService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String login = getRequestParam(req, LOGIN_PARAM_NAME);
        String password = getRequestParam(req, PASSWORD_PARAM_NAME);
        loginService.singIn(new UserLoginDTO(login, password));

        HttpSession session = req.getSession();
        session.setAttribute(USER_PARAM_NAME, loginService.getUserSessionDTO(login));
        session.setMaxInactiveInterval(-1);
        writer.append("<h2>login completed successfully</h2>");
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
