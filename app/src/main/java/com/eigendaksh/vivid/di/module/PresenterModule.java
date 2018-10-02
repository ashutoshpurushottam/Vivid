package com.eigendaksh.vivid.di.module;

import android.content.Context;

import com.eigendaksh.vivid.screens.addnotes.AddNotePresenter;
import com.eigendaksh.vivid.screens.addnotes.AddNotePresenterImpl;
import com.eigendaksh.vivid.screens.detailnote.NoteDetailPresenter;
import com.eigendaksh.vivid.screens.detailnote.NoteDetailPresenterImpl;
import com.eigendaksh.vivid.screens.notes.NotesListPresenter;
import com.eigendaksh.vivid.screens.notes.NotesListPresenterImpl;
import com.eigendaksh.vivid.screens.weather.WeatherPresenter;
import com.eigendaksh.vivid.screens.weather.WeatherPresenterImpl;
import com.eigendaksh.vivid.screens.weatherdetail.WeatherDetailPresenter;
import com.eigendaksh.vivid.screens.weatherdetail.WeatherDetailPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

@Module
public class PresenterModule {

    private final Context mContext;

    public PresenterModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @Singleton
    public WeatherPresenter provideWeatherPresenter() {
        return new WeatherPresenterImpl(mContext);
    }

    @Provides
    @Singleton
    public WeatherDetailPresenter provideWeatherDetailPresenter() {
        return new WeatherDetailPresenterImpl(mContext);
    }

    @Provides
    @Singleton
    public NotesListPresenter provideNotesListPresenter() {
        return new NotesListPresenterImpl(mContext);
    }

    @Provides
    @Singleton
    public AddNotePresenter provideAddNotePresenter() {
        return new AddNotePresenterImpl(mContext);
    }

    @Provides
    @Singleton
    public NoteDetailPresenter provideNoteDetailPresenter() {
        return new NoteDetailPresenterImpl(mContext);
    }

}
