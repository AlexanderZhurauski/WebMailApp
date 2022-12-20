package services.api;

import dto.UserRegistrationDTO;

public interface IRegistrationService {

    boolean signUp(UserRegistrationDTO user);
}