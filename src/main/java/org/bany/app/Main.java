package org.bany.app;

import org.bany.model.Administrador;
import org.bany.model.Client;
import org.bany.model.User;
import org.bany.service.AuthService;


public class Main {
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        ViewAdmin viewAdmin = new ViewAdmin(authService);
        ViewAuth viewAuth = new ViewAuth(authService);
        ViewClient viewClient = new ViewClient(authService);

        while(true) {
            try {
                User currentUser = authService.getCurrentUser();
                if (!authService.isAuth()) {
                    viewAuth.runAuthView();
                } else if (currentUser instanceof Client) {
                    viewClient.runClientView();
                } else if ( currentUser instanceof Administrador) {
                    viewAdmin.RunAdminView();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }





}