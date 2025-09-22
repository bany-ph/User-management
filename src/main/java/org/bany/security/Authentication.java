package org.bany.security;

public interface Authentication {
    void login(String email, String password);
    void register(String email, String password, String name);
}
