package org.bany.service;

import org.bany.model.Administrador;
import org.bany.model.User;
import org.bany.utils.FindElements;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserService implements UserInterface {
    private static List<User> users = new ArrayList<>();
    private AuthService authService = new AuthService();



    @Override
    public void save(User user) {
        User userExist = getUserByEmail(user.getEmail());
        if(userExist != null){
            throw new RuntimeException("User already exist!");
        }
        users.add(user);
    }

    @Override
    public void delete(String userEmail) {
        User getUserToRemove = getUserByEmail(userEmail);
        if(getUserToRemove == null){
            throw new NoSuchElementException("No user found");
        }
        users.remove(getUserToRemove);
    }

    @Override
    public void update(User user) {

        users.set(getIndexOfUser(user), user);
    }

    @Override
    public User getUserByEmail(String userEmail) {
        return FindElements.findByString(users,userEmail,User::getEmail); // user.getEmail()
    }

    @Override
    public List<User> listAllUsers() {
        return users;
    }


    private int getIndexOfUser(User user){
        return users.indexOf(user);
    }

    private boolean isAdmin(){
        return authService.getCurrentUser() instanceof Administrador;
    }


    /*FOR ADMIN*/
    public void changeStatusOfAnClient(User user){
        if(!isAdmin()){
            throw new RuntimeException("You're not allowed to do this action");
        }else if(user instanceof Administrador){
            throw new RuntimeException("You're not allow to do this action");
        }
        users.get(getIndexOfUser(user)).setStatus();
    }

}
