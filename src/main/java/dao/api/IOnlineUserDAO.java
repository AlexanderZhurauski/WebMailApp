package dao.api;

import dto.UserSessionDTO;

import java.util.List;

public interface IOnlineUserDAO {
    void save(UserSessionDTO user);

    void remove(UserSessionDTO user);

    List<UserSessionDTO> getAll();
}
