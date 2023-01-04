package services;

import dao.api.IMessageDAO;
import dao.api.IOnlineUserDAO;
import dao.api.IUserDAO;
import services.api.IAdminStatisticService;

public class AdminStatisticService implements IAdminStatisticService {
    private final IUserDAO userDAO;
    private final IMessageDAO messageDAO;
    private final IOnlineUserDAO onlineUserDAO;

    public AdminStatisticService(IUserDAO userDAO, IMessageDAO messageDAO,
                                 IOnlineUserDAO onlineUserDAO) {
        this.userDAO = userDAO;
        this.messageDAO = messageDAO;
        this.onlineUserDAO = onlineUserDAO;
    }

    @Override
    public int getMessageStatistic() {
        return messageDAO.getAll().size();
    }

    @Override
    public String getOnlineUsers() {
        StringBuilder sb = new StringBuilder();
        onlineUserDAO.getAll().
                forEach(user -> sb.append(user.getLogin()).append("\n"));
        return sb.toString();
    }

    @Override
    public String getUserStatistic() {
        StringBuilder sb = new StringBuilder();
        userDAO.getAll().forEach(user -> sb.append(user.getLogin()).append("\n"));
        return sb.toString();
    }
}

