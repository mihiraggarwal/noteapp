package com.example.mihiraggarwal.myapplicationreal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ViewNotes extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);
        mAuth=FirebaseAuth.getInstance();
    }
    public void logout(View view) {
        mAuth.signOut();
        Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ViewNotes.this,MainActivity.class));
        finish();
    }
    public void addNote(View view) {
        startActivity(new Intent(ViewNotes.this,AddTask.class));
    }
}
