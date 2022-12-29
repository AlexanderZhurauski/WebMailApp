package dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserSessionDTO {
    private final String login;
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final LocalDate birthDate;
    private final LocalDateTime registrationDate;
    private final UserRole role;

    public UserSessionDTO(String login, String firstName,
                          String lastName, String patronymic, LocalDate birthDate,
                          LocalDateTime registrationDate, UserRole role) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.role = role;
    }

    public UserSessionDTO(String login, String firstName,
                          String lastName, String patronymic, LocalDate birthDate,
                          UserRole role) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.registrationDate = LocalDateTime.now();
        this.role = role;
    }

    public String getLogin() {
        return login;
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSessionDTO that = (UserSessionDTO) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(registrationDate, that.registrationDate) &&
                role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, firstName, lastName,
                patronymic, birthDate, registrationDate, role);
    }
}
