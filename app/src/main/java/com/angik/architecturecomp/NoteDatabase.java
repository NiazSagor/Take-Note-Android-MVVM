package com.angik.architecturecomp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//This annotations enables the system to use the Note class to use them as entities
//Version number changes if we need to make changes on the database or table
@Database(entities = Note.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    //Declaring very first instance of the database which is null at the beginning
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();//Abstract means this method has no body

    public static synchronized NoteDatabase getInstance(Context context) {
        //Synchronized means only one thread can access the method at a time
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
            //When an instance is built the "noteDao" method is also given appropriate body, which returns a NoteDao type interface
        }
        return instance;
    }

    //This method ensures that a database is made at the very beginning of the app at any onCreate method
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        //Member variable, which will be filled up in the constructor
        private NoteDao noteDao;

        public PopulateDbAsyncTask(NoteDatabase db) {
            this.noteDao = db.noteDao();//db is the created instance of the database, which means "noteDao" is also given proper body and can return NoteDao interface
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Putting some default values at the beginning, even there is no data retrieved yet from server or any other source
            noteDao.insert(new Note("Title 1", "Description 1", 1));
            noteDao.insert(new Note("Title 2", "Description 2", 2));
            noteDao.insert(new Note("Title 3", "Description 3", 3));
            return null;
        }
    }
}
