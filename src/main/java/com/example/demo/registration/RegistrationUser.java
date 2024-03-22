package com.example.demo.registration;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Schema(description = "Запрос на регистрацию")
public class RegistrationUser {
    @Schema(description = "Логин пользователя", example = "Jon")
    @Size(min = 5, max = 50, message = "Логин пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Логин пользователя не может быть пустыми")
    private String login;

    @Schema(description = "Имя пользователя", example = "Jon")
    @Size(min = 5, max = 50, message = "Логин пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Логин пользователя не может быть пустыми")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Jon")
    @Size(min = 5, max = 50, message = "Фамилия пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Фамилия пользователя не может быть пустыми")
    private String lastName;


    @Schema(description = "Адрес электронной почты", example = "jondoe@gmail.com")
    @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    @Email(message = "Email адрес должен быть в формате user@example.com")
    private String email;

    @Schema(description = "Пароль", example = "my_1secret1_password")
    @Size(max = 255, message = "Длина пароля должна быть не более 255 символов")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
