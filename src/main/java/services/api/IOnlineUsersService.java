package services.api;

import dto.UserSessionDTO;

import java.util.List;

public interface IOnlineUsersService {
    void save(UserSessionDTO user);

    void remove(UserSessionDTO user);

    List<UserSessionDTO> getAll();
}
