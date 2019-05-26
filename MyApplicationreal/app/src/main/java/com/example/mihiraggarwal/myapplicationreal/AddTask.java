package com.example.mihiraggarwal.myapplicationreal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddTask extends AppCompatActivity {
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        mAuth=FirebaseAuth.getInstance();
    }
    public void saveNote(View view){
        EditText titleet=findViewById(R.id.title_edit);
        EditText contentet=findViewById(R.id.content_edit);
        EditText timestampet=findViewById(R.id.date_edit);
        String title=titleet.getText().toString();
        String content=contentet.getText().toString();
        String timestamp=timestampet.getText().toString();
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        Map<String, Object> noteDetails=new HashMap<>();
        noteDetails.put("title", title);
        noteDetails.put("content", content);
        noteDetails.put("timestamp", timestamp);
        FirebaseUser user=mAuth.getCurrentUser();
        noteDetails.put("UID", user.getUid());
        db.collection("Notes").add(noteDetails)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddTask.this, "Note saved", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddTask.this,ViewNotes.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddTask.this, "Note not saved. Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
