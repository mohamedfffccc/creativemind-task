package com.openshop.creativemindsdevtask.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.openshop.creativemindsdevtask.R;
import com.openshop.creativemindsdevtask.data.model.githubSquare.GithubSquare;

import java.util.List;

import butterknife.BindView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.ProductViewHolder> {



    private Context context;
    private List<GithubSquare> articleslist;
    private Dialog dialog;


    public GithubAdapter(List<GithubSquare> articleslist, Context context) {
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
        holder.githubItemName.setText(articleslist.get(position).getName() + "");
        holder.githubItemDesc.setText(articleslist.get(position).getDescription() + "");
        holder.githubItemUserName.setText(articleslist.get(position).getOwner().getLogin() + "");
        if (articleslist.get(position).getFork() == true) {
            holder.card.setBackgroundColor(context.getColor(R.color.green));
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialoge(position, context);
                return true;
            }
        });

    }

    //TODO logout
    private void showDialoge(int i, Context c) {
        dialog = new Dialog(context, R.style.customDialogTheme);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dialog_select_from, null);
        dialog.setContentView(v);
        TextView repo = (TextView) v.findViewById(R.id.dialog_repo_tv);
        TextView owner = (TextView) v.findViewById(R.id.dialog_owner_tv);

        repo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(articleslist.get(i).getHtmlUrl())));

                dialog.dismiss();


            }
        });
        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(articleslist.get(i).getOwner().getHtmlUrl())));


                dialog.dismiss();


            }
        });


        dialog.show();

    }

    @Override
    public int getItemCount() {
        return articleslist.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        CardView card;

        TextView githubItemName;
        TextView githubItemDesc;
        TextView githubItemUserName;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            githubItemName = itemView.findViewById(R.id.github_item_name);
            githubItemDesc = itemView.findViewById(R.id.github_item_desc);
            githubItemUserName = itemView.findViewById(R.id.github_item_user_name);
            card=itemView.findViewById(R.id.card);
        }
    }


}
