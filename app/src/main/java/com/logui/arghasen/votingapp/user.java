package com.logui.arghasen.votingapp;

public class user {
    String username;
    int option;
    public user(){

    }

    public user(String username,int option) {
        this.username = username;
        this.option = option;
    }
    public String getUsername() {
        return username;
    }

    public int getOption() {
        return option;
    }
}

