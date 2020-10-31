package com.dev.cinema.model.dto;

import com.dev.cinema.validation.EmailValueConstraint;
import com.dev.cinema.validation.PasswordValueMatch;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordValueMatch(
        password = "password",
        repeatPassword = "repeatPassword"
)
public class UserRequestDto {
    @EmailValueConstraint
    private String email;
    @NotNull
    @Size(min = 5)
    private String password;
    @NotNull
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Override
    public String toString() {
        return "User{"
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
