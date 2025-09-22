package org.bany.utils;

import java.util.regex.Pattern;

public class ValidateInputs {

    public String validateEmail(String email){
        Pattern emailRegex = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@\"\n"
                + "[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$");

        if(emailRegex.matcher(email).matches()){
            throw new RuntimeException("Invalid Email");
        }
        return email;
    }

    public String validatePassword(String password){
        byte passwordMinLong = 6;
        if(password.length() < passwordMinLong){
            throw new RuntimeException("The password has to be larger than 5");
        }
        return  password;
    }
}
