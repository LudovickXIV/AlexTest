package com.test.alex.fragments;

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
import com.test.alex.R;
import com.test.alex.adapters.FullDayAdapter;

import java.util.ArrayList;

public class FullDayFragment extends Fragment {

    private ArrayList<DaysModel> data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        data = bundle.getParcelableArrayList("KEY_FULL");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.days_list_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.mRecyclerDaysList);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());

        FullDayAdapter adapter = new FullDayAdapter(getActivity(), data);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
