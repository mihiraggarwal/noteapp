package com.example.mihiraggarwal.myapplicationreal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TheNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_note);
    }
    public void goBack(View view){
        startActivity(new Intent(TheNote.this, ViewNotes.class));
    }
}
