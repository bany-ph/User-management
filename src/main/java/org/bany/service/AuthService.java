package org.bany.service;

import org.bany.exception.NotAllowed;
import org.bany.model.User;
import org.bany.security.Authentication;
import org.bany.utils.FindElements;
import org.bany.utils.ValidateInputs;

import java.util.NoSuchElementException;


public class AuthService implements Authentication {

    private static User currentUser;
    private UserService userService;

    private static boolean isAuth;

    public AuthService(){
        userService = new UserService(this);
        currentUser = null;
        isAuth = false;
    }

    @Override
    public boolean login(String email, String password) {

        User getUser = FindElements.findByString(userService.listAllUsers(),email,User::getEmail);
        if(getUser == null){
            throw new NoSuchElementException("The User does not exist!");
        }
        if(!getUser.getPassword().equals(password)){
            throw new IllegalArgumentException("Incorrect Password");
        }

        if(getUser.getStatus().equals("BLOCKED")){
            throw new NotAllowed("Your account it's blocked");
        }

        isAuth = true;
        currentUser = getUser;

        return true;
    }

    @Override
    public void register(User newUser) {
        ValidateInputs.validateEmail(newUser.getEmail());
        ValidateInputs.validatePassword(newUser.getPassword());

        User userExist = FindElements.findByString(userService.listAllUsers(),newUser.getEmail(), User::getEmail);

        if(userExist != null){
            throw new IllegalArgumentException("The User already exist");
        }
        userService.save(newUser);

        isAuth = true;
        currentUser = newUser;

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
