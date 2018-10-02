package com.eigendaksh.vivid.screens.detailnote;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eigendaksh.vivid.R;
import com.eigendaksh.vivid.application.AppConstants;
import com.eigendaksh.vivid.application.VividApplication;
import com.eigendaksh.vivid.data.db.NotesItem;
import com.eigendaksh.vivid.utils.utilities.UIUtilities;
import com.eigendaksh.vivid.utils.utilities.Utility;

import javax.inject.Inject;

public class NoteDetailFragment extends Fragment implements NoteDetailView {

    @Inject NoteDetailPresenter _mPresenter;

    private TextView titleView;
    private TextView dateView;
    private TextView descriptionView;
    private ProgressBar progressBar;


    public NoteDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VividApplication.getComponent(this).inject(this);
        _mPresenter.setView(this);

    }

    public static NoteDetailFragment getInstance() {
        return new NoteDetailFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleView = view.findViewById(R.id.title);
        dateView = view.findViewById(R.id.date);
        descriptionView = view.findViewById(R.id.description);
        progressBar = view.findViewById(R.id.progress_bar);
        Button deleteButton = view.findViewById(R.id.delete_button);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long intentId = getActivity().getIntent().getExtras().getLong(AppConstants.NOTE_ID);
                deleteNote(intentId);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        showLoading();
        long intentId = getActivity().getIntent().getExtras().getLong(AppConstants.NOTE_ID);
        _mPresenter.loadData(intentId);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNoteDetail(NotesItem notesItem) {
        titleView.setText(notesItem.getTitle());
        descriptionView.setText(notesItem.getDescription());
        dateView.setText(Utility.getFormattedMonthDay(getActivity(), notesItem.getTime()));
        hideLoading();
    }

    @Override
    public void deleteNote(long id) {
        _mPresenter.deleteData(id);
    }

    @Override
    public void itemDeleted() {
        UIUtilities.showToast(getActivity().getApplicationContext(), "Item deleted");
        getActivity().finish();
    }

    @Override
    public void showError(String message) {
        UIUtilities.showToast(getActivity(), message);
    }
}
