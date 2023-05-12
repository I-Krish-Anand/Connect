package com.connect.register;

import com.connect.repository.Repository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterViewModel {


    public boolean validateUserName(String userName) {
       if(Pattern.matches("[a-z[A-Z][0-9]]{5,20}",userName))
        return Repository.getInstance().validateUsername(userName);
       
       System.out.println("Username Regex Failed:");
       return false;
    }

    // Add Mobile number validation and trim string values


    public boolean validatePassword(String password) {
        
    	String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        
        return matcher.matches();
          
        
    }


    public void createAccount(String name, String phoneNumber, String username, String password, int age, String gender) {
        Repository.getInstance().createAccount(name, phoneNumber, username, password, age, gender);
    }


}
