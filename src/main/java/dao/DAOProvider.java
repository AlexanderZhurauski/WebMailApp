package dao;

import dao.api.IMessageDAO;
import dao.api.IOnlineUserDAO;
import dao.api.IUserDAO;

public class DAOProvider {

    private IMessageDAO messageDAO;
    private IUserDAO userDAO;
    private final IOnlineUserDAO onlineUserDAO;

    private static volatile DAOProvider instance;

    private DAOProvider() {
        messageDAO = new MessageMemoryDAO();
        userDAO = new UserMemoryDAO();
        onlineUserDAO=new OnlineUserDAO();
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
        return onlineUserDAO;
    }
}
