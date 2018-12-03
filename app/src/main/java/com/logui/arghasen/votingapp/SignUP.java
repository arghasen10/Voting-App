package com.logui.arghasen.votingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUP extends AppCompatActivity implements View.OnClickListener {

    EditText editTextUsername, getEditTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    private registerUser(){

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogin:
                    startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.buttonSignUP:
                registerUser();

                break;
        }
    }
}
