package listeners;

import dto.UserSessionDTO;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListenerOnlineUsers implements HttpSessionListener {
    private volatile static ListenerOnlineUsers instance;
    private final List<UserSessionDTO> onlineUsers;

    private ListenerOnlineUsers(){
        onlineUsers=new ArrayList<>();
    }

    public static ListenerOnlineUsers getInstance() {
        if(instance==null){
            synchronized (ListenerOnlineUsers.class){
                if(instance==null){
                    instance=new ListenerOnlineUsers();
                }
            }
        }
        return instance;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        onlineUsers.add((UserSessionDTO) se.getSession().getAttribute("user"));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        onlineUsers.remove((UserSessionDTO)se.getSession().getAttribute("user"));
    }

    public List<UserSessionDTO> getOnlineUsers() {
        return Collections.unmodifiableList(onlineUsers);
    }

}
