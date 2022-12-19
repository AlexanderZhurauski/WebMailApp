package controllers.servlets;

import dto.UserRole;
import dto.UserSessionDTO;
import services.ServiceProvider;
import services.api.IAdminStatisticService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdminStatisticServlet", urlPatterns = "api/admin/statistic")
public class AdminStatisticServlet extends HttpServlet {
    private final IAdminStatisticService adminStatisticService;

    public AdminStatisticServlet() {
        this.adminStatisticService = ServiceProvider.getInstance().getAdminStatisticService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession();
        var user = (UserSessionDTO) session.getAttribute("user");
        if(!user.getRole().equals(UserRole.ADMIN)){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"you are not ADMIN");
        }
        int messageStat = adminStatisticService.getMessageStatistic();
        String onlineUsers = adminStatisticService.getOnlineUsers();
        String usersStat = adminStatisticService.getUserStatistic();
        writer.write("<h3>The number of messages sent in the application: "
                + messageStat + "</h3>");
        writer.write("<h3> Online users </h3>");
        for (var s : onlineUsers.split("\n")) {
            writer.write("<h4>  " + s + "</h4>");
        }
        writer.write("<h3> Registered users in the application </h3>");
        for (var s : usersStat.split("\n")) {
            writer.write("<h4>  " + s + "</h4>");
        }
    }
}
