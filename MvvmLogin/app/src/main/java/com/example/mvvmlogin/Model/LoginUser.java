package com.example.mvvmlogin.Model;

import android.util.Patterns;

public class LoginUser {

    private String user;
    private String strPassword;

    public LoginUser(String Username, String Password) {
        user = Username;
        strPassword = Password;
    }

    public String getStrEmailAddress() {
        return user;
    }

    public String getStrPassword() {
        return strPassword;
    }




    public boolean isPasswordLengthGreaterThan5() {
        return getStrPassword().length() > 5;
    }
}
