package com.eigendaksh.vivid.screens.notes;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public interface NoteItemRowView {

    void setTitle(String title);
    void setDescription(String description);
    void setDateString(String dateString);
    void setClickListener(long id);
}
