package controllers.servlets;

import dto.UserRegistrationDTO;
import services.ServiceProvider;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/api/user")
public class RegistrationServlet extends HttpServlet {

    private final ServiceProvider provider;
    private final String LOGIN_PARAM_NAME = "login";
    private final String PASSWORD_PARAM_NAME = "password";
    private final String FIRSTNAME_PARAM_NAME = "firstName";
    private final String LASTNAME_PARAM_NAME = "lastName";
    private final String PATRONYMIC_PARAM_NAME = "patronymic";
    private final String BIRTHDAY_PARAM_NAME = "birthday";
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public RegistrationServlet() {
        this.provider = ServiceProvider.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        UserRegistrationDTO user = createUser(req);
        provider.getRegistrationService().signUp(user);
        writer.append("<h2>registration completed successfully</h2>");
    }

    private UserRegistrationDTO createUser(HttpServletRequest req) {
        String login = getData(req.getParameterValues(LOGIN_PARAM_NAME),
                LOGIN_PARAM_NAME);
        String password = getData(req.getParameterValues(PASSWORD_PARAM_NAME),
                PASSWORD_PARAM_NAME);
        String firstName = getData(req.getParameterValues(FIRSTNAME_PARAM_NAME),
                FIRSTNAME_PARAM_NAME);
        String lastName = getData(req.getParameterValues(LASTNAME_PARAM_NAME),
                LASTNAME_PARAM_NAME);
        String patronymic = getData(req.getParameterValues(PATRONYMIC_PARAM_NAME),
                PATRONYMIC_PARAM_NAME);
        LocalDate birthday = getBirthday(req.getParameterValues(BIRTHDAY_PARAM_NAME));
        return new UserRegistrationDTO(login, password, firstName,
                lastName, patronymic, birthday);
    }

    private String getData(String[] parameterValues, String paramName) {
        if (parameterValues == null) {
            throw new IllegalArgumentException(paramName + " was not set");
        }
        if (parameterValues.length > 1) {
            throw new IllegalArgumentException("there must be only one " + paramName);
        }
        return parameterValues[0].trim();
    }

    private LocalDate getBirthday(String[] birthdays) {
        if (birthdays == null) {
            throw new IllegalArgumentException("birthday was not set");
        }
        if (birthdays.length > 1) {
            throw new IllegalArgumentException("there must be only one birthday");
        }
        try {
            return LocalDate.parse(birthdays[0], formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("date of birth was entered incorrectly");
        }
    }
}