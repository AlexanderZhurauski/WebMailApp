package dao.api;

import dto.UserLoginDTO;
import dto.UserSessionDTO;
import entities.UserEntity;

import java.util.List;

public interface IUserDAO {
    List<UserSessionDTO> getAll();

    UserSessionDTO getUserSessionDTO(String login);

    UserLoginDTO getUserLoginDTO(String login);

    boolean add(UserEntity userEntity);

    boolean exist(String login);
}
