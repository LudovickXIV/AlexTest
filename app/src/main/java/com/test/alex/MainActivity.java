package com.test.alex;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.test.alex.Pojo.DaysModel;
import com.test.alex.fragments.DaysListFragment;
import com.test.alex.fragments.FullDayFragment;
import com.test.alex.interfaces.OnFragmentCallback;
import com.test.alex.interfaces.OnLoadFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnFragmentCallback {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.mToolbar);
        setSupportActionBar(toolbar);

        manager = getSupportFragmentManager();

        presenter = new Presenter(this, new OnLoadFragment() {
            @Override
            public void OnLoadFragment(ArrayList<DaysModel> data) {
                loadListFragment(data);
            }

            @Override
            public void OnLoadException(Throwable throwable) {

            }
        });
        presenter.getData();
    }

    private void loadListFragment(ArrayList<DaysModel> data){
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("KEY", data);

        DaysListFragment fragment = new DaysListFragment();
        fragment.setArguments(bundle);
        transaction = manager.beginTransaction();
        transaction
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                        android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .add(R.id.mFragmentHolder, fragment, "listFragment")
                .commit();
    }

    private void loadDayListFragment(ArrayList<DaysModel> data){
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("KEY_FULL", data);

        FullDayFragment fragment = new FullDayFragment();
        fragment.setArguments(bundle);
        transaction = manager.beginTransaction();
        transaction
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                        android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.mFragmentHolder, fragment)
                .addToBackStack("listFragment")
                .commit();
    }

    @Override
    public void onItemClickSuccess(ArrayList<DaysModel> data) {
        loadDayListFragment(data);
    }

    @Override
    public void onItemClickThrowable(Throwable throwable) {

    }

    @Override
    public void onBackPressed() {
        if(manager.getBackStackEntryCount() == 0) {
            super.onBackPressed();
        }
        else {
            manager.popBackStack();
        }
    }
}
