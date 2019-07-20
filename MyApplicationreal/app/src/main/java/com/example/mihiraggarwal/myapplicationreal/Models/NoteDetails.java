package com.example.mihiraggarwal.myapplicationreal.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class NoteDetails implements Parcelable {
    private String title;
    private String timestamp;
    private String content;

    public NoteDetails(String title, String timestamp, String content) {
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
    }

    public NoteDetails() {
    }

    protected NoteDetails(Parcel in) {
        title = in.readString();
        content = in.readString();
        timestamp = in.readString();
    }

    public static final Creator<NoteDetails> CREATOR = new Creator<NoteDetails>() {
        @Override
        public NoteDetails createFromParcel(Parcel in) {
            return new NoteDetails(in);
        }

        @Override
        public NoteDetails[] newArray(int size) {
            return new NoteDetails[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeString(timestamp);
    }
}
