package org.bany.app;

import org.bany.model.Client;
import org.bany.service.AuthService;
import org.bany.utils.ValidateInputs;

import java.util.Scanner;

public class ViewClient {
    private final Scanner sc = new Scanner(System.in);
    private final AuthService authService;
    private boolean running ;

    public ViewClient(AuthService authService){
        this.authService = authService;
    }

    public void runClientView(){
        running = true;
        while (running){
            showClientMenu();
            System.out.print("Enter the option > ");
            String choice = sc.nextLine().trim();
            switch (choice){
                case "1" -> {
                    actionUpateData();
                }
                case "2" -> {
                    authService.getUserService().listAllUsers()
                            .forEach(user -> System.out.println(user.showProfile()));
                }
                case "3" -> {
                    authService.logout();
                    running = false;
                }
                default -> {
                    System.out.println("Choice does not exist");
                }
            }
        }
    }


    private void actionUpateData(){
        try{
            System.out.println("Update Address or phone");
            System.out.print("New Address > ");
            String address = ValidateInputs.validateString(sc.nextLine(), true);
            System.out.print("New phone > ");
            String phone = ValidateInputs.validateString(sc.nextLine(), true);

            Client currentUser = (Client) authService.getCurrentUser();

            currentUser.setAddress(address.isEmpty() ? currentUser.getAddress() : address);
            currentUser.setPhone(phone.isEmpty() ? currentUser.getPhone() : phone);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    private void showClientMenu(){
        System.out.println("""
                        
                        MENU
                        
                1. Update phone or address
                2. Show Users
                3. Logout
                
                """);
    }
}
