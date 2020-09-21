package com.openshop.creativemindsdevtask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openshop.creativemindsdevtask.R;
import com.openshop.creativemindsdevtask.data.model.OrderItem;
import com.openshop.creativemindsdevtask.data.model.githubSquare.GithubSquare;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ProductViewHolder> {

    private Context context;
    private List<OrderItem> articleslist;


    public OrdersAdapter(List<OrderItem> articleslist, Context context) {
        this.articleslist = articleslist;
        this.context = context;
//        roomDao = getInistance(context).roomDao();


    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.orderitemItemTvText.setText(articleslist.get(position).getText());
        holder.orderitemItemTvCount.setText(articleslist.get(position).getCount() + "فزعوا");
        holder.orderitemItemTvFazaType.setText(articleslist.get(position).getFazaa_type());
        holder.orderitemItemTvGender.setText(articleslist.get(position).getGender());
        holder.orderitemItemTvTime.setText(articleslist.get(position).getTime()+"");
        if (articleslist.get(position).getStatus()==1 )
        {
            holder.orderitemItemBtnCancel.setVisibility(View.GONE);

        }
        if (articleslist.get(position).getStatus() ==2)
        {
            holder.orderitemItemBtnCancel.setVisibility(View.GONE);
        }
        if (articleslist.get(position).getType().equals("car"))
        {
            holder.orderitemItemIvFazaaType.setImageResource(R.drawable.type_car);
            holder.orderitemItemIvType.setImageResource(R.drawable.type_car);
        }
        if (articleslist.get(position).getType().equals("food"))
        {
            holder.orderitemItemIvFazaaType.setImageResource(R.drawable.type_food);
            holder.orderitemItemIvType.setImageResource(R.drawable.type_food);
        }


    }

    @Override
    public int getItemCount() {
        return articleslist.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {



        @BindView(R.id.orderitem_item_iv_type)
        ImageView orderitemItemIvType;
        @BindView(R.id.orderitem_item_tv_text)
        TextView orderitemItemTvText;
        @BindView(R.id.orderitem_item_tv_time)
        TextView orderitemItemTvTime;
        @BindView(R.id.orderitem_item_tv_gender)
        TextView orderitemItemTvGender;
        @BindView(R.id.orderitem_item_tv_faza_type)
        TextView orderitemItemTvFazaType;
        @BindView(R.id.orderitem_item_iv_fazaa_type)
        ImageView orderitemItemIvFazaaType;
        @BindView(R.id.orderitem_item_tv_count)
        TextView orderitemItemTvCount;
        @BindView(R.id.orderitem_item_lin1)
        LinearLayout orderitemItemLin1;
        @BindView(R.id.orderitem_item_btn_cancel)
        Button orderitemItemBtnCancel;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);


        }
    }


}
