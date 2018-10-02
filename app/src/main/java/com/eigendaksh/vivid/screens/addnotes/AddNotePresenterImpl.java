package com.eigendaksh.vivid.screens.addnotes;

import android.content.Context;

import com.eigendaksh.vivid.application.VividApplication;
import com.eigendaksh.vivid.data.dao.NotesItemDao;
import com.eigendaksh.vivid.data.db.NotesItem;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Ashutosh Purushottam.
 * EigenDaksh - App Development
 */

public class AddNotePresenterImpl implements AddNotePresenter {

    private final Context mContext;
    private AddNoteView mView;

    public AddNotePresenterImpl(Context context) {
        this.mContext = context;
        VividApplication.getComponent(mContext).inject(this);
    }

    @Override
    public void setView(AddNoteView addNoteView) {
        this.mView = addNoteView;
    }

    @Override
    public void addNote(String title, String description, long currentTime) {
        Observable<NotesItem> itemObservable = insertNoteItemObservable(title, description, currentTime);
        itemObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NotesItem>() {
                    @Override
                    public void accept(NotesItem notesItem) throws Exception {
                        mView.noteAdded(notesItem);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable.getLocalizedMessage());
                        Timber.e(throwable.getLocalizedMessage());
                    }
                });

    }

    private Observable<NotesItem> insertNoteItemObservable(final String title, final String description, final long currentTime) {

        return Observable.fromCallable(new Callable<NotesItem>() {
            @Override
            public NotesItem call() throws Exception {

                NotesItem notesItem = new NotesItem();
                notesItem.setTitle(title);
                notesItem.setDescription(description);
                notesItem.setTime(currentTime);

                NotesItemDao itemDao = VividApplication.getDatabase(mContext).getNotesItemDao();
                itemDao.insertAll(notesItem);

                return notesItem;
            }
        });
    }


}
