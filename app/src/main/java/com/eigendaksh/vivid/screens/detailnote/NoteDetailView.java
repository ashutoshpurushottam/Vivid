package com.eigendaksh.vivid.screens.detailnote;

import com.eigendaksh.vivid.data.db.NotesItem;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public interface NoteDetailView {
    void showLoading();
    void hideLoading();
    void showNoteDetail(NotesItem notesItem);
    void deleteNote(long id);
    void itemDeleted();
    void showError(String message);

}
