package org.bany.utils;

public class ValidateInputs {

    public String validateEmail(String email){
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
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
