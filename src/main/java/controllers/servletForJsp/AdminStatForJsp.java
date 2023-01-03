package controllers.servletForJsp;

import services.ServiceProvider;
import services.api.IAdminStatisticService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminStatForJsp" , urlPatterns = "/ui/admin/statistic")
public class AdminStatForJsp extends HttpServlet {
    private final IAdminStatisticService service;

    public AdminStatForJsp(){
        service= ServiceProvider.getInstance().getAdminStatisticService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("messageStat",service.getMessageStatistic());
        req.setAttribute("onlineStat",service.getOnlineUsers());
        req.setAttribute("usersStat",service.getUserStatistic());

        getServletContext().getRequestDispatcher("/adminstatistic.jsp")
                .forward(req,resp);
    }
}
