package com.eigendaksh.vivid.screens.weatherdetail;

import android.content.Context;

import com.eigendaksh.vivid.application.VividApplication;
import com.eigendaksh.vivid.data.dao.WeatherEntryDao;
import com.eigendaksh.vivid.data.db.WeatherEntry;
import com.eigendaksh.vivid.model.WeatherItemDetail;
import com.eigendaksh.vivid.utils.utilities.Utility;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


/**
 * Created by Ashutosh Purushottam
 * EigenDaksh - App Development
 */

public class WeatherDetailPresenterImpl implements WeatherDetailPresenter {

    private final Context mContext;
    private WeatherDetailView detailView;

    public WeatherDetailPresenterImpl(Context context) {
        this.mContext = context;
        VividApplication.getComponent(mContext).inject(this);
    }



    @Override
    public void setView(WeatherDetailView detailView) {
        this.detailView = detailView;
    }

    @Override
    public void loadData(long id) {
        Observable<WeatherItemDetail> itemObservable = getWeatherDetailItemObservable(id);
        itemObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherItemDetail>() {
                    @Override
                    public void accept(WeatherItemDetail detail) throws Exception {
                        detailView.showWeatherDetail(detail);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable.getLocalizedMessage());
                    }
                });

    }

    private Observable<WeatherItemDetail> getWeatherDetailItemObservable(final long id) {

        return Observable.fromCallable(new Callable<WeatherItemDetail>() {
            @Override
            public WeatherItemDetail call() throws Exception {
                WeatherEntryDao weatherEntryDao = VividApplication.getDatabase(mContext).getWeatherEntryDao();

                WeatherEntry weatherEntry = weatherEntryDao.getEntryFromId(id);
                WeatherItemDetail weatherItemDetail = new WeatherItemDetail();
                weatherItemDetail.setShortDescription(weatherEntry.getShortDescription());
                weatherItemDetail.setCityName(weatherEntry.getCityName());
                weatherItemDetail.setDateString(Utility.getFormattedMonthDay(mContext, weatherEntry.getDate() * 1000L));
                weatherItemDetail.setDrawable(Utility.getIconResourceForWeatherCondition(weatherEntry.getWeatherId()));
                weatherItemDetail.setHumidityString(String.valueOf(weatherEntry.getHumidity()));
                weatherItemDetail.setId(weatherEntry.id);
                weatherItemDetail.setMaxTemp(Utility.formatTemperature(mContext, weatherEntry.getMaxTemp()));
                weatherItemDetail.setMinTemp(Utility.formatTemperature(mContext, weatherEntry.getMinTemp()));
                weatherItemDetail.setPressureString(String.valueOf(weatherEntry.getPressure()));
                weatherItemDetail.setWindString(Utility.getFormattedWind(mContext, weatherEntry.getWindSpeed(), weatherEntry.getDegrees()));
                return weatherItemDetail;
            }
        });

    }
}
