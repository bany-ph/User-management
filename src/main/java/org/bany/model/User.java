package org.bany.model;


import org.bany.utils.ValidateInputs;

public abstract class User {
    private String name;
    private String email;
    private String password;
    private enum Status {
        ACTIVE,
        BLOCKED
    };

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        ValidateInputs.validateEmail(email);
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        ValidateInputs.validatePassword(password);
        this.password = password;
    }
    public void setStatus(Status status){

    }

    public abstract String getRole();
    public abstract String showProfile();
}
