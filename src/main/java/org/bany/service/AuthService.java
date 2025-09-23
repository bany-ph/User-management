package org.bany.service;

import org.bany.model.User;
import org.bany.security.Authentication;
import org.bany.utils.FindElements;
import org.bany.utils.ValidateInputs;

import java.util.ArrayList;
import java.util.List;

public class AuthService implements Authentication {

    private static User currentUser;
    private final UserService userService;

    private boolean isAuth;

    public AuthService(){
        userService = new UserService();
        currentUser = null;
        this.isAuth = false;
    }

    @Override
    public boolean login(String email, String password) {

        User getUser = FindElements.findByString(userService.listAllUsers(),email,User::getEmail);
        if(getUser == null){
            throw new RuntimeException("The User does not exist!");
        }
        if(!getUser.getPassword().equals(password)){
            throw new RuntimeException("Incorrect Password");
        }

        this.isAuth = true;
        currentUser = getUser;

        return true;
    }

    @Override
    public boolean register(User newUser) {
        ValidateInputs.validateEmail(newUser.getEmail());
        ValidateInputs.validatePassword(newUser.getPassword());

        User userExist = FindElements.findByString(userService.listAllUsers(),newUser.getEmail(), User::getEmail);

        if(userExist != null){
            throw new RuntimeException("The User already exist");
        }
        userService.save(newUser);
        return true;
    }

    @Override
    public void logout() {
        if(isAuth && currentUser != null ){
            currentUser = null;
            isAuth = false;
        }else {
            throw new RuntimeException("System Error: logout failed");
        }
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    public UserService getUserService() {
        return userService;
    }

    public boolean isAuth() {
        return isAuth;
    }


}
