package com.eigendaksh.vivid.screens.notes;

import android.content.Context;

import com.eigendaksh.vivid.application.VividApplication;
import com.eigendaksh.vivid.data.dao.NotesItemDao;
import com.eigendaksh.vivid.data.db.NotesItem;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public class NotesListPresenterImpl implements NotesListPresenter {

    private final Context mContext;
    private NotesView notesView;

    public NotesListPresenterImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void setView(NotesView notesView) {
        this.notesView = notesView;
    }

    @Override
    public void loadNotes() {
        Timber.d("Notes loaded");
        NotesItemDao notesItemDao = VividApplication.getDatabase(mContext).getNotesItemDao();
        Flowable<List<NotesItem>> allNotes = notesItemDao.getAllNotes();

        allNotes.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<NotesItem>>() {
                    @Override
                    public void accept(List<NotesItem> items) throws Exception {
                        notesView.loadNotes(items);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable.getLocalizedMessage());
                    }
                });
    }
}
