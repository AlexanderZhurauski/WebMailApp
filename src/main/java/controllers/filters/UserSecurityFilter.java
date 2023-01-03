package controllers.filters;

import dto.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"ui/user/*", "/api/message"})
public class UserSecurityFilter implements Filter {
    private final String USER_PARAM_NAME = "user";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        UserRole role = (UserRole) session.getAttribute(USER_PARAM_NAME);
        if (role != null && (role.equals(UserRole.USER) || role.equals(UserRole.ADMIN))) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + "/signIn");
        }
    }

    @Override
    public void destroy() {

    }
}
