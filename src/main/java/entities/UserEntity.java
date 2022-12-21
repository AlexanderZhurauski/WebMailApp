package entities;

import dto.UserRole;

import java.time.LocalDate;
import java.util.Objects;

public class UserEntity {
    private final String login;
    private String password;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthDate;
    private final LocalDate registrationDate;
    private UserRole role;

    public UserEntity(String login, String password, String firstName,
                      String lastName, String patronymic, LocalDate birthDate,
                      LocalDate registrationDate, UserRole role) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.role = role;
    }

    public UserEntity(String login, String password, String firstName,
                      String lastName, String patronymic, LocalDate birthDate,
                      UserRole role) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.registrationDate = LocalDate.now();
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(login, that.login)
                && Objects.equals(password, that.password)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(patronymic, that.patronymic)
                && Objects.equals(birthDate, that.birthDate)
                && Objects.equals(registrationDate, that.registrationDate)
                && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, firstName, lastName, patronymic,
                birthDate, registrationDate, role);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthDate=" + birthDate +
                ", registrationDate=" + registrationDate +
                ", role=" + role +
                '}';
    }
}