package controllers.filters;

import dto.UserRole;
import dto.UserSessionDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = {"/ui/admin/*", "/api/admin/*"})
public class AdminSecurityFilter implements Filter {
    private final String USER_PARAM_NAME = "user";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession();
        UserSessionDTO user = (UserSessionDTO) session.getAttribute(USER_PARAM_NAME);
        if ((session != null) && (user != null) && (user.getRole() == UserRole.ADMIN)) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(contextPath+"/ui/signIn");
        }
    }

    @Override
    public void destroy() {
    }
}
