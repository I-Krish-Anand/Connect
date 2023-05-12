package com.connect.login;

import com.connect.repository.Repository;

public class LoginViewModel
{

    public boolean verifycredentials(String username, String password) {
        return Repository.getInstance().verifyCredentials(username,password);
    }
}
