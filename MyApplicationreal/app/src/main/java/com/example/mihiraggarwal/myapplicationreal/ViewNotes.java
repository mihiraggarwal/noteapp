package com.example.mihiraggarwal.myapplicationreal;

import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mihiraggarwal.myapplicationreal.Models.NoteDetails;
import com.example.mihiraggarwal.myapplicationreal.adapter.NotesRecyclerAdapter;
import com.example.mihiraggarwal.myapplicationreal.util.VerticalSpacingItemDecorator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.LogDescriptor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewNotes extends AppCompatActivity implements NotesRecyclerAdapter.OnNoteListener{
    // UI components
    private RecyclerView mRecyclerView;
    // Vars
    private ArrayList<NoteDetails> mNotes=new ArrayList<>();
    private NotesRecyclerAdapter mNotesRecyclerAdapter;

    private FirebaseAuth mAuth;

    private static final String TAG = "ViewNotes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);
        mAuth=FirebaseAuth.getInstance();
        mRecyclerView=findViewById(R.id.recyclerView);

        initRecyclerView();
        insertNotes();
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

    private void insertNotes(){
        FirebaseUser user=mAuth.getCurrentUser();
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("Notes")
                .whereEqualTo("UID", user.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot:task.getResult()){
                        NoteDetails note = new NoteDetails();
                        note.setTitle(documentSnapshot.getString("title"));
                        note.setContent(documentSnapshot.getString("content"));
                        note.setTimestamp(documentSnapshot.getString("timestamp"));
                        mNotes.add(note);
                        Log.d(TAG, documentSnapshot.getId() + "=>" + documentSnapshot.getData());
                    }
                    mNotesRecyclerAdapter.notifyDataSetChanged();
                } else {
                    Log.d(TAG, "Error getting documents" , task.getException());
                }
            }
        });
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator itemDecorator=new VerticalSpacingItemDecorator(12);
        mRecyclerView.addItemDecoration(itemDecorator);
        mNotesRecyclerAdapter=new NotesRecyclerAdapter(mNotes, this);
        mRecyclerView.setAdapter(mNotesRecyclerAdapter);
    }

    @Override
    public void onNoteClick(int position){
        Intent intent = new Intent(this, TheNote.class);
        intent.putExtra("selected_note", mNotes.get(position));
        startActivity(intent);
        Log.d(TAG, "onNoteClick: " + position);
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Are you sure you want to exit?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(0);
            }
        });
        alertDialogBuilder.setNegativeButton("No", null);
        alertDialogBuilder.show();
    }
}
