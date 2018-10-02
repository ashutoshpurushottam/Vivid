package com.eigendaksh.vivid.screens.detailnote;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.eigendaksh.vivid.R;
import com.eigendaksh.vivid.utils.utilities.ActivityUtils;

public class NoteDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.note_detail);
            actionBar.setSubtitle(getString(R.string.tutorial_app));
        }

        NoteDetailFragment detailFragment = (NoteDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if(detailFragment == null) {
            detailFragment = NoteDetailFragment.getInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    detailFragment, R.id.contentFrame);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
