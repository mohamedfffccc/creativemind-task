package com.openshop.creativemindsdevtask.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.openshop.creativemindsdevtask.R;
import com.openshop.creativemindsdevtask.data.model.githubSquare.GithubSquare;
import com.openshop.creativemindsdevtask.data.model.room.room.RepoItem;

import java.util.List;


public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ProductViewHolder> {


    private Context context;
    private List<RepoItem> articleslist;


    public RoomAdapter(List<RepoItem> articleslist, Context context) {
        this.articleslist = articleslist;
        this.context = context;
//        roomDao = getInistance(context).roomDao();


    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.github_item, parent, false);
        return new ProductViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.githubItemName.setText(articleslist.get(position).getRepo_name()+"");
        holder.githubItemDesc.setText(articleslist.get(position).getRepo_desc()+"");
        holder.githubItemUserName.setText(articleslist.get(position).getOwner_username()+"");


    }

    @Override
    public int getItemCount() {
        return articleslist.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {


        TextView githubItemName;
        TextView githubItemDesc;
        TextView githubItemUserName;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            githubItemName = itemView.findViewById(R.id.github_item_name);
            githubItemDesc = itemView.findViewById(R.id.github_item_desc);
            githubItemUserName = itemView.findViewById(R.id.github_item_user_name);
        }
    }


}
