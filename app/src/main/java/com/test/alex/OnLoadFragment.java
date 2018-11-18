package com.test.alex;

import com.test.alex.Pojo.DaysModel;

import java.util.ArrayList;
import java.util.List;

public interface OnLoadFragment {
    void OnLoadFragment(ArrayList<DaysModel> data);
    void OnLoadException(Throwable throwable);
}