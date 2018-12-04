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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    EditText edittextemail,edittextpassword;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.signuptextview).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.Progressbar);
        mAuth =FirebaseAuth.getInstance();
        edittextemail = (EditText) findViewById(R.id.edittextemail);
        edittextpassword = (EditText)findViewById(R.id.edittextpassword);


    }

    private void loginuser(){
        final String username = edittextemail.getText().toString().trim();
        final String password = edittextpassword.getText().toString().trim();
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
        mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    myRef.setValue(username);
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(MainActivity.this, vote.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signuptextview:
                startActivity(new Intent(this, SignUP.class));
                break;
            case R.id.buttonLogin:
                loginuser();
        }

    }
}
