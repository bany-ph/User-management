package org.bany.app;

import org.bany.service.AuthService;
import org.bany.service.UserService;

public class Main {
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        UserService userService = authService.getUserService();
           try{
              if(authService.isAuth()){
                  System.out.println("login or register");
              }

           } catch (Exception e) {
               System.out.println(e.getMessage());
           }
    }
}