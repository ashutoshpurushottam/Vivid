package com.eigendaksh.vivid.screens.addnotes;


/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public interface AddNotePresenter {
    void setView(AddNoteView addNoteView);
    void addNote(String title, String description, long currentTime);

}
