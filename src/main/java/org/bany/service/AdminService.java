package org.bany.service;

import org.bany.model.User;
import org.bany.utils.FindElements;

import java.util.ArrayList;
import java.util.List;

public class AdminService implements  UserInterface {
    private static List<User> adminUsers = new ArrayList<>();

    public AdminService(){}



    @Override
    public void save(User user) {
        adminUsers.add(user);
    }

    @Override
    public void delete(String email) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> listAllUsers() {
        return adminUsers;
    }

    @Override
    public User getUserByEmail(String email) {
        return FindElements.findByString(adminUsers,email,User::getEmail);
    }
}
