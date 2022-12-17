package dao;

import dao.api.IUserDAO;
import dto.UserLoginDTO;
import dto.UserRole;
import dto.UserSessionDTO;
import entities.UserEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class UserMemoryDAO implements IUserDAO {
    private final Map<String, UserEntity> users = new HashMap<>();

    public UserMemoryDAO() {
        add(new UserEntity("admin", "admin", "admin", "admin",
                "admin", LocalDate.now(), LocalDateTime.now(), UserRole.ADMIN));
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
