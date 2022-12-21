package controllers.listeners;

import dao.DAOProvider;
import dao.api.IOnlineUserDAO;
import dto.UserSessionDTO;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class ListenerOnlineUsers implements HttpSessionAttributeListener{
    private final IOnlineUserDAO users = DAOProvider.getInstance().getUserOnlineDAO();

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        UserSessionDTO user = (UserSessionDTO) se.getSession().getAttribute("user");
        if (user != null) {
            users.save(user);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        UserSessionDTO user = (UserSessionDTO) se.getSession().getAttribute("user");
        if (user != null) {
            users.remove(user);
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
    }

}
