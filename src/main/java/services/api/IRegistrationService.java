package services.api;

import dto.UserRegistrationDTO;

public interface IRegistrationService {

    void signUp(UserRegistrationDTO user);
}