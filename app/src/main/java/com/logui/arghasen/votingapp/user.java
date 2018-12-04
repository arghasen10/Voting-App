package com.logui.arghasen.votingapp;

public class user {
    //String userId;
    String username;
    int option;
    public user(){

    }

    public user(String username,int option) {
        //this.userId = userId;
        this.username = username;
        this.option = option;
    }

   /*public String getUserId() {
        return userId;
    }
*/
    public String getUsername() {
        return username;
    }

    public int getOption() {
        return option;
    }
}

