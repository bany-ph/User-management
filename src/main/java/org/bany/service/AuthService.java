package org.bany.service;

import org.bany.model.User;
import org.bany.security.Authentication;
import org.bany.utils.FindElements;
import org.bany.utils.ValidateInputs;

import java.util.ArrayList;
import java.util.List;

public class AuthService implements Authentication {
    private User currentUser;

    private  AdminService adminService;
    private  ClientService clientService;
    private boolean isAuth;

    public AuthService(){
        this.adminService = new AdminService();
        this.clientService = new ClientService();
        this.currentUser = null;
        this.isAuth = false;
    }

    @Override
    public boolean login(String email, String password) {

        User getUser = FindElements.findByString(getAllUsers(),email,User::getEmail);
        if(getUser == null){
            throw new RuntimeException("The User does not exist!");
        }
        if(!getUser.getPassword().equals(password)){
            throw new RuntimeException("Incorrect Password");
        }

        this.isAuth = true;
        this.currentUser = getUser;

        return true;
    }

    @Override
    public boolean register(User newUser) {
        ValidateInputs.validateEmail(newUser.getEmail());
        ValidateInputs.validatePassword(newUser.getPassword());

        User userExist = FindElements.findByString(getAllUsers(),newUser.getEmail(), User::getEmail);
        if(userExist != null){
            throw new RuntimeException("The User already exist");
        }
        if(newUser.getRole().equalsIgnoreCase("ADMIN")){
            adminService.save(newUser);
        }else{
            clientService.save(newUser);
        }
        return true;
    }

    @Override
    public void logout() {
        if(isAuth && currentUser != null ){
            this.currentUser = null;
            isAuth = false;
        }else {
            throw new RuntimeException("System Error: logout failed");
        }
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public boolean isAuth() {
        return isAuth;
    }

    private List<User> getAllUsers(){
        List<User> admins = new ArrayList<>(new AdminService().listAllUsers());
        List<User> clients = new ArrayList<>(new ClientService().listAllUsers());
        List<User> newList = new ArrayList<>();
        newList.addAll(admins);
        newList.addAll(clients);
        return newList;
    }
}
