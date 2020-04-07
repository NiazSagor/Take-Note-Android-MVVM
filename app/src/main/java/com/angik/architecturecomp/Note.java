package com.angik.architecturecomp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//This enables the system to make sql database according to the following class
@Entity(tableName = "note_table")//Table name
public class Note {
    //Following 4 variables represents each column in a table
    @PrimaryKey(autoGenerate = true)//id is primary key
    private int id;
    private String title;
    private String description;
    private int priority;

    //This default constructor does not have the id, because id will be auto incrementing
    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
