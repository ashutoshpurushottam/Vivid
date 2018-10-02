package com.eigendaksh.vivid.screens.detailnote;

import com.eigendaksh.vivid.screens.weatherdetail.WeatherDetailView;

/**
 * Created by Ashutosh Purushottam on 05/12/17.
 * EigenDaksh - App Development
 */

public interface NoteDetailPresenter {

    void setView(NoteDetailView detailView);
    void loadData(long id);
    void deleteData(long id);

}
