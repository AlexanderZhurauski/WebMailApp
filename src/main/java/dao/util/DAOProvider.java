package dao.util;

import dao.MessageMemoryDAO;
import dao.OnlineUserDAO;
import dao.UserMemoryDAO;
import dao.api.util.IDAOProvider;
import dao.api.IMessageDAO;
import dao.api.IOnlineUserDAO;
import dao.api.IUserDAO;

public class DAOProvider implements IDAOProvider {

    private final IMessageDAO messageDAO;
    private final IUserDAO userDAO;
    private final IOnlineUserDAO onlineUserDAO;

    private static volatile IDAOProvider instance;

    private DAOProvider() {
        messageDAO = new MessageMemoryDAO();
        userDAO = new UserMemoryDAO();
        onlineUserDAO = new OnlineUserDAO();
    }

    public static IDAOProvider getInstance() {
        if (instance == null) {
            synchronized (DAOProvider.class) {
                if (instance == null) {
                    instance = new DAOProvider();
                }
            }
        }
        return instance;
    }

    @Override
    public IMessageDAO getMessageDAO() {
        return messageDAO;
    }

    @Override
    public IUserDAO getUserDAO() {
        return userDAO;
    }

    @Override
    public IOnlineUserDAO getOnlineUserDAO() {
        return onlineUserDAO;
    }
}
