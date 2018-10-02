package com.eigendaksh.vivid.screens.notes;

import android.content.Context;

import com.eigendaksh.vivid.data.db.NotesItem;
import com.eigendaksh.vivid.utils.utilities.Utility;

import java.util.List;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public class NotesItemPresenterImpl implements NotesItemPresenter {

    private final List<NotesItem> itemList;
    private final Context mContext;

    public NotesItemPresenterImpl(Context context, List<NotesItem> itemList) {
        this.itemList = itemList;
        this.mContext = context;
    }

    @Override
    public void onBindWeatherRowViewAtPosition(NoteItemRowView rowView, int position) {
        NotesItem notesItem = itemList.get(position);
        rowView.setTitle(notesItem.getTitle());
        rowView.setDescription(notesItem.getDescription());
        rowView.setDateString(Utility.getFriendlyDayString(mContext, notesItem.getTime()));
        rowView.setClickListener(notesItem.id);
    }

    @Override
    public int getRowCount() {
        return itemList.size();
    }

    @Override
    public void renewItems(List<NotesItem> newItems) {
        itemList.clear();
        itemList.addAll(newItems);

    }
}
