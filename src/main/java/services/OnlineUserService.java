package services;

import dao.api.IOnlineUserDAO;
import dto.UserSessionDTO;
import services.api.IOnlineUsersService;

import java.util.List;

public class OnlineUserService implements IOnlineUsersService {

    private final IOnlineUserDAO onlineUserDAO;

    public OnlineUserService(IOnlineUserDAO onlineUserDAO) {
        this.onlineUserDAO = onlineUserDAO;
    }

    @Override
    public void save(UserSessionDTO user) {
        onlineUserDAO.save(user);
    }

    @Override
    public void remove(UserSessionDTO user) {
        onlineUserDAO.remove(user);
    }

    @Override
    public List<UserSessionDTO> getAll() {
        return onlineUserDAO.getAll();
    }
}
