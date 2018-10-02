package com.eigendaksh.vivid.screens.weather;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.eigendaksh.vivid.R;
import com.eigendaksh.vivid.application.AppConstants;
import com.eigendaksh.vivid.application.VividApplication;
import com.eigendaksh.vivid.model.WeatherItem;
import com.eigendaksh.vivid.screens.weatherdetail.WeatherDetailActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class WeatherFragment extends RxFragment implements WeatherView, WeatherAdapter.OnWeatherItemClickListener {

    @Inject
    WeatherPresenter _mWeatherPresenter;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private WeatherAdapter mWeatherAdapter;
    private TextView errorText;

    public WeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VividApplication.getComponent(this).inject(this);
        _mWeatherPresenter.setView(this);

    }

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView = view.findViewById(R.id.weather_list);
        errorText = view.findViewById(R.id.error_text);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                linearLayoutManager.getOrientation()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mWeatherAdapter = new WeatherAdapter(new WeatherItemPresenterImpl(new ArrayList<WeatherItem>()), this);
        recyclerView.setAdapter(mWeatherAdapter);

    }

    @Override
    public void loadWeather(final List<WeatherItem> weatherItemList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                errorText.setVisibility(View.INVISIBLE);
                mWeatherAdapter.renewItems(weatherItemList);
                hideLoading();
            }
        });
    }

    @Override
    public void showError() {
        recyclerView.setVisibility(View.INVISIBLE);
        errorText.setVisibility(View.VISIBLE);

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
    public void onResume() {
        super.onResume();
        start();
    }

    @Override
    public void start() {
        _mWeatherPresenter.updateWeather();
        showLoading();
    }

    @Override
    public void onItemClick(long id) {
        Intent intent = new Intent(getActivity(), WeatherDetailActivity.class);
        intent.putExtra(AppConstants.WEATHER_ID, id);
        startActivity(intent);
    }
}
