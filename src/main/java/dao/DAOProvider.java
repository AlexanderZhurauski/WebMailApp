package dao;

import dao.api.IMessageDAO;
import dao.api.IOnlineUserDAO;
import dao.api.IUserDAO;

public class DAOProvider {

    private final IMessageDAO messageDAO;
    private final IUserDAO userDAO;
    private final IOnlineUserDAO userOnlineDAO;
    private static volatile DAOProvider instance;

    private DAOProvider() {
        messageDAO = new MessageMemoryDAO();
        userDAO = new UserMemoryDAO();
        userOnlineDAO = new OnlineUserDAO();
    }

    public static DAOProvider getInstance() {
        if (instance == null) {
            synchronized (DAOProvider.class) {
                if (instance == null) {
                    instance = new DAOProvider();
                }
            }
        }
        return instance;
    }

    public IMessageDAO getMessageDAO() {
        return messageDAO;
    }

    public IUserDAO getUserDAO() {
        return userDAO;
    }

    public IOnlineUserDAO getUserOnlineDAO() {
        return userOnlineDAO;
    }
}
