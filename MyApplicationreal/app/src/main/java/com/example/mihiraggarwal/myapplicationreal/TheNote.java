package com.example.mihiraggarwal.myapplicationreal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mihiraggarwal.myapplicationreal.Models.NoteDetails;

public class TheNote extends AppCompatActivity {
    private NoteDetails mInitialNote;
    private static final String TAG = "TheNote";
    private TextView text_title;
    private TextView text_content;
    private TextView text_timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_note);

        text_title = findViewById(R.id.title_heading);
        text_content = findViewById(R.id.content_display);
        text_timestamp = findViewById(R.id.date_display);

        if (getIntent().hasExtra("selected_note")){
            mInitialNote = getIntent().getParcelableExtra("selected_note");
            Log.d(TAG, "onCreate: " + mInitialNote.toString());
            setNoteProperties();
        }
    }

    private void setNoteProperties() {
        text_title.setText(mInitialNote.getTitle());
        text_content.setText(mInitialNote.getContent());
        text_timestamp.setText(mInitialNote.getTimestamp());
    }

    public void goBack(View view){
        startActivity(new Intent(TheNote.this, ViewNotes.class));
    }
}
