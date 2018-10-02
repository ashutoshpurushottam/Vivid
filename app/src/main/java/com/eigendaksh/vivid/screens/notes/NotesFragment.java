package com.eigendaksh.vivid.screens.notes;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eigendaksh.vivid.R;
import com.eigendaksh.vivid.application.AppConstants;
import com.eigendaksh.vivid.application.VividApplication;
import com.eigendaksh.vivid.data.db.NotesItem;
import com.eigendaksh.vivid.screens.addnotes.AddNoteActivity;
import com.eigendaksh.vivid.screens.detailnote.NoteDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NotesFragment extends Fragment implements NotesView, NotesAdapter.OnNotesItemClickListener {

    @Inject NotesListPresenter _mNotesListPresenter;

    private RecyclerView notesRecyclerView;
    private FloatingActionButton fab;
    private NotesAdapter mListAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;


    public NotesFragment() {
        // Required empty public constructor
    }

    public static NotesFragment getInstance() {
        return new NotesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VividApplication.getComponent(this).inject(this);
        mListAdapter = new NotesAdapter(new NotesItemPresenterImpl(getActivity(), new ArrayList<NotesItem>()),
                this );
        _mNotesListPresenter.setView(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        _mNotesListPresenter.loadNotes();
        showLoading();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notesRecyclerView = view.findViewById(R.id.notes_list);
        notesRecyclerView.setAdapter(mListAdapter);

        int numColumns = getContext().getResources().getInteger(R.integer.num_notes_columns);
        notesRecyclerView.setHasFixedSize(true);
        notesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

        fab = getActivity().findViewById(R.id.fab_add_notes);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddNote();
            }
        });

        // Pull-to-refresh
        swipeRefreshLayout =
                view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark),
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                _mNotesListPresenter.loadNotes();
            }
        });


    }

    @Override
    public void showAddNote() {
        Intent intent = new Intent(getActivity(), AddNoteActivity.class);
        startActivity(intent);

    }

    @Override
    public void loadNotes(final List<NotesItem> notesItems) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mListAdapter.renewItems(notesItems);
                hideLoading();
            }
        });
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClick(long id) {
        Intent intent = new Intent(getActivity(), NoteDetailActivity.class);
        intent.putExtra(AppConstants.NOTE_ID, id);
        startActivity(intent);
    }
}
