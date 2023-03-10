package dao;

import dao.api.IUserDAO;
import dto.UserLoginDTO;
import dto.UserRole;
import dto.UserSessionDTO;
import entities.UserEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class UserMemoryDAO implements IUserDAO {
    private final Map<String, UserEntity> users = new HashMap<>();

    public UserMemoryDAO() {
        add(new UserEntity("admin",
                "5a38afb1a18d408e6cd367f9db91e2ab9bce834cdad3da24183cc174956c20c" +
                        "e35dd39c2bd36aae907111ae3d6ada353f7697a5f1a8fc567aae9e4ca41a9d19d",
                "admin", "admin",
                "admin", LocalDate.now(), LocalDate.now(), UserRole.ADMIN));
    }

    @Override
    public List<UserSessionDTO> getAll() {
        List<UserSessionDTO> userSessionDTOS = new ArrayList<>();
        users.forEach((key, value) -> userSessionDTOS.add(createUserSessionDTO(value)));
        return userSessionDTOS;
    }

    @Override
    public UserSessionDTO getUserSessionDTO(String login) {
        if (exist(login)) {
            return createUserSessionDTO(users.get(login));
        } else {
            throw new NoSuchElementException("there is no user with this username");
        }
    }

    @Override
    public UserLoginDTO getUserLoginDTO(String login) {
        if (exist(login)) {
            return createUserLoginDTO(users.get(login));
        } else {
            throw new NoSuchElementException("there is no user with this username");
        }
    }

    @Override
    public void add(UserEntity userEntity) {
        users.put(userEntity.getLogin(), userEntity);
    }

    @Override
    public boolean exist(String login) {
        return users.containsKey(login);
    }

    private UserSessionDTO createUserSessionDTO(UserEntity userEntity) {
        return new UserSessionDTO(userEntity.getLogin(), userEntity.getFirstName(),
                userEntity.getLastName(), userEntity.getPatronymic(),
                userEntity.getBirthDate(), userEntity.getRole());
    }

    private UserLoginDTO createUserLoginDTO(UserEntity userEntity) {
        return new UserLoginDTO(userEntity.getLogin(), userEntity.getPassword());
    }
}
