package com.eigendaksh.vivid.screens.addnotes;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.eigendaksh.vivid.R;
import com.eigendaksh.vivid.application.VividApplication;
import com.eigendaksh.vivid.data.db.NotesItem;
import com.eigendaksh.vivid.utils.utilities.UIUtilities;

import javax.inject.Inject;

public class AddNoteFragment extends Fragment implements AddNoteView {

    private EditText titleEditText;
    private EditText descriptionEditText;

    @Inject AddNotePresenter _mPresenter;

    public AddNoteFragment() {
        // Required empty public constructor
    }

    public static AddNoteFragment getInstance() {
        return new AddNoteFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VividApplication.getComponent(this).inject(this);
        _mPresenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleEditText = view.findViewById(R.id.title_edit_text);
        descriptionEditText = view.findViewById(R.id.description_edit_text);
        Button addNoteButton = view.findViewById(R.id.add_button);

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                long currentTime = System.currentTimeMillis();
                addNote(title, description, currentTime);
            }
        });
    }

    @Override
    public void addNote(String title, String description, long currentTime) {
        if(title.isEmpty()) {
            showTitleRequired();
        } else {
            _mPresenter.addNote(title, description, currentTime);
        }

    }

    @Override
    public void showTitleRequired() {
        UIUtilities.showToast(getActivity(), getString(R.string.enter_some_title));
    }

    @Override
    public void noteAdded(NotesItem item) {
        getActivity().finish();
    }

    @Override
    public void showError(String message) {
        UIUtilities.showToast(getActivity(), message);
    }
}
