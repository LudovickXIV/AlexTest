package com.test.alex;

import android.os.AsyncTask;
import android.util.Log;

import com.test.alex.Pojo.DaysModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetData extends AsyncTask<Void, Void, Void> {

    private String mURL;
    private OnDataBack listener;

    public GetData(String url, OnDataBack listener) {
        this.mURL = url;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        listener.onStartDataLoad();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetWeekData service = retrofit.create(GetWeekData.class);
        service.loadData().enqueue(new Callback<ArrayList<DaysModel>>() {
            @Override
            public void onResponse(Call<ArrayList<DaysModel>> call, Response<ArrayList<DaysModel>> response) {
                listener.onSuccess(response.body());
                listener.onEnd();
            }

            @Override
            public void onFailure(Call<ArrayList<DaysModel>> call, Throwable t) {
                listener.onFailure(t);
                listener.onEnd();
            }
        });
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
