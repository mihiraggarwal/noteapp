package com.example.mihiraggarwal.myapplicationreal;

import android.content.Intent;
import android.nfc.Tag;
import android.provider.ContactsContract;
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
import com.google.api.LogDescriptor;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ViewNotes extends AppCompatActivity implements NotesRecyclerAdapter.OnNoteListener{
    // UI components
    private RecyclerView mRecyclerView;
    // Vars
    private ArrayList<NoteDetails> mNotes=new ArrayList<>();
    private NotesRecyclerAdapter mNotesRecyclerAdapter;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);
        mAuth=FirebaseAuth.getInstance();
        mRecyclerView=findViewById(R.id.recyclerView);

        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNoteClick(1);
            }
        });

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
        for (int i=0; i<=100; i++){
            NoteDetails note=new NoteDetails();
            note.setTitle("Title #" + i);
            note.setContent("content" + i);
            note.setTimestamp("26.05.19");
            mNotes.add(note);
        }
        mNotesRecyclerAdapter.notifyDataSetChanged();
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
        startActivity(new Intent(ViewNotes.this,TheNote.class));
    }
}
