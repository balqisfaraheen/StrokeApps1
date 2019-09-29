package com.example.strokeapps1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.LoginFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {
    android.widget.TextView textView;
    android.widget.Button btnClick1;
    EditText Email, Password;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        Email = findViewById(R.id.editText1);
        Password = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView4);
        btnClick1 = findViewById(R.id.button1);



        mAuthStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
              FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
              if(mFirebaseUser !=null ){
                  Toast.makeText(login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                  Intent i = new Intent(login.this, main_menu.class);
                  startActivity(i);
              }
              else{
                  Toast.makeText(login.this, "Please Login", Toast.LENGTH_SHORT).show();
              }
            }
        };

        btnClick1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String pwd = Password.getText().toString();
                if (email.isEmpty()) {
                    Email.setError("Please Enter Email");
                    Email.requestFocus();
                }
                else if (pwd.isEmpty()) {
                    Password.setError("Please Enter Password");
                    Password.requestFocus();
                }
                else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(login.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(login.this, "Login Error, Please try again", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent intToHome = new Intent(login.this, main_menu.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(login.this, "Error Occured", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Intent intent = new Intent(login.this,register.class);
                startActivity(intent);

            }
        });

    }
    @Override
    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}



