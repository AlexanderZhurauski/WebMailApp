package controllers.listeners;

import dto.UserSessionDTO;
import services.api.IOnlineUsersService;
import services.util.ServiceProviderFactory;
import services.util.ServiceType;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class ListenerOnlineUsers implements HttpSessionAttributeListener {
    private final IOnlineUsersService users = ServiceProviderFactory
            .getInstance(ServiceType.TYPE1).getOnlineUserService();

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
