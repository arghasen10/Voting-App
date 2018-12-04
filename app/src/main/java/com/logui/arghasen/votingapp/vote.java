package com.logui.arghasen.votingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class vote extends AppCompatActivity implements View.OnClickListener{
    public int option;
    public String username;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        username = extras.getString("UserName");


    }
    private void databasesend(){
        String id = myRef.push().getKey();
        user newuser = new user(username,option);
        myRef.child(username).setValue(newuser);
        Intent intent = new Intent(vote.this,logout.class);
        startActivity(intent);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                option = 1;
                databasesend();
                break;
            case R.id.button2:
                option=2;
                databasesend();
                break;
            case R.id.button3:
                option=3;
                databasesend();
                break;
            case R.id.button4:
                option=4;
                databasesend();
                break;
            case R.id.button5:
                option=5;
                databasesend();
                break;
        }

    }
    @Override
    public void onBackPressed() {
        return;
    }
}
