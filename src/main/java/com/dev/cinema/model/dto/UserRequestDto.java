package com.dev.cinema.model.dto;

public class UserRequestDto {
    private String email;
    private String password;

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

    @Override
    public String toString() {
        return "User{"
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
