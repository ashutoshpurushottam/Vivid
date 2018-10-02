package com.eigendaksh.vivid.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.eigendaksh.vivid.data.db.NotesItem;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */
@Dao
public interface NotesItemDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertAll(NotesItem... entries);

    @Query("SELECT * FROM notes_table WHERE  id = :notesId")
    NotesItem getNotesById(long notesId);

    @Query("SELECT * FROM notes_table")
    Flowable<List<NotesItem>> getAllNotes();

    @Query("DELETE FROM notes_table WHERE id= :notesId")
    int deleteNotesItem(long notesId);
}
