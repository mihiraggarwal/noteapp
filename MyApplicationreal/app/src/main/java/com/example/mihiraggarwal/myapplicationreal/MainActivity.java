package com.example.mihiraggarwal.myapplicationreal;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        if (user!=null){
            startActivity(new Intent(MainActivity.this,ViewNotes.class));
        }
    }
    public void signUp(View view){
        EditText emailet=findViewById(R.id.emailAddress_edit);
        EditText passwordet=findViewById(R.id.password_edit);
        String email=emailet.getText().toString();
        String password=passwordet.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            // Successful sign in. Update the UI with the user's information
                            FirebaseUser user=mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Sign up successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,AddTask.class));
                        } else {
                            // Failed sign in. Display a message to the user regarding the same
                            Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                            System.out.println(task.getException());
                        }
                    }
                });
    }
    public void login(View view){
        EditText emailet=findViewById(R.id.emailAddress_edit);
        EditText passwordet=findViewById(R.id.password_edit);
        String email=emailet.getText().toString();
        String password=passwordet.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            // Successful sign in. Update the UI with the user's information
                            FirebaseUser user=mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,ViewNotes.class));
                        } else  {
                            // Failed sign in. Display a message to the user regarding the same
                            Toast.makeText(MainActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

