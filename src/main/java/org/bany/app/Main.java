package org.bany.app;

import org.bany.model.Client;
import org.bany.service.AuthService;
import org.bany.service.UserService;

public class Main {
    public static void main(String[] args) {
        AuthService authService = new AuthService();

        while(true) {
            try {
                if (!authService.isAuth()) {
                    showMainMenu();

                } else if (authService.getCurrentUser() instanceof Client) {
                    showClientMenu();

                } else {
                    ControllerAdmin controllerAdmin = new ControllerAdmin(authService);
                    showAdminMenu();
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static void showMainMenu(){
        System.out.println("""
                        MAIN MENU
                        
                1. Login
                2. Register
                3. exit
                """);
    }


    static  void showClientMenu(){
        System.out.println("""
                        MENU
                        
                1. Update data
                2. Show Users
                3. Logout
                
                """);
    }

    static  void showAdminMenu(){
        System.out.println("""
                    MENU
                    
                1. Block user/ unblock User     
                2. Show users
                3. logout
                """);
    }
}