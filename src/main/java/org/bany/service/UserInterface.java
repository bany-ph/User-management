package org.bany.service;

import org.bany.model.User;
import java.util.List;

public interface UserInterface {
    void save(User user );
    void delete(String email);
    void update(User user);
    User getUserByEmail(String userEmail);
    List<User> listAllUsers();
}
