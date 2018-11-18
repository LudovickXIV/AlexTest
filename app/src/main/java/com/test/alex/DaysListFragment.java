package com.test.alex;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.alex.Pojo.DaysModel;

import java.util.ArrayList;
import java.util.List;

public class DaysListFragment extends Fragment {

    private ArrayList data;
    private OnFragmentCallback callback;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (OnFragmentCallback) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        data = bundle.getParcelableArrayList("KEY");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.days_list_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.mRecyclerDaysList);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());

        WeekAdapter adapter = new WeekAdapter(getActivity(), data, new OnDataBack() {
            @Override
            public void onStartDataLoad() {
            }
            @Override
            public void onEnd() {
            }
            @Override
            public void onSuccess(ArrayList<DaysModel> data) {
                callback.onItemClickSuccess(data);
            }
            @Override
            public void onFailure(Throwable throwable) {
            }
        });

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
