package controllers.filters;

import dto.UserSessionDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/ui/user/*", "/api/message"})
public class UserSecurityFilter implements Filter {
    private final String USER_PARAM_NAME = "user";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        UserSessionDTO userSessionDTO = (UserSessionDTO) session.getAttribute(USER_PARAM_NAME);
        if (session != null && userSessionDTO!=null){
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + "/ui/signIn");
        }
    }

    @Override
    public void destroy() {
    }
}
