package services;

import dto.UserRegistrationDTO;
import services.api.IRegistrationService;

import java.time.LocalDate;
import java.time.Period;

public class RegistrationService implements IRegistrationService {

    @Override
    public boolean signUp(UserRegistrationDTO user) {
        validateUser(user);
        return true;
    }

    private void validateUser(UserRegistrationDTO user) {
        validateLogin(user.getLogin());
        validatePassword(user.getPassword());
        validateName(user.getFirstName());
        validateName(user.getLastName());
        validateName(user.getPatronymic());
        validateBirthday(user.getBirthday());
    }

    private void validateLogin(String login) {
        if (login.length() < 5) {
            throw new IllegalArgumentException("the login is too simple," +
                    " please change it");
        }
    }

    private void validatePassword(String password) {
        if (password.length() < 5) {
            throw new IllegalArgumentException("the password is too simple," +
                    " please change it");
        }
    }

    private void validateName(String name) {
        if (name.length() < 3) {
            throw new IllegalArgumentException("invalid name specified");
        }
    }

    private void validateBirthday(LocalDate birthday) {
        Period period = Period.between(birthday, LocalDate.now());
        if (period.getYears() < 7) {
            throw new IllegalArgumentException("to use the app" +
                    " you must be over 7 years old");
        }
    }
}