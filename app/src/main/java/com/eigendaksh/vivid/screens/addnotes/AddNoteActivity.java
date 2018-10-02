package com.eigendaksh.vivid.screens.addnotes;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.eigendaksh.vivid.R;
import com.eigendaksh.vivid.utils.utilities.ActivityUtils;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.add_note);
            actionBar.setSubtitle(getString(R.string.tutorial_app));
        }

        AddNoteFragment addNoteFragment = (AddNoteFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if(addNoteFragment == null) {
            addNoteFragment = AddNoteFragment.getInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    addNoteFragment, R.id.contentFrame);
        }


    }
}
