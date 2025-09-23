package org.bany.app;

import org.bany.model.User;
import org.bany.service.AuthService;
import org.bany.service.UserService;
import org.bany.utils.FindElements;
import org.bany.utils.ValidateInputs;

import java.util.Scanner;

public class ControllerAdmin {
    private AuthService authService;
    private UserService userService;


    public ControllerAdmin(AuthService authService){
        this.authService = authService;
         userService = new UserService();
    }

    public void selectChoice(){
        Scanner sc = new Scanner(System.in);
        String choice = sc.next();

        switch (choice){
            case "1"  -> {
                System.out.println("Insert the email of the user you want to block or unblock");
                String email = ValidateInputs.validateEmail(sc.next());
                userService.changeStatusOfAnClient(email);
            }
            case "2" ->{
                userService.listAllUsers().forEach(User::showProfile);
            }
            case "3" -> {
                authService.logout();
            }
        }

    }
}
