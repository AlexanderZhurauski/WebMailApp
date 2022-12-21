package services.api;

import dto.UserLoginDTO;
import dto.UserSessionDTO;

public interface ILoginService {
    void singIn(UserLoginDTO loginDTO);

    UserSessionDTO getUserSessionDTO(String login);
}
