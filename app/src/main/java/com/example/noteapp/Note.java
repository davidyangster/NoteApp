package com.example.noteapp;

public class Note {
    private long n_id;
    private String title;
    private String info;
    private String date;
    private String time;


    public Note() {

    }
    public Note(long id, String title, String info, String date, String time) {
        n_id = id;
        this.title = title;
        this.info = info;
        this.date = date;
        this.time = time;
    }
    public Note(String title, String info, String date, String time) {
        //n_id = id;
        this.title = title;
        this.info = info;
        this.date = date;
        this.time = time;
    }


    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public void updateID(long newId) {
        n_id = newId;
    }

    public void updateTitle(String newTitle) {
        title = newTitle;
        // updateTime();
    }

    public void updateInfo(String newInfo) {
        info = newInfo;
        // updateTime();
    }

    public void updateDate(String newDate) {
        date = newDate;
    }

    public void updateTime(String newTime) { time = newTime;}

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
