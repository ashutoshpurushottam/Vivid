package com.eigendaksh.vivid.screens.notes;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.eigendaksh.vivid.R;
import com.eigendaksh.vivid.utils.utilities.ActivityUtils;

public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.notes);
            actionBar.setSubtitle(getString(R.string.tutorial_app));
        }

        NotesFragment notesFragment = (NotesFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (notesFragment == null) {
            notesFragment = NotesFragment.getInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    notesFragment, R.id.contentFrame);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
