package dao.api.util;

import dao.api.IMessageDAO;
import dao.api.IOnlineUserDAO;
import dao.api.IUserDAO;

public interface IDAOProvider {

    IMessageDAO getMessageDAO();

    IUserDAO getUserDAO();

    IOnlineUserDAO getOnlineUserDAO();
}

