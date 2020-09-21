package com.openshop.creativemindsdevtask.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.openshop.creativemindsdevtask.R;

import static com.openshop.creativemindsdevtask.data.helper.HelperMethod.replaceFragment;


public class MyOrdersFragment extends Fragment {
    TabLayout orders_tabl;
    WaitingFragment fragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_orders, container, false);
        orders_tabl = v.findViewById(R.id.fragment_orders_tab_layout);
        fragment = new WaitingFragment();
        replaceFragment( getActivity().getSupportFragmentManager(), R.id.container2,  fragment);
        orders_tabl.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition())
                {
                    case 0:
                        replaceFragment( getActivity().getSupportFragmentManager(), R.id.container2,  fragment);
                        break;
                    case 1:
                        AcceptedFragment fragment_accept = new AcceptedFragment();
                        replaceFragment( getActivity().getSupportFragmentManager(), R.id.container2,  fragment_accept);

                        break;
                    case 2:
                       RefusedFragment fragment_refuse = new RefusedFragment();
                        replaceFragment( getActivity().getSupportFragmentManager(), R.id.container2,  fragment_refuse);

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
        TabLayout.Tab tab = orders_tabl.getTabAt(0);
        tab.select();


        // Inflate the layout for this fragment
        return v;
    }
}