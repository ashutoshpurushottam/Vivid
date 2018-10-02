package com.eigendaksh.vivid.screens.notes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eigendaksh.vivid.R;
import com.eigendaksh.vivid.data.db.NotesItem;
import com.eigendaksh.vivid.model.WeatherItem;

import java.util.List;

/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    public interface OnNotesItemClickListener {
        void onItemClick(long id);
    }


    private final NotesItemPresenter mPresenter;
    private final OnNotesItemClickListener mListener;

    public NotesAdapter(NotesItemPresenter presenter, OnNotesItemClickListener listener) {
        this.mPresenter = presenter;
        this.mListener = listener;
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NotesViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        mPresenter.onBindWeatherRowViewAtPosition(holder, position);

    }

    @Override
    public int getItemCount() {
        return mPresenter.getRowCount();
    }

    public void renewItems(List<NotesItem> items) {
        mPresenter.renewItems(items);
        notifyDataSetChanged();
    }


    public static class NotesViewHolder extends RecyclerView.ViewHolder implements NoteItemRowView {

        TextView titleView;
        TextView descriptionView;
        TextView dateView;

        private final OnNotesItemClickListener mListener;

        public NotesViewHolder(View itemView, OnNotesItemClickListener listener) {
            super(itemView);
            this.mListener = listener;
            titleView = itemView.findViewById(R.id.note_detail_title);
            descriptionView = itemView.findViewById(R.id.note_detail_description);
            dateView = itemView.findViewById(R.id.note_detail_date);

        }

        @Override
        public void setTitle(String title) {
            titleView.setText(title);

        }

        @Override
        public void setDescription(String description) {
            descriptionView.setText(description);

        }

        @Override
        public void setDateString(String dateString) {
            dateView.setText(dateString);

        }

        @Override
        public void setClickListener(final long id) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClick(id);
                }
            });

        }
    }

}
