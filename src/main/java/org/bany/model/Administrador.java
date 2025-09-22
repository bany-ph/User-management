package org.bany.model;

public class Administrador extends User{

    public Administrador(String name, String email, String password) {
        super(name, email, password);
    }



    @Override
    public String getRole() {
        return "";
    }

    @Override
    public String showProfile() {
        return "";
    }
}
