package controllers.servlets;

import services.api.IAdminStatisticService;
import services.util.ServiceProviderFactory;
import services.util.ServiceType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdminStatisticServlet", urlPatterns = "/api/admin/statistic")
public class AdminStatisticServlet extends HttpServlet {
    private final IAdminStatisticService adminStatisticService;

    public AdminStatisticServlet() {
        this.adminStatisticService = ServiceProviderFactory.getInstance(ServiceType.STANDARD)
                .getAdminStatisticService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        int messageStat = adminStatisticService.getMessageStatistic();
        String onlineUsers = adminStatisticService.getOnlineUsers();
        String usersStat = adminStatisticService.getUserStatistic();
        writer.write("<h3>The number of messages sent in the application: "
                + messageStat + "</h3>");
        writer.write("<h3> Online users </h3>");
        printStat(onlineUsers, writer);
        writer.write("<h3> Registered users in the application </h3>");
        printStat(usersStat, writer);
    }

    private void printStat(String stat, PrintWriter writer) {
        for (var s : stat.split("\n")) {
            writer.write("<h4>  " + s + "</h4>");
        }
    }
}
