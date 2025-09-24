package org.bany.utils;

import java.util.regex.Pattern;

public class ValidateInputs {

    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    private static final int MIN_PASSWORD_LENGTH = 6;

    public static String validateEmail(String email){

        if(email == null || email.trim().isEmpty()) throw new IllegalArgumentException("The email cannot be empty");

        if(!EMAIL_REGEX.matcher(email).matches()) throw new IllegalArgumentException("Invalid email format");
        return email.trim();
    }

    public static String validatePassword(String password){
        if (password == null || password.trim().isEmpty()){
            throw new IllegalArgumentException("The password cannot be empty");
        }
        if(password.length() < MIN_PASSWORD_LENGTH){
            throw new IllegalArgumentException("The password has to be larger than 5");
        }
        return password.trim();
    }


    public static String validateString(String str, boolean ALLOW_EMPTY){
        if(!ALLOW_EMPTY && str.trim().isEmpty()){
            throw new IllegalArgumentException("Empty input it's not allowed");
        }
        return str.trim();
    }


    public static String validateString(String str){
        return validateString(str,false);
    }
}