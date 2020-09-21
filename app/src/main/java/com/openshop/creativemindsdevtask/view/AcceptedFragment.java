package com.openshop.creativemindsdevtask.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openshop.creativemindsdevtask.R;
import com.openshop.creativemindsdevtask.adapter.OrdersAdapter;
import com.openshop.creativemindsdevtask.data.model.OrderItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AcceptedFragment extends Fragment {
    @BindView(R.id.orders_rv)
    RecyclerView ordersRv;
    ArrayList<OrderItem> data;
    OrdersAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sub_orders, container, false);
        ButterKnife.bind(this,v);
        addOrders();

        // Inflate the layout for this fragment
        return v;
    }
    private void addOrders() {
        ordersRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        data = new ArrayList<>();
        data.add(new OrderItem(1,"ابي اروح الهايبر وما معي سيارة" ,
                "car" , "رجال" , "منذ ساعة" , "فزعة سيارة" , 5));
        data.add(new OrderItem(1,"ابي اكل هامبورجر ولا يوجد محل قريبة" ,
                "food" , "رجال" , "منذ ساعة" , "فوعة اكل" , 6));
        data.add(new OrderItem(1,"ابي اروح الهايبر وما معي سيارة" ,
                "car" , "سيدات" , "منذ 6 ساعة" , "فزعة سيارة" , 9));
        data.add(new OrderItem(1,"ابي اروح الهايبر وما معي سيارة" ,
                "car" , "رجال" , "منذ 9 ساعة" , "فزعة سيارة" , 15));
        data.add(new OrderItem(1,"ابي اكل هامبورجر ولا يوجد محل قريبة" ,
                "food" , "سيدات" , "منذ 2 ساعة" , "فزعلا اكل" , 11));
        data.add(new OrderItem(1,"ابي اروح الشيراتون ممكن حدا يوصلنية" ,
                "car" , "سيدات" , "منذ 3 ساعة" , "فزعلا سيارة" , 30));

        adapter = new OrdersAdapter(data , getActivity());
        ordersRv.setAdapter(adapter);
    }
}