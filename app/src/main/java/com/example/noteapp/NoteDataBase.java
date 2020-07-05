package com.example.noteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class NoteDataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myDataBase";
    private static final String DATABASE_TAB = "myTable";
    // columns
    private static final String NID = "Note ID";
    private static final String CREATION_DATE = "Date";
    private static final String TIME = "Time";
    private static final String TITLE= "Title";
    private static final String DETAILS = "Details";

    NoteDataBase(Context content) {
        super(content, DATABASE_TAB, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL CREATE TABLE()
        String createQuery = "CREATE TABLE " + DATABASE_TAB + "(" + NID + "INT PRIMARY KEY, "
                + TITLE + " TEXT, " + DETAILS + " TEXT, " + CREATION_DATE + " TEXT, " + TIME
                + " TEXT" + ")";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion) {
            return;
        }
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TAB);
    }

    public long addNote(Note n) {
        SQLiteDatabase newDB = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(TITLE, n.getTitle());
        v.put(CREATION_DATE, n.getDate());
        v.put(TIME, n.getTime());
        v.put(DETAILS, n.getInfo());
        long keyID = newDB.insert(DATABASE_TAB, null, v);
        return keyID;
    }

    public Note getNote(long id) {
        SQLiteDatabase d = this.getReadableDatabase();
        Cursor c = d.query(DATABASE_TAB, new String[]{NID, TITLE, DETAILS, CREATION_DATE, TIME}
        , NID+"=?", new String[]{String.valueOf(id)}, null, null, null);
        if (c != null)
            c.moveToFirst();

        Note n = new Note(c.getLong(0), c.getString(1), c.getString(2),
                c.getString(3), c.getString(4));
        return n;
    }

    public List<Note> getNotes() {
        SQLiteDatabase d = this.getReadableDatabase();
        List<Note> notes = new ArrayList<>();
        String query = "SELECT * FROM "+DATABASE_TAB;
        Cursor c = d.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                Note n = new Note();
                n.updateTitle(c.getString(1));
                n.updateID(c.getLong(0));
                n.updateInfo(c.getString(2));
                n.updateDate(c.getString(3));
                n.updateTime(c.getString(4));
                notes.add(n);
            }while (c.moveToNext());
        }

        // System.out.println(notes.size() + " morioh");
        // for (int i=0 ; i < notes.size();i++){
           // System.out.println(notes.get(i).getInfo());
        // }
        return notes;
    }
}
