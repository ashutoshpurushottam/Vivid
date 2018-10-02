package com.eigendaksh.vivid.screens.detailnote;

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
 * Created by Ashutosh Purushottam on 05/12/17.
 * EigenDaksh - App Development
 */

public class NoteDetailPresenterImpl implements NoteDetailPresenter {

    private final Context mContext;
    private NoteDetailView noteDetailView;

    public NoteDetailPresenterImpl(Context context) {
        this.mContext = context;
        VividApplication.getComponent(mContext).inject(this);

    }

    @Override
    public void setView(NoteDetailView detailView) {
        this.noteDetailView = detailView;
    }

    @Override
    public void loadData(long id) {
        Observable<NotesItem> notesItemObservable = getNotesItem(id);
        notesItemObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NotesItem>() {
                    @Override
                    public void accept(NotesItem item) throws Exception {
                        noteDetailView.showNoteDetail(item);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.w(throwable.getLocalizedMessage());
                    }
                });
    }

    private Observable<NotesItem> getNotesItem(final long id) {

        return Observable.fromCallable(new Callable<NotesItem>() {
            @Override
            public NotesItem call() throws Exception {

                NotesItemDao itemDao = VividApplication.getDatabase(mContext).getNotesItemDao();
                return itemDao.getNotesById(id);
            }
        });

    }

    @Override
    public void deleteData(long id) {
        Observable<Boolean> deleteObservable = deleteNotesItem(id);

        deleteObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if(aBoolean) {
                            noteDetailView.itemDeleted();
                        } else {
                            noteDetailView.showError("Failed to delete item.");
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable.getMessage());
                    }
                });
    }

    private Observable<Boolean> deleteNotesItem(final long id) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                NotesItemDao itemDao = VividApplication.getDatabase(mContext).getNotesItemDao();
                int numItems = itemDao.deleteNotesItem(id);
                return numItems > 0;
            }
        });

    }
}
