package com.test.alex.interfaces;

import com.test.alex.Pojo.DaysModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetWeekData {
    @GET("/schedule/get_group_lessons_v2/4/")
    Call<ArrayList<DaysModel>> loadData();
}
