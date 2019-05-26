package com.example.mihiraggarwal.myapplicationreal.Models;

public class NoteDetails {
    private String title;
    private String timestamp;
    private String content;

    public NoteDetails(String title, String timestamp, String content) {
        this.title = title;
        this.timestamp = timestamp;
        this.content = content;
    }

    public NoteDetails() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NoteDetails{" +
                "title='" + title + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

}
