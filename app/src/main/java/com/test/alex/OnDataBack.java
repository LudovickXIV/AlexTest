package com.test.alex;

import com.test.alex.Pojo.DaysModel;

import java.util.ArrayList;
import java.util.List;

public interface OnDataBack {
    void onStartDataLoad();
    void onEnd();
    void onSuccess(ArrayList<DaysModel> data);
    void onFailure(Throwable throwable);
}
