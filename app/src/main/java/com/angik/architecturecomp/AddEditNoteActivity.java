package com.angik.architecturecomp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "NOTE_ID";

    public static final String EXTRA_TITLE =
            "NOTE_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "NOTE_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "NOTE_PRIORITY";

    private EditText title, description;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        numberPicker = findViewById(R.id.number_picker_priority);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            title.setText(intent.getStringExtra(EXTRA_TITLE));
            description.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }
    }

    private void saveNote() {
        String titleString = title.getText().toString();
        String descriptionString = description.getText().toString();
        int priority = numberPicker.getValue();

        if (titleString.trim().isEmpty() || descriptionString.trim().isEmpty()) {
            Toast.makeText(this, "Field must not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        /* We want this activity to work as a input form
         * That's why we do not do any database operation here, and also do not call any view model here
         * We want to get the user input and pass them to the main activity where database operation will be happened
         * And we can not use the NoteViewModel in this activity as that view model is tied to the main activity life cycle
         */

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, titleString);
        data.putExtra(EXTRA_DESCRIPTION, descriptionString);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
