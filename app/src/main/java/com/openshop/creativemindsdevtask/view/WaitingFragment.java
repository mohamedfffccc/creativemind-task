package com.openshop.creativemindsdevtask.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openshop.creativemindsdevtask.R;
import com.openshop.creativemindsdevtask.adapter.OrdersAdapter;
import com.openshop.creativemindsdevtask.data.model.OrderItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WaitingFragment extends Fragment {

    public int status;
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
        data.add(new OrderItem(0,"ابي اروح الهايبر وما معي سيارة" ,
                "car" , "رجال" , "منذ ساعة" , "فزعلا سيارة" , 4));
        data.add(new OrderItem(0,"ابي اكل هامبورجر ولا يوجد محل قريبة" ,
                "food" , "رجال" , "منذ ساعة" , "فزعلا اكل" , 6));
        data.add(new OrderItem(0,"ابي اروح الهايبر وما معي سيارة" ,
                "car" , "سيدان" , "منذ ساعة" , "فزعلا سيارة" , 8));
        data.add(new OrderItem(0,"ابي اروح الهايبر وما معي سيارة" ,
                "car" , "رجال" , "منذ ساعة" , "فزعلا سيارة" , 8));
        data.add(new OrderItem(0,"ابي اروح الهايبر وما معي سيارة" ,
                "car" , "رجال" , "منذ ساعة" , "فزعلا سيارة" , 3));
        data.add(new OrderItem(0,"ابي اروح الهايبر وما معي سيارة" ,
                "car" , "سيدات" , "منذ ساعة" , "فزعلا سيارة" , 1));
        adapter = new OrdersAdapter(data , getActivity());
        ordersRv.setAdapter(adapter);
    }
}