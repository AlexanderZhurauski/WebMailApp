package services;

import dao.api.IUserDAO;
import dto.UserLoginDTO;
import dto.UserSessionDTO;
import services.api.ILoginService;
import services.api.util.IHashGenerator;

public class LoginService implements ILoginService {
    private final IUserDAO userDAO;
    private final IHashGenerator hashGenerator;

    public LoginService(IUserDAO userDAO, IHashGenerator hashGenerator) {
        this.userDAO = userDAO;
        this.hashGenerator = hashGenerator;
    }

    @Override
    public void singIn(UserLoginDTO loginDTO) {
        String providedLogin = loginDTO.getLogin();
        String providedPassword = hashGenerator.createHash(loginDTO.getPassword());
        UserLoginDTO loginDTOFromDAO = userDAO.getUserLoginDTO(providedLogin);

        if (!providedPassword.equals(loginDTOFromDAO.getPassword())) {
            throw new IllegalArgumentException("login failed," +
                    " wrong password");
        }
    }

    @Override
    public UserSessionDTO getUserSessionDTO(String login) {
        return userDAO.getUserSessionDTO(login);
    }
}
