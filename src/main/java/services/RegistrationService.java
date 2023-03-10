package services;

import dao.api.util.IDAOProvider;
import dto.UserRegistrationDTO;
import dto.UserRole;
import entities.UserEntity;
import services.api.IRegistrationService;
import services.api.util.IHashGenerator;

import java.time.LocalDate;
import java.time.Period;

public class RegistrationService implements IRegistrationService {

    private final IDAOProvider daoProvider;
    private final IHashGenerator hashGenerator;

    public RegistrationService(IDAOProvider daoProvider, IHashGenerator hashGenerator) {
        this.daoProvider = daoProvider;
        this.hashGenerator = hashGenerator;
    }

    @Override
    public void signUp(UserRegistrationDTO user) {
        validateUser(user);
        user.setPassword(hashGenerator.createHash(user.getPassword()));
        UserEntity newUser = createUserEntity(user);
        if (!daoProvider.getUserDAO().exist(newUser.getLogin())) {
            daoProvider.getUserDAO().add(newUser);
        } else {
            throw new IllegalArgumentException("registration failed," +
                    " login already taken");
        }
    }

    private UserEntity createUserEntity(UserRegistrationDTO user) {
        return new UserEntity(user.getLogin(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getPatronymic(),
                user.getBirthday(),
                UserRole.USER);
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