package org.bany.utils;

import java.util.regex.Pattern;

public class ValidateInputs {

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\\\.[A-Za-z]{2,})$");
    private static final int MIN_PASSWORD_LENGTH = 6;

    public static void validateEmail(String email){

        if(email == null || email.trim().isEmpty()) throw new IllegalArgumentException("The email cannot be empty");

        if(!EMAIL_REGEX.matcher(email).matches()) throw new IllegalArgumentException("Invalid email format");

    }

    public static void validatePassword(String password){
        if (password == null || password.trim().isEmpty()){
            throw new IllegalArgumentException("The password cannot be empty");
        }

        if(password.length() < MIN_PASSWORD_LENGTH){
            throw new IllegalArgumentException("The password has to be larger than 5");
        }
    }


    public static String validateString(String str, boolean ALLOW_EMPTY){
        try {
            if(!ALLOW_EMPTY && str.trim().isEmpty()){
                throw new IllegalArgumentException("Empty input it's not allowed ");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return str.trim();
    }


    public static String validateString(String str){
       return validateString(str,false);
    }
}