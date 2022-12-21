package dao;

import dao.api.IOnlineUserDAO;
import dto.UserSessionDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OnlineUserDAO implements IOnlineUserDAO {
    private final List<UserSessionDTO> users=new ArrayList<>();

    public OnlineUserDAO(){
    }


    @Override
    public void save(UserSessionDTO user) {
        users.add(user);
    }

    @Override
    public void remove(UserSessionDTO user) {
        users.remove(user);
    }

    @Override
    public List<UserSessionDTO> getAll() {
        return Collections.unmodifiableList(users);
    }

}
