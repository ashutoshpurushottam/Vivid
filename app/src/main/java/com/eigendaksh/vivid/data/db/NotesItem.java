package com.eigendaksh.vivid.data.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

@Entity(tableName = "notes_table" , indices = {@Index(value = {"title"}, unique = true)})
public class NotesItem {

    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "time")
    public long time;

    public NotesItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "NotesItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", time=" + time +
                '}';
    }
}
