package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toolbar;

import java.util.Calendar;

public class AddNote extends AppCompatActivity {
    private EditText noteDetails, noteTitles;
    private Toolbar toolBar;
    private Calendar cal;
    private String today;
    private String currTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        noteDetails = findViewById(R.id.NoteDetails);
        noteTitles = findViewById(R.id.NoteTitle);
        toolBar = findViewById(R.id.toolbar);
        getSupportActionBar().setTitle("Title");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cal = Calendar.getInstance();
        noteTitles.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        String currDate = cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1)+ "/" + cal.get(Calendar.DAY_OF_MONTH);
        today = currDate;
        currTime = zeroTime(cal.get(Calendar.HOUR)) + ":" + zeroTime(cal.get(Calendar.MINUTE));

    }

    private String zeroTime(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return String.valueOf(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.back,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deletebutton)
            onBackPressed();

        if (item.getItemId() == R.id.submitbutton){
            Note newNote = new Note(noteTitles.getText().toString(),noteDetails.getText().toString(), today, currTime);
            NoteDataBase d = new NoteDataBase(this);
            d.addNote(newNote);

            //MainActivity.doThis(d.getNotes());
            //System.out.println(newNote.getInfo());
            //System.out.println(d.getNotes());

            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
