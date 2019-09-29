package com.example.strokeapps1;

import android.provider.ContactsContract;

public class Users {
    String UserId;
    String UserName;
    String UserEmail;
    String UserPassword;

    public Users() {

    }

    public Users(String userId, String userName, String userEmail, String userPassword) {
        UserId = userId;
        UserName = userName;
        UserEmail = userEmail;
        UserPassword = userPassword;
    }

    public String getUserId() {
        return UserId;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }
}