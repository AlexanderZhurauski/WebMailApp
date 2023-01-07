package controllers.servletsforjsp;

import services.api.IAdminStatisticService;
import services.util.ServiceProviderFactory;
import services.util.ServiceType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminStatForJsp", urlPatterns = "/ui/admin/statistic")
public class AdminStatForJsp extends HttpServlet {
    private final IAdminStatisticService service;

    public AdminStatForJsp() {
        service = ServiceProviderFactory.getInstance(ServiceType.STANDARD).getAdminStatisticService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("messageStat", service.getMessageStatistic());
        req.setAttribute("onlineStat", service.getOnlineUsers());
        req.setAttribute("usersStat", service.getUserStatistic());
        req.setAttribute("ContextPath",req.getContextPath());
        getServletContext().getRequestDispatcher("/adminstatistic.jsp")
                .forward(req, resp);
    }
}
