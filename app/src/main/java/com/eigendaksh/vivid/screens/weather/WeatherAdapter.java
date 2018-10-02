package com.eigendaksh.vivid.screens.weather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eigendaksh.vivid.R;
import com.eigendaksh.vivid.model.WeatherItem;

import java.util.List;


/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>{

    private static final int LIST_ITEM_TODAY = 0;
    private static final int LIST_ITEM_GENERAL = 1;

    public interface OnWeatherItemClickListener {
        void onItemClick(long id);
    }

    private final WeatherItemPresenter mPresenter;
    private final OnWeatherItemClickListener mListener;


    public WeatherAdapter(WeatherItemPresenter presenter, OnWeatherItemClickListener listener) {
        this.mPresenter = presenter;
        this.mListener = listener;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case LIST_ITEM_TODAY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_forecast_today,
                        parent, false);
                return new WeatherViewHolder(view, mListener);
            case LIST_ITEM_GENERAL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_forecast,
                        parent, false);
                return new WeatherViewHolder(view, mListener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        mPresenter.onBindWeatherRowViewAtPosition(holder, position);

    }

    @Override
    public int getItemCount() {
        return mPresenter.getRowCount();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return LIST_ITEM_TODAY;
        } else {
            return LIST_ITEM_GENERAL;
        }
    }

    public void renewItems(List<WeatherItem> items) {
        mPresenter.renewItems(items);
        notifyDataSetChanged();
    }

    public static class WeatherViewHolder extends RecyclerView.ViewHolder implements WeatherItemRowView {

        TextView dateTextView;
        TextView maxTempTextView;
        TextView minTempTextView;
        TextView cityTextView;
        ImageView iconImageView;
        TextView descriptionTextView;
        OnWeatherItemClickListener mListener;

        public WeatherViewHolder(View itemView, OnWeatherItemClickListener listener) {
            super(itemView);
            this.mListener = listener;
            dateTextView = itemView.findViewById(R.id.list_item_date_textview);
            maxTempTextView = itemView.findViewById(R.id.list_item_high_textview);
            minTempTextView = itemView.findViewById(R.id.list_item_low_textview);
            cityTextView = itemView.findViewById(R.id.list_item_cityname);
            iconImageView = itemView.findViewById(R.id.list_item_icon);
            descriptionTextView = itemView.findViewById(R.id.list_item_forecast_textview);
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

        @Override
        public void setDateString(String dateString) {
            dateTextView.setText(dateString);
        }

        @Override
        public void setCityName(String cityName) {
            cityTextView.setText(cityName);

        }

        @Override
        public void setMaxTemp(String maxTempString) {
            maxTempTextView.setText(maxTempString);

        }

        @Override
        public void setMinTemp(String minTempString) {
            minTempTextView.setText(minTempString);
        }

        @Override
        public void setIcon(int icon) {
            iconImageView.setImageResource(icon);

        }

        @Override
        public void setDescription(String description) {
            descriptionTextView.setText(description);
        }
    }
}
