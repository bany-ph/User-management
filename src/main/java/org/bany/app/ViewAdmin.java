package org.bany.app;

import org.bany.exception.NotAllowed;
import org.bany.service.AuthService;
import org.bany.utils.ValidateInputs;


import java.util.NoSuchElementException;
import java.util.Scanner;

public class ViewAdmin {
    private final  AuthService authService;
    private final Scanner sc = new Scanner(System.in);


    public ViewAdmin(AuthService authService){
        this.authService = authService;

    }

    public void RunAdminView(){
        boolean running = true;
        while(running){
            showAdminMenu();
            System.out.print("Enter option > ");
            String choice = sc.next();
            switch (choice){
                case "1"  -> {
                    actionChangeStatus();
                }
                case "2" ->{
                    authService.getUserService()
                            .listAllUsers()
                            .forEach(user -> System.out.println(user.showProfile()));
                }
                case "3" -> {
                    authService.logout();
                    System.out.println("Bye :)");
                    running = false;
                }
                default -> {
                    System.out.println("CHOICE DOES NOT EXIST");
                }
            }
        }

    }
    private void actionChangeStatus(){

            try {
                String email;
                System.out.println("Insert the email of the user you want to block or unblock");
                email = ValidateInputs.validateEmail(sc.next());
                authService.getUserService().changeStatusOfAnClient(email);

            }catch (NotAllowed e) {
                System.out.println(e.getMessage());

            }catch (IllegalArgumentException | NoSuchElementException e) { // should return
                System.out.println(e.getMessage());

            } catch (Exception e) { // for unexpected errors
                System.out.println(e.getMessage());
            }


    }

    private void showAdminMenu(){
        System.out.println("""
                    
                    MENU
                    
                1. Block user/ unblock User     
                2. Show users
                3. logout
                
                """);
    }
}
