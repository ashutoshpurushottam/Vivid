package com.eigendaksh.vivid.screens.notes;

import com.eigendaksh.vivid.data.db.NotesItem;

import java.util.List;

/**
 * Created by Ashutosh Purushottam on 04/12/17.
 * UltraCash Technologies
 */

public interface NotesView {
    void showLoading();
    void hideLoading();
    void showAddNote();
    void loadNotes(List<NotesItem> notesItems);

}
