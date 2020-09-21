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

public class RefusedFragment extends Fragment {

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
        data.add(new OrderItem(2,"عندي عزومة ومش لاقيةة" ,
                "food" , "سيدات" , "منذ ساعة" , "فزعلا اكل" , 4));
        data.add(new OrderItem(2,"ابي اكل هامبورجر ولا يوجد محل قريبة" ,
                "car" , "رجال" , "منذ ساعة" , "فزعلا اكل" , 4));
        data.add(new OrderItem(2,"ابي اروح الهايبر وما معي سيارة" ,
                "car" , "رجال" , "منذ ساعة" , "فزعلا سيارة" , 4));

        adapter = new OrdersAdapter(data , getActivity());
        ordersRv.setAdapter(adapter);
    }
}