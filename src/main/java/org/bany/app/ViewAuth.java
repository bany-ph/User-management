package org.bany.app;

import org.bany.exception.NotAllowed;
import org.bany.model.Administrador;
import org.bany.model.Client;
import org.bany.service.AuthService;
import org.bany.utils.ValidateInputs;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ViewAuth {
    private final Scanner sc = new Scanner(System.in);
    private final AuthService authService;
    private boolean running;

    public ViewAuth(AuthService authService){
        this.authService = authService;
    }

    public void runAuthView(){
        running = true;
        while (running){
            showMenu();
            System.out.print("Enter the option > ");
            String choice = sc.nextLine().trim();
            switch (choice){
                case "1" -> {
                    actionLogin();
                }
                case "2" -> {
                    actionRegister();
                }
                case "3" -> {
                    System.out.println("Bye!");
                    System.exit(0); // end the program

                }
                default -> {
                    System.out.println("Choice does not exist");
                }
            }
        }

    }

    private void showMenu(){
        System.out.println("""
                        
                        MAIN MENU
                        
                1. Login
                2. Register
                3. exit
                
                """);
    }

    private void actionLogin(){
        while(true) {
            try {
                //if the login success, then running = false
                System.out.print("Enter Email > ");
                String email = sc.nextLine();
                email = ValidateInputs.validateEmail(email);
                System.out.print("Enter Password > ");
                String password = ValidateInputs.validateString(sc.nextLine()); // just do trim

                authService.login(email, password);
                System.out.println("LOG IN!");
                running = false;

                break;

            } catch (IllegalArgumentException | NoSuchElementException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
                if(answer("Want to stop?")){ // in case the user does not want to keep trying
                    break;
                }
            }catch (NotAllowed e){
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    private void actionRegister(){
        while(true) {
            try {
                //if the login success, then running = false
                System.out.print("Enter Name > ");
                String name = ValidateInputs.validateString(sc.nextLine());
                System.out.print("Enter Email > ");
                String email = ValidateInputs.validateEmail(sc.nextLine());
                System.out.print("Enter Password > ");
                String password = ValidateInputs.validatePassword(sc.nextLine()); // just do trim
                if(answer("Register as an admin")){
                    authService.register(new Administrador(name,email,password));
                }else{
                    authService.register(new Client(name,email,password));
                }
                System.out.println("success register");
                running = false;
                break;
            } catch (IllegalArgumentException | NoSuchElementException e) {
                System.out.println(e.getMessage());
                sc.nextLine(); // delete input
                if(answer("Want to stop?")){ // in case the user does not want to keep trying
                    break;
                }
            }catch (NotAllowed e){
                System.out.println(e.getMessage());
                break;
            }
        }
    }








    private boolean answer(String message){
        System.out.print(message + " | Y=yes \n> ");
        String choice = sc.nextLine();
        return choice.toUpperCase().contains("Y");
    }
}
