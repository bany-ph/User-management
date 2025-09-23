package org.bany.security;

import org.bany.model.User;

public interface Authentication {
    boolean login(String email, String password);
    boolean register(User user);
    void logout();
    User getCurrentUser();

}
