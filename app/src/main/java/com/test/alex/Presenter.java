package com.test.alex;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.alex.Pojo.DaysModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Presenter {

    private Context context;
    private OnLoadFragment listener;

    public Presenter(Context context, OnLoadFragment listener){
        this.context = context;
        this.listener = listener;
    }

    public void getData(){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork.isConnected();
            Log.d("TAG", isConnected + "");
                new GetData(context.getResources().getString(R.string.url),
                        new OnDataBack() {
                            @Override
                            public void onStartDataLoad() {
                            }

                            @Override
                            public void onEnd() {
                            }

                            @Override
                            public void onSuccess(ArrayList<DaysModel> data) {
                                listener.OnLoadFragment(data);
                                saveData(data);
                            }
                            @Override
                            public void onFailure(Throwable throwable) {
                                listener.OnLoadException(throwable);
                            }
                        }).execute();
            } else {
            listener.OnLoadFragment(loadData());
        }
    }

    private void saveData(ArrayList<DaysModel> list) {
        try {
            SharedPreferences preferences = context.getSharedPreferences("KEY_GRAND", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            Gson gson = new Gson();
            String data = gson.toJson(list);

            editor.putString("KEY_DATA", data);

            editor.apply();

        } catch (Throwable throwable) {
        }
    }


    private ArrayList<DaysModel> loadData(){
        try {
            SharedPreferences preferences = context.getSharedPreferences("KEY_GRAND", MODE_PRIVATE);
            Gson gson = new Gson();
            String data = preferences.getString("KEY_DATA", null);

            Type type = new TypeToken<ArrayList<DaysModel>>() {
            }.getType();
            ArrayList<DaysModel> list = gson.fromJson(data, type);

            return list;
        } catch (Throwable throwable){
        }
        return null;
    }
}
