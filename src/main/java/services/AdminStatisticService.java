package services;

import dao.api.IMessageDAO;
import dao.api.IUserDAO;
import dto.UserRole;
import dto.UserSessionDTO;
import listeners.ListenerOnlineUsers;
import services.api.IAdminStatisticService;

public class AdminStatisticService implements IAdminStatisticService {
    private final IUserDAO userDAO;
    private final IMessageDAO messageDAO;

    public AdminStatisticService(IUserDAO userDAO, IMessageDAO messageDAO) {
        this.userDAO = userDAO;
        this.messageDAO = messageDAO;
    }

    @Override
    public String getStatistic() {
        StringBuilder sb=new StringBuilder();
        sb.append("The number of messages sent in the application: ")
                .append(getMessageStatistic()).append("\t")
                .append("Online users: ").append("\t")
                .append(getOnlineUsers()).append("\t")
                .append("Registered users in the application")
                .append("\t")
                .append(getUserStatistic());
        return sb.toString();
    }

    @Override
    public int getMessageStatistic() {
        return messageDAO.getAll().size();
    }

    @Override
    public String getOnlineUsers() {
        StringBuilder sb = new StringBuilder();
        ListenerOnlineUsers.getInstance().getOnlineUsers().
                forEach(user -> sb.append(user.getLogin()).append("\n"));
        return sb.toString();
    }

    @Override
    public String getUserStatistic() {
        StringBuilder sb = new StringBuilder();
        userDAO.getAll().forEach(user -> sb.append(user.getLogin()).append("\n"));
        return sb.toString();
    }

    @Override
    public boolean verifyRole(UserSessionDTO user) {
        return user.getRole().equals(UserRole.ADMIN);
    }
}

