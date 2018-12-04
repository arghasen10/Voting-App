package com.logui.arghasen.votingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUP extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;
    EditText edittextemail, edittextpassword;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        progressBar =(ProgressBar) findViewById(R.id.Progressbar);
        edittextemail = (EditText) findViewById(R.id.edittextemail);
        edittextpassword = (EditText) findViewById(R.id.edittextpassword);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.buttonSignUP).setOnClickListener(this);
        findViewById(R.id.logintextview).setOnClickListener(this);
    }
    private void registerUser(){
        String username = edittextemail.getText().toString().trim();
        String password = edittextpassword.getText().toString().trim();
        if(username.isEmpty()){
            edittextemail.setError("Username is required");
            edittextemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()){
            edittextemail.setError("Please enter a valid Email");
            edittextemail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            edittextpassword.setError("Password is required");
            edittextpassword.requestFocus();
            return;
        }
        if(password.length()<6){
            edittextpassword.setError("Password length is more than 6");
            edittextpassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"You are successfully registered. Go to login",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"You are already registered",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logintextview:
                    startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.buttonSignUP:
                registerUser();
        }
    }
    @Override
    public void onBackPressed() {

        return;
    }
}
