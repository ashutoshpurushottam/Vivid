package com.eigendaksh.vivid.screens.notes;

import com.eigendaksh.vivid.data.db.NotesItem;
import com.eigendaksh.vivid.model.WeatherItem;
import com.eigendaksh.vivid.screens.weather.WeatherItemRowView;

import java.util.List;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public interface NotesItemPresenter {

    void onBindWeatherRowViewAtPosition(NoteItemRowView rowView, int position);
    int getRowCount();
    void renewItems(List<NotesItem> newItems);
}
