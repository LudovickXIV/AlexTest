package com.test.alex.interfaces;

import com.test.alex.Pojo.DaysModel;

import java.util.ArrayList;

public interface OnFragmentCallback {
    void onItemClickSuccess(ArrayList<DaysModel> data);
    void onItemClickThrowable(Throwable throwable);
}
