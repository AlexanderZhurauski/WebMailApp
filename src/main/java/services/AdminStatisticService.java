package services;

import dao.api.IMessageDAO;
import dao.api.IOnlineUserDAO;
import dao.api.IUserDAO;
import services.api.IAdminStatisticService;

import java.util.ArrayList;
import java.util.List;

public class AdminStatisticService implements IAdminStatisticService {
    private final IUserDAO userDAO;
    private final IMessageDAO messageDAO;
    private final IOnlineUserDAO onlineUserDAO;

    public AdminStatisticService(IUserDAO userDAO, IMessageDAO messageDAO,
                                 IOnlineUserDAO onlineUserDAO) {
        this.userDAO = userDAO;
        this.messageDAO = messageDAO;
        this.onlineUserDAO=onlineUserDAO;
    }

    @Override
    public int getMessageStatistic() {
        return messageDAO.getCount();
    }

    @Override
    public List<String> getOnlineUsers() {
        List<String> onlineUsers=new ArrayList<>();
        onlineUserDAO.getAll().forEach(user -> onlineUsers.add(user.getLogin()));
        return onlineUsers;
    }

    @Override
    public List<String> getUserStatistic() {
        List<String> users = new ArrayList<>();
        userDAO.getAll().forEach(user -> users.add(user.getLogin()));
        return users;
    }
}

