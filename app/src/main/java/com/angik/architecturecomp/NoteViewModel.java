package com.angik.architecturecomp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

//View model communicates with the activity or fragment to pass data
public class NoteViewModel extends AndroidViewModel {

    //View model gets data from repository, that's why we initialize our note repository
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    //View model constructor makes new repository object which indirectly creates a database
    public NoteViewModel(@NonNull Application application) {
        super(application);

        repository = new NoteRepository(application);//This creates database
        allNotes = repository.getAllNotes();//Gets all the data from repository
    }

    //Database-operation-methods which invokes the repository to handle actual works
    public void insert(Note note){
        repository.insert(note);
    }

    public void update(Note note){
        repository.update(note);
    }

    public void delete(Note note){
        repository.delete(note);
    }

    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }
}
