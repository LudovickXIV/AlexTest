package com.test.alex;

import android.content.Context;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.test.alex.Pojo.DaysModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.mViewHolder> {

    interface OnPositionClick{
        void currentPosition(int pos);
    }

    private Context context;
    private ArrayList<DaysModel> data;
    private List<List<DaysModel>> allData = new ArrayList<>();
    private String[] days = {"Понедельник", "Вторник", "Среда",
            "Четверг", "Пятница", "Суббота", "Воскресенье"};

    private OnDataBack listener;

    public WeekAdapter(Context context, ArrayList<DaysModel> data, OnDataBack listener){
        this.context = context;
        this.data = data;
        this.listener = listener;
        parseCollectionToDays();
    }

    private void parseCollectionToDays(){
        ArrayList<DaysModel> monday = new ArrayList<>();
        ArrayList<DaysModel> tuesday = new ArrayList<>();
        ArrayList<DaysModel> wednesday = new ArrayList<>();
        ArrayList<DaysModel> thursday = new ArrayList<>();
        ArrayList<DaysModel> friday = new ArrayList<>();
        ArrayList<DaysModel> saturday = new ArrayList<>();
        ArrayList<DaysModel> sunday = new ArrayList<>();

        for (DaysModel arr : data) {
            if (arr.getWeekDay() == 1)monday.add(arr);
            if (arr.getWeekDay() == 2)tuesday.add(arr);
            if (arr.getWeekDay() == 3)wednesday.add(arr);
            if (arr.getWeekDay() == 4)thursday.add(arr);
            if (arr.getWeekDay() == 5)friday.add(arr);
            if (arr.getWeekDay() == 6)saturday.add(arr);
            if (arr.getWeekDay() == 7)sunday.add(arr);
        }

        allData.add(monday);
        allData.add(tuesday);
        allData.add(wednesday);
        allData.add(thursday);
        allData.add(friday);
        allData.add(saturday);
        allData.add(sunday);
    }

    class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected TextView dayOfWeek, amount, today;
        protected CardView mainCard;
        private OnPositionClick listener;

        public mViewHolder(@NonNull View itemView, OnPositionClick listener) {
            super(itemView);
            dayOfWeek = itemView.findViewById(R.id.list_day_of_week);
            amount = itemView.findViewById(R.id.available_amount);
            today = itemView.findViewById(R.id.today);
            mainCard = itemView.findViewById(R.id.mCardList);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (allData.get(getAdapterPosition()).size() == 0) {
                Snackbar.make(mainCard, "Нет занятий на сегодня :(", Snackbar.LENGTH_SHORT).show();
            } else {
                listener.currentPosition(getAdapterPosition());
                Log.d("TAG", getAdapterPosition() + "");
            }
        }
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.days_list_item, viewGroup, false);
        return new mViewHolder(v, new OnPositionClick() {
            @Override
            public void currentPosition(int pos) {
                listener.onSuccess((ArrayList<DaysModel>) allData.get(pos));
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder vh, int position) {

        Calendar calendar = Calendar.getInstance();

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek){
            case 1 :
                dayOfWeek = 6;
                break;
            case 2:
                dayOfWeek = 0;
                break;
            case 3:
                dayOfWeek = 1;
                break;
            case 4:
                dayOfWeek = 2;
                break;
            case 5:
                dayOfWeek = 3;
                break;
            case 6:
                dayOfWeek = 4;
                break;
            case 7:
                dayOfWeek = 5;
                break;
        }

        Log.d("TAG", calendar.get(Calendar.DAY_OF_WEEK) + "");
        Log.d("TAG", dayOfWeek + "");
        vh.amount.setText(String.valueOf(allData.get(position).size()));
        vh.dayOfWeek.setText(String.valueOf(days[position]));

        if (dayOfWeek == position) {
            vh.today.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public int getItemCount() {
        return allData.size();
    }
}
