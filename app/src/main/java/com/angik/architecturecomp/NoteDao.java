package com.angik.architecturecomp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//This is used to perform various action is the database
@Dao
public interface NoteDao {
    //These annotations call the necessary SQL query which is preset
    //And below the annotations, the methods are for the invocation of that particular query
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    //As there is no default "deleteAllData" query is SQL command, we are writing like this
    @Query("DELETE FROM note_table")
    void deleteAllNotes();//This method represents the SQL command

    //Selecting all data from the table which is live data
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();
}
