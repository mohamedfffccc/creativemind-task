package com.openshop.creativemindsdevtask.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.openshop.creativemindsdevtask.R;

import static com.openshop.creativemindsdevtask.data.helper.HelperMethod.replaceFragment;

public class DesignTaskActivity extends AppCompatActivity {
    TabLayout tab_l;
    MyOrdersFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme2);
        setContentView(R.layout.activity_design_task);
        tab_l=findViewById(R.id.activity_design_tab_layout);
        fragment=new MyOrdersFragment();
        replaceFragment( getSupportFragmentManager(), R.id.container,  fragment);
        tab_l.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:

                        replaceFragment(getSupportFragmentManager(), R.id.container, fragment);
                        break;
                    case 1:
                        replaceFragment(getSupportFragmentManager(), R.id.container, fragment);
                        break;
                }

                }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //TODO set default selection
        TabLayout.Tab tab = tab_l.getTabAt(0);
        tab.select();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    //
}