package com.eigendaksh.vivid.screens.weatherdetail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eigendaksh.vivid.R;
import com.eigendaksh.vivid.application.AppConstants;
import com.eigendaksh.vivid.application.VividApplication;
import com.eigendaksh.vivid.model.WeatherItemDetail;

import javax.inject.Inject;

public class WeatherDetailFragment extends Fragment implements WeatherDetailView {


    @Inject WeatherDetailPresenter _mDetailPresenter;


    private ImageView iconView;
    private TextView friendlyDateView;
    private TextView descriptionView;
    private TextView highTempView;
    private TextView lowTempView;
    private TextView humidityView;
    private TextView windView;
    private TextView pressureView;
    private TextView cityNameView;
    private ProgressBar progressBar;



    public WeatherDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        VividApplication.getComponent(this).inject(this);
        _mDetailPresenter.setView(this);

    }

    public static WeatherDetailFragment getInstance() {
        return new WeatherDetailFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        long intentId = getActivity().getIntent().getExtras().getLong(AppConstants.WEATHER_ID);
        showLoading();
        _mDetailPresenter.loadData(intentId);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iconView = view.findViewById(R.id.detail_icon);
        friendlyDateView = view.findViewById(R.id.detail_day_textview);
        descriptionView = view.findViewById(R.id.detail_forecast_textview);
        highTempView = view.findViewById(R.id.detail_high_textview);
        lowTempView =  view.findViewById(R.id.detail_low_textview);
        humidityView =  view.findViewById(R.id.detail_humidity_textview);
        windView =  view.findViewById(R.id.detail_wind_textview);
        pressureView =  view.findViewById(R.id.detail_pressure_textview);
        cityNameView = view.findViewById(R.id.city_name_textview);
        progressBar = view.findViewById(R.id.progress_bar);

    }

    @Override
    public void showWeatherDetail(WeatherItemDetail detail) {
        iconView.setImageResource(detail.getDrawable());
        friendlyDateView.setText(detail.getDateString());
        descriptionView.setText(detail.getShortDescription());
        highTempView.setText(detail.getMaxTemp());
        lowTempView.setText(detail.getMinTemp());
        humidityView.setText(getString(R.string.humidity_text, detail.getHumidityString()));
        windView.setText(detail.getWindString());
        pressureView.setText(getString(R.string.pressure_text, detail.getPressureString()));
        cityNameView.setText(detail.getCityName());

        hideLoading();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
