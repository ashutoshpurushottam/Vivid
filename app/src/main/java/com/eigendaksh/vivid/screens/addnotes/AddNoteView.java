package com.eigendaksh.vivid.screens.addnotes;

import com.eigendaksh.vivid.data.db.NotesItem;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public interface AddNoteView {
    void addNote(String title, String description, long currentTime);
    void showTitleRequired();
    void noteAdded(NotesItem item);
    void showError(String message);
}
