package org.bany.service;

import org.bany.model.User;

import java.util.ArrayList;
import java.util.List;

public class ClientService implements UserInterface {
    private static List<User> clientUsers = new ArrayList<>();


    public ClientService(){}

    @Override
    public void save(User user) {
        clientUsers.add(user);
    }

    @Override
    public void delete(String email) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User getUserByEmail(String userEmail) {
        return null;
    }

    @Override
    public List<User> listAllUsers() {
        return clientUsers;
    }
}
