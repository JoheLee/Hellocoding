package com.example.johesfirstproject;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "planner_table")
public class Planner {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    @ColumnInfo(name = "subject")
    public String subject;
    @NonNull
    @ColumnInfo(name = "category")
    public String category;
    @NonNull
    @ColumnInfo(name = "from")
    public String from;
    @NonNull
    @ColumnInfo(name = "to")
    public String to;
    @NonNull
    @ColumnInfo(name = "date")
    public String date;

    public Planner(@NonNull String subject, @NonNull String category, @NonNull String from, @NonNull String to, @NonNull String date) {
        this.subject = subject;
        this.category = category;
        this.from = from;
        this.to = to;
        this.date = date;
    }
}